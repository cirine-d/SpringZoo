package com.ada.developer.zoo.web;

import com.ada.developer.zoo.entities.AnimalPen;
import com.ada.developer.zoo.repositories.AnimalPenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class AnimalPenController {

    private final Logger log = LoggerFactory.getLogger(AnimalController.class);
    private AnimalPenRepository animalPenRepository;

    public AnimalPenController(AnimalPenRepository animalPenRepository) {
        this.animalPenRepository = animalPenRepository;
    }

    @GetMapping("/animalPens")
    Collection<AnimalPen> animals() {
        return animalPenRepository.findAll();
    }

    @GetMapping("/animalPens/{id}")
    ResponseEntity<?> getAnimal(@PathVariable Long id) {
        Optional<AnimalPen> animalPen = animalPenRepository.findById(id);
        return animalPen.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/animalPen")
    ResponseEntity<AnimalPen> createAnimal(@Valid @RequestBody AnimalPen animalPen) throws URISyntaxException {
        log.info("Request to create animalPen: {}", animalPen);
        AnimalPen result = animalPenRepository.save(animalPen);
        return ResponseEntity.created(new URI("/api/animalPen/" + result.getId())).body(result);
    }

    @PutMapping("/animalPen/{id}")
    ResponseEntity<AnimalPen> updateAnimal(@PathVariable Long id, @Valid @RequestBody AnimalPen animalPen) {
        animalPen.setId(id);
        log.info("Request to update animalPen: {}", animalPen);
        AnimalPen result = animalPenRepository.save(animalPen);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/animalPen/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        log.info("Request to delete group: {}", id);
        animalPenRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}