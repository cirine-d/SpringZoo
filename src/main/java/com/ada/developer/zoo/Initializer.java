package com.ada.developer.zoo;

import com.ada.developer.zoo.animal.Animal;
import com.ada.developer.zoo.animal.AnimalRepository;
import com.ada.developer.zoo.pen.AnimalPen;
import com.ada.developer.zoo.pen.AnimalPenRepository;
import com.ada.developer.zoo.staff.ZooKeeper;
import com.ada.developer.zoo.staff.ZooKeeperRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fj.data.Array;
import static fj.data.Array.array;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

        private final AnimalRepository animalRepository;
        private final AnimalPenRepository animalPenRepository;
        private final ZooKeeperRepository zooKeeperRepository;

        public Initializer(AnimalRepository animalRepository, AnimalPenRepository animalPenRepository,
                        ZooKeeperRepository zooKeeperRepository) {
                this.animalRepository = animalRepository;
                this.animalPenRepository = animalPenRepository;
                this.zooKeeperRepository = zooKeeperRepository;
        }

        @Override
        public void run(String... strings) throws Exception {

                Array<Animal> arrayOfAnimals = array(new Animal("Cujo", "Sloth", array("Dry"), array("Sloth"), 3, 0, 0),
                                new Animal("Daze", "Penguin", array("PartDry-PartWater"), array("Penguin"), 2, 4, 0),
                                new Animal("Harper", "Goat", array("Dry", "Petting"), array("Goat"), 3, 0, 0),
                                new Animal("Sticky", "Dog", array("Dry", "Petting"), array("Dog"), 3, 0, 0),
                                new Animal("Ernst", "Owl", array("Aviary"), array("Owl"), 0, 0, 20),
                                new Animal("Aspen", "Dolphin", array("Aquarium"), array("Dolphin"), 0, 40, 0),
                                new Animal("Camay", "Hippo", array("PartDry-PartWater"), array("Hippo"), 10, 20, 0),
                                new Animal("Blue", "Cat", array("Dry", "Petting"), array("Cat"), 4, 0, 0));
                Array<AnimalPen> arrayOfPens = array(new AnimalPen("Sloths", "Dry", 3, 0, 0),
                                new AnimalPen("Penguins", "PartDry-PartWater", 2, 4, 0),
                                new AnimalPen("Goats", "Petting", 3, 0, 0), new AnimalPen("Dogs", "Petting", 3, 0, 0),
                                new AnimalPen("Owls", "Aviary", 0, 0, 20),
                                new AnimalPen("Dolphins", "Aquarium", 0, 40, 0),
                                new AnimalPen("Hippos", "PartDry-PartWater", 10, 20, 0),
                                new AnimalPen("Cats", "Dry", 4, 0, 0));
                Array<ZooKeeper> arrayOfZooKeepers = array(new ZooKeeper("Hardip", array("Dry", "Aviaries")),
                                new ZooKeeper("Alex", array("Aquariums", "PartDry-PartWater")),
                                new ZooKeeper("Farhad", array("Aviaries", "Aquarium")),
                                new ZooKeeper("Alan", array("Dry", "Petting")));

                arrayOfAnimals.map(animal -> animalRepository.save(animal));
                arrayOfPens.map(pen -> animalPenRepository.save(pen));
                arrayOfZooKeepers.map(keeper -> zooKeeperRepository.save(keeper));

                // arrayOfAnimals.map(animal ->
                // {(array(animalRepository.findOneBySpecies(animal.getSpecies()))
                // .findAvailablePens(array(animalPenRepository.findAll()))).assign(animal);});

                // availablePens.stream().findFirst().get().assignAnimal(animalRepository.findBySpecies("Cat"));

                // Array<AnimalPen> availablePens2 = animalRepository.findBySpecies("Sloth")
                // .findAvailablePens(animalPenRepository.findAll().stream().collect(Collectors.toSet()));

                // animalPenRepository.save(availablePens.stream().findFirst().get());
                // animalPenRepository.save(availablePens2.stream().findFirst().get());
                // System.out.print(availablePens3);
                System.out.print(animalRepository.findOneBySpecies("Cat"));
                zooKeeperRepository.findAll().forEach(System.out::println);
                animalPenRepository.findAll().forEach(System.out::println);
                animalRepository.findAll().forEach(System.out::println);
        }

}