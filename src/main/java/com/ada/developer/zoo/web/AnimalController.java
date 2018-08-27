package com.ada.developer.zoo.web;

import com.ada.developer.zoo.FileHandler;
import com.ada.developer.zoo.entities.Animal;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
class AnimalController {

    private final Logger log = LoggerFactory.getLogger(AnimalController.class);
    File file = new File("C:\\Code\\GitKraken\\SpringZoo\\animals.json");

    @GetMapping("/animals")
    ArrayList<Animal> animals() {
        return FileHandler.readFromAnimalFile(file.getPath());
    }

    @GetMapping("/animals/{name}")
    ResponseEntity<?> getAnimal(@PathVariable String name) {
        Animal animal = FileHandler.findAnimal(name, file.getPath());
        if (!animal.equals(null)) {
            return ResponseEntity.ok().body(animal);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/animals")
    ResponseEntity<Animal> createAnimal(@Valid @RequestBody Animal animal) throws URISyntaxException {
        log.info("Request to create animal: {}", animal);
        ArrayList<Animal> animalList = new ArrayList<Animal>();
        FileHandler.readFromAnimalFile(file.getPath()).stream().forEach(item -> animalList.add(item));
        animalList.add(animal);
        FileHandler.writeAnimalToFile(animalList, file.getPath());
        Animal result = FileHandler.findAnimal(animal.getName(), file.getPath());
        return ResponseEntity.created(new URI("/api/animal/" + result.getName())).body(result);
    }

    @PutMapping("/animals/{name}")
    ResponseEntity<Animal> updateAnimal(@PathVariable String name, @Valid @RequestBody Animal animal)
            throws URISyntaxException {
        log.info("Request to update animal: {}", animal);
        ArrayList<Animal> animalList = new ArrayList<Animal>();
        FileHandler.readFromAnimalFile(file.getPath()).stream().forEach(item -> animalList.add(item));
        ArrayList<Animal> updatedList = animalList.stream().filter(keeper -> !keeper.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new));
        updatedList.add(animal);
        FileHandler.writeAnimalToFile(updatedList, file.getPath());
        Animal result = FileHandler.findAnimal(animal.getName(), file.getPath());
        return ResponseEntity.created(new URI("/api/animal/" + result.getName())).body(result);
    }

    @DeleteMapping("/animals/{name}")
    public ResponseEntity<?> deleteAnimal(@PathVariable String name) {
        log.info("Request to delete animal: {}", name);
        FileHandler.retrieveAnimal(name, file.getPath());
        return ResponseEntity.ok().build();
    }
}