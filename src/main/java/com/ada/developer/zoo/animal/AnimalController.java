// package com.ada.developer.zoo.animal;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.stream.Collectors;

// @RestController
// public class AnimalController {
// private AnimalRepository repository;

// public AnimalController(AnimalRepository repository) {
// this.repository = repository;
// }

// @GetMapping("/good-animals")
// public Collection<Animal> zooAnimals() {
// return
// repository.findAll().stream().filter(this::isGreat).collect(Collectors.toList());
// }

// private boolean isGreat(Animal animal) {
// return !animal.getName().equals("Budweiser") &&
// !animal.getName().equals("Coors Light")
// && !animal.getName().equals("PBR");
// }
// }