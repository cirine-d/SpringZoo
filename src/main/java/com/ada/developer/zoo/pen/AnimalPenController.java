// package com.ada.developer.zoo.pen;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.stream.Collectors;

// @RestController
// public class AnimalPenController {
// private AnimalPenRepository repository;

// public AnimalPenController(AnimalPenRepository repository) {
// this.repository = repository;
// }

// @GetMapping("/good-pens")
// public Collection<AnimalPen> zooPens() {
// return
// repository.findAll().stream().filter(this::isGreat).collect(Collectors.toList());
// }

// private boolean isGreat(AnimalPen penl) {
// return !penl.getName().equals("Budweiser") && !penl.getName().equals("Coors
// Light")
// && !penl.getName().equals("PBR");
// }
// }