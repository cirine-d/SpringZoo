// package com.ada.developer.zoo.staff;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import java.util.stream.Stream;

// @Component
// public class ZooKeeperCommandLineRunner implements CommandLineRunner {

// private final ZooKeeperRepository repository;

// public ZooKeeperCommandLineRunner(ZooKeeperRepository repository) {
// this.repository = repository;
// }

// @Override
// public void run(String... strings) throws Exception {
// // Top beers from https://www.beeradvocate.com/lists/top/
// Stream.of("Cat", "Zebra", "Sloth", "Hippo", "Wolf", "Giraffe", "Bee")
// .forEach(name -> repository.save(new ZooKeeper(name)));
// repository.findAll().forEach(System.out::println);
// }
// }