package com.ada.developer.zoo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ada.developer.zoo.entities.Animal;
import com.ada.developer.zoo.entities.AnimalPen;
import com.ada.developer.zoo.entities.ZooKeeper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface FileHandler {

    public static ArrayList<Animal> readFromAnimalFile(final String filePath) {

        ArrayList<Animal> animals = new ArrayList<Animal>();
        try {
            InputStream in = new FileInputStream(filePath);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext()) {
                Animal animal = gson.fromJson(reader, Animal.class);
                animals.add(animal);
            }
            reader.endArray();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public static ArrayList<AnimalPen> readFromAnimalPenFile(final String filePath) {

        ArrayList<AnimalPen> pens = new ArrayList<AnimalPen>();
        try {
            InputStream in = new FileInputStream(filePath);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext()) {
                AnimalPen pen = gson.fromJson(reader, AnimalPen.class);
                pens.add(pen);
            }
            reader.endArray();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pens;
    }

    public static ArrayList<ZooKeeper> readFromZooKeeperFile(final String filePath) {

        ArrayList<ZooKeeper> keepers = new ArrayList<ZooKeeper>();
        try {
            InputStream in = new FileInputStream(filePath);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext()) {
                ZooKeeper keeper = gson.fromJson(reader, ZooKeeper.class);
                keepers.add(keeper);
            }
            reader.endArray();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return keepers;
    }

    public static void writeAnimalToFile(final ArrayList<Animal> animals, final String filePath) {
        // try {
        // Gson gson = new Gson();
        // String jsonString = gson.toJson(animal);
        // FileWriter fileWriter = new FileWriter(filePath);
        // fileWriter.write(jsonString);
        // fileWriter.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        try {
            Gson gson = new Gson();
            OutputStream out = new FileOutputStream(filePath);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.setIndent("  ");
            writer.beginArray();
            gson.toJson(animals, Animal.class, writer);
            writer.endArray();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void writeAnimalPenToFile(final AnimalPen pen, final String filePath) {
        try {
            Gson gson = new Gson();
            String jsonString = gson.toJson(pen);
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeZooKeeperToFile(final ZooKeeper keeper, final String filePath) {
        try {
            Gson gson = new Gson();
            String jsonString = gson.toJson(keeper);
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static Animal retrieveAnimal(String name, final String filePath) {
    // ArrayList<Animal> fileOutput = readFromAnimalFile(filePath);
    // Animal[] target = new Animal[0];
    // fileOutput.stream().forEach(animal -> {
    // if (animal.getName().equals(name)) {
    // target[0] = animal;
    // }
    // });
    // fileOutput.stream().filter(animal -> !animal.getName().equals(name))
    // .forEach(animal -> writeAnimalToFile(animal, filePath));
    // return target[0];
    // }

    public static AnimalPen retrieveAnimalPen(String name, final String filePath) {
        ArrayList<AnimalPen> fileOutput = readFromAnimalPenFile(filePath);
        AnimalPen[] target = new AnimalPen[0];
        fileOutput.stream().forEach(pen -> {
            if (pen.getName().equals(name)) {
                target[0] = pen;
            }
        });
        fileOutput.stream().filter(pen -> !pen.getName().equals(name))
                .forEach(pen -> writeAnimalPenToFile(pen, filePath));
        return target[0];
    }

    public static ZooKeeper retrieveZooKeeper(String name, final String filePath) {
        ArrayList<ZooKeeper> fileOutput = readFromZooKeeperFile(filePath);
        ZooKeeper[] target = new ZooKeeper[0];
        fileOutput.stream().forEach(keeper -> {
            if (keeper.getName().equals(name)) {
                target[0] = keeper;
            }
        });
        fileOutput.stream().filter(keeper -> !keeper.getName().equals(name))
                .forEach(keeper -> writeZooKeeperToFile(keeper, filePath));
        return target[0];
    }

}