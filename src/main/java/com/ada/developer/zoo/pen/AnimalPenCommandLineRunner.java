// package com.ada.developer.zoo.pen;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import java.util.stream.Stream;

// @Component
// public class AnimalPenCommandLineRunner implements CommandLineRunner {

// private final AnimalPenRepository repository;

// public AnimalPenCommandLineRunner(AnimalPenRepository repository) {
// this.repository = repository;
// }

// @Override
// public void run(String... strings) throws Exception {
// // Top beers from https://www.beeradvocate.com/lists/top/
// Stream.of("Cat", "Zebra", "Sloth", "Hippo", "Wolf", "Giraffe", "Bee")
// .forEach(name -> repository.save(new AnimalPen(name)));
// repository.findAll().forEach(System.out::println);
// }
// }