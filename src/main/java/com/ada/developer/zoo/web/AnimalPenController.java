package com.ada.developer.zoo.web;

import com.ada.developer.zoo.FileHandler;
import com.ada.developer.zoo.entities.Animal;
import com.ada.developer.zoo.entities.AnimalPen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
class AnimalPenController {

    private final Logger log = LoggerFactory.getLogger(AnimalPenController.class);

    File file = new File("C:\\Code\\GitKraken\\SpringZoo\\pens.json");
    File animalFile = new File("C:\\Code\\GitKraken\\SpringZoo\\animals.json");

    @GetMapping("/animalPens")
    ArrayList<AnimalPen> pens() {
        return FileHandler.readFromAnimalPenFile(file.getPath());
    }

    @GetMapping("/animalPens/{name}")
    ResponseEntity<?> getPen(@PathVariable String name) {
        AnimalPen pen = FileHandler.findAnimalPen(name, file.getPath());
        if (!pen.equals(null)) {
            return ResponseEntity.ok().body(pen);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/animalPens")
    ResponseEntity<AnimalPen> createAnimalPen(@Valid @RequestBody AnimalPen animalPen) throws URISyntaxException {
        log.info("Request to create animalPen: {}", animalPen);
        ArrayList<AnimalPen> animalList = new ArrayList<AnimalPen>();
        FileHandler.readFromAnimalPenFile(file.getPath()).stream().forEach(item -> animalList.add(item));
        animalList.add(animalPen);
        FileHandler.writeAnimalPenToFile(animalList, file.getPath());
        AnimalPen result = FileHandler.findAnimalPen(animalPen.getName(), file.getPath());
        return ResponseEntity.created(new URI("/api/animalPens/" + result.getName())).body(result);
    }

    @PutMapping("/animalPens/{name}")
    ResponseEntity<AnimalPen> assignAnimalToAnimalPen(@PathVariable String name, @Valid @RequestBody String animal)
            throws URISyntaxException {
        log.info("Request to assign animal to animalPen: {}", name);
        ArrayList<AnimalPen> penListToReturn = new ArrayList<AnimalPen>();
        AnimalPen penToUpdate = FileHandler.retrieveAnimalPen(name, file.getPath());
        Animal animalToassign = FileHandler.findAnimal(animal, animalFile.getPath());
        penToUpdate.assignAnimal(animalToassign);
        penListToReturn.add(penToUpdate);
        FileHandler.readFromAnimalPenFile(file.getPath()).stream().forEach(item -> penListToReturn.add(item));
        FileHandler.writeAnimalPenToFile(penListToReturn, file.getPath());
        AnimalPen result = FileHandler.findAnimalPen(name, file.getPath());
        return ResponseEntity.created(new URI("/api/animalPens/" + result.getName())).body(result);
    }

    @DeleteMapping("/animalPens/{name}")
    public ResponseEntity<?> deleteAnimalPen(@PathVariable String name) {
        log.info("Request to delete animalPen: {}", name);
        FileHandler.retrieveAnimalPen(name, file.getPath());
        return ResponseEntity.ok().build();
    }
}