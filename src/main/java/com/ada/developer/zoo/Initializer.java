package com.ada.developer.zoo;

import com.ada.developer.zoo.entities.Animal;
import com.ada.developer.zoo.entities.AnimalPen;
import com.ada.developer.zoo.entities.ZooKeeper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

        File animals = new File("C:\\Code\\GitKraken\\SpringZoo\\animals.json");
        File pens = new File("C:\\Code\\GitKraken\\SpringZoo\\pens.json");
        File staff = new File("C:\\Code\\GitKraken\\SpringZoo\\staff.json");

        ArrayList<Animal> arrayOfAnimals = new ArrayList<Animal>();
        ArrayList<AnimalPen> arrayOfPens = new ArrayList<AnimalPen>();
        ArrayList<ZooKeeper> arrayOfZooKeepers = new ArrayList<ZooKeeper>();

        @Override
        public void run(String... strings) throws Exception {

                arrayOfAnimals.add(new Animal("Cujo", "Sloth", 3, 0, 0));
                arrayOfAnimals.add(new Animal("Daze", "Penguin", 2, 4, 0));
                arrayOfAnimals.add(new Animal("Harper", "Goat", 3, 0, 0));
                arrayOfAnimals.add(new Animal("Sticky", "Dog", 3, 0, 0));
                arrayOfAnimals.add(new Animal("Ernst", "Owl", 0, 0, 20));
                arrayOfAnimals.add(new Animal("Aspen", "Dolphin", 0, 40, 0));
                arrayOfAnimals.add(new Animal("Camay", "Hippo", 10, 20, 0));
                arrayOfAnimals.add(new Animal("Blue", "Cat", 4, 0, 0));
                arrayOfAnimals.stream().forEach(animal -> {
                        if (animal.getSpecies().equals("Sloth")) {
                                animal.setPenType(Stream.of("Dry").collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Sloth").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Penguin")) {
                                animal.setPenType(Stream.of("PartWater-PartDry")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Penguin").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Goat")) {
                                animal.setPenType(Stream.of("Dry", "Petting")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Goat").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Dog")) {
                                animal.setPenType(Stream.of("Dry", "Petting")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Dog").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Owl")) {
                                animal.setPenType(Stream.of("Aviary").collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Owl").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Dolphin")) {
                                animal.setPenType(
                                                Stream.of("Aquarium").collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Dolphin").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Hippo")) {
                                animal.setPenType(Stream.of("PartWater-PartDry")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Hippo").collect(Collectors.toCollection(ArrayList::new)));
                        } else if (animal.getSpecies().equals("Cat")) {
                                animal.setPenType(Stream.of("Dry").collect(Collectors.toCollection(ArrayList::new)));
                                animal.setAnimalsCompatibleWith(
                                                Stream.of("Cat").collect(Collectors.toCollection(ArrayList::new)));
                        } else {
                                return;
                        }
                });

                arrayOfPens.add(new AnimalPen("Sloths", "Dry", 3, 0, 0, 10));
                arrayOfPens.add(new AnimalPen("Penguins", "PartWater-PartDry", 2, 4, 0, 10));
                arrayOfPens.add(new AnimalPen("Goats", "Petting", 3, 0, 0, 10));
                arrayOfPens.add(new AnimalPen("Dogs", "Petting", 3, 0, 0, 10));
                arrayOfPens.add(new AnimalPen("Owls", "Aviary", 0, 0, 20, 10));
                arrayOfPens.add(new AnimalPen("Dolphins", "Aquarium", 0, 40, 0, 10));
                arrayOfPens.add(new AnimalPen("Hippos", "PartWater-PartDry", 10, 20, 0, 10));
                arrayOfPens.add(new AnimalPen("Cats", "Dry", 4, 0, 0, 10));

                Stream.of("Hardip", "Alex", "Farhad", "Alan")
                                .forEach(name -> arrayOfZooKeepers.add(new ZooKeeper(name)));
                arrayOfZooKeepers.stream().forEach(keeper -> {
                        if (keeper.getName().equals("Hardip")) {
                                keeper.setPenTypes(Stream.of("Dry", "Aviary")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                        } else if (keeper.getName().equals("Alex")) {
                                keeper.setPenTypes(Stream.of("Aquarium", "PartWater-PartDry")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                        } else if (keeper.getName().equals("Farhad")) {
                                keeper.setPenTypes(Stream.of("Aviary", "Aquarium")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                        } else if (keeper.getName().equals("Alan")) {
                                keeper.setPenTypes(Stream.of("Dry", "Petting")
                                                .collect(Collectors.toCollection(ArrayList::new)));
                        } else {
                                return;
                        }
                });

                arrayOfAnimals.stream().forEach(animal -> {
                        animal.findAvailablePens(arrayOfPens.stream().collect(Collectors.toCollection(ArrayList::new)))
                                        .stream().forEach(pen -> {
                                                pen.assignAnimal(animal);
                                        });
                });

                arrayOfPens.stream().forEach(pen -> {
                        pen.findAvailableStaff(
                                        arrayOfZooKeepers.stream().collect(Collectors.toCollection(ArrayList::new)))
                                        .stream().forEach(keeper -> {
                                                keeper.assignPen(pen);
                                        });
                });

                FileHandler.writeAnimalToFile(arrayOfAnimals, animals.getPath());
                FileHandler.writeAnimalPenToFile(arrayOfPens, pens.getPath());
                FileHandler.writeZooKeeperToFile(arrayOfZooKeepers, staff.getPath());

        }

}