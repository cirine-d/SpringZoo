package com.ada.developer.zoo.web;

import com.ada.developer.zoo.animal.Animal;
import com.ada.developer.zoo.animal.AnimalRepository;
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
class AnimalController {

    private final Logger log = LoggerFactory.getLogger(AnimalController.class);
    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/animals")
    Collection<Animal> animals() {
        return animalRepository.findAll();
    }

    @GetMapping("/animals/{id}")
    ResponseEntity<?> getAnimal(@PathVariable Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/animal")
    ResponseEntity<Animal> createAnimal(@Valid @RequestBody Animal animal) throws URISyntaxException {
        log.info("Request to create animal: {}", animal);
        Animal result = animalRepository.save(animal);
        return ResponseEntity.created(new URI("/api/animal/" + result.getId())).body(result);
    }

    @PutMapping("/animal/{id}")
    ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal animal) {
        animal.setId(id);
        log.info("Request to update animal: {}", animal);
        Animal result = animalRepository.save(animal);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/animal/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        log.info("Request to delete group: {}", id);
        animalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}