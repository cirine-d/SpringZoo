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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.ada.developer.zoo.entities.Animal;
import com.ada.developer.zoo.entities.AnimalPen;
import com.ada.developer.zoo.entities.ZooKeeper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface FileHandler {

    public static ArrayList<Animal> readFromAnimalFile(final String filePath) {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        try {
            Gson gson = new Gson();
            JsonArray firstArr = (JsonArray) gson.fromJson(new FileReader(filePath), JsonElement.class);
            JsonArray secondArr = firstArr.get(0).getAsJsonArray();
            animals = new Gson().fromJson(secondArr.toString(), new TypeToken<List<Animal>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public static ArrayList<AnimalPen> readFromAnimalPenFile(final String filePath) {

        ArrayList<AnimalPen> pens = new ArrayList<AnimalPen>();
        try {
            Gson gson = new Gson();
            JsonArray firstArr = (JsonArray) gson.fromJson(new FileReader(filePath), JsonElement.class);
            JsonArray secondArr = firstArr.get(0).getAsJsonArray();
            pens = new Gson().fromJson(secondArr.toString(), new TypeToken<List<AnimalPen>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pens;
    }

    public static ArrayList<ZooKeeper> readFromZooKeeperFile(final String filePath) {

        ArrayList<ZooKeeper> keepers = new ArrayList<ZooKeeper>();
        try {
            Gson gson = new Gson();
            JsonArray firstArr = (JsonArray) gson.fromJson(new FileReader(filePath), JsonElement.class);
            JsonArray secondArr = firstArr.get(0).getAsJsonArray();
            keepers = new Gson().fromJson(secondArr.toString(), new TypeToken<List<ZooKeeper>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return keepers;
    }

    public static void writeAnimalToFile(final ArrayList<Animal> animal, final String filePath) {
        try {
            Type listType = new TypeToken<List<Animal>>() {
            }.getType();
            Gson gson = new Gson();
            OutputStream out = new FileOutputStream(filePath);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.setIndent("  ");
            writer.beginArray();
            gson.toJson(animal, listType, writer);
            writer.endArray();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void writeAnimalPenToFile(final ArrayList<AnimalPen> pen, final String filePath) {
        try {
            Type listType = new TypeToken<List<AnimalPen>>() {
            }.getType();
            Gson gson = new Gson();
            OutputStream out = new FileOutputStream(filePath);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.setIndent("  ");
            writer.beginArray();
            gson.toJson(pen, listType, writer);
            writer.endArray();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeZooKeeperToFile(final ArrayList<ZooKeeper> keeper, final String filePath) {
        try {
            Type listType = new TypeToken<List<ZooKeeper>>() {
            }.getType();
            Gson gson = new Gson();
            OutputStream out = new FileOutputStream(filePath);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.setIndent("  ");
            writer.beginArray();
            gson.toJson(keeper, listType, writer);
            writer.endArray();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Animal retrieveAnimal(String name, final String filePath) {
        ArrayList<Animal> fileOutput = readFromAnimalFile(filePath);
        ArrayList<Animal> newAnimalList = new ArrayList<Animal>();
        Animal[] target = new Animal[1];
        fileOutput.stream().forEach(animal -> {
            if (animal.getName().equals(name)) {
                target[0] = animal;
            }
        });
        fileOutput.stream().filter(animal -> !animal.getName().equals(name))
                .forEach(animal -> newAnimalList.add(animal));
        writeAnimalToFile(newAnimalList, filePath);
        return target[0];
    }

    public static AnimalPen retrieveAnimalPen(String name, final String filePath) {
        ArrayList<AnimalPen> fileOutput = readFromAnimalPenFile(filePath);
        ArrayList<AnimalPen> newAnimalPenList = new ArrayList<AnimalPen>();
        AnimalPen[] target = new AnimalPen[1];
        fileOutput.stream().forEach(pen -> {
            if (pen.getName().equals(name)) {
                target[0] = pen;
            }
        });
        fileOutput.stream().filter(pen -> !pen.getName().equals(name)).forEach(pen -> newAnimalPenList.add(pen));
        writeAnimalPenToFile(newAnimalPenList, filePath);
        return target[0];
    }

    public static ZooKeeper retrieveZooKeeper(String name, final String filePath) {
        ArrayList<ZooKeeper> fileOutput = readFromZooKeeperFile(filePath);
        ArrayList<ZooKeeper> newZooKeeperList = new ArrayList<ZooKeeper>();

        ZooKeeper[] target = new ZooKeeper[1];
        fileOutput.stream().forEach(keeper -> {
            if (keeper.getName().equals(name)) {
                target[0] = keeper;
            }
        });
        fileOutput.stream().filter(keeper -> !keeper.getName().equals(name))
                .forEach(keeper -> newZooKeeperList.add(keeper));
        writeZooKeeperToFile(newZooKeeperList, filePath);
        return target[0];
    }

    public static Animal findAnimal(String name, final String filePath) {
        ArrayList<Animal> fileOutput = readFromAnimalFile(filePath);
        Animal[] target = new Animal[1];
        fileOutput.stream().forEach(animal -> {
            if (animal.getName().equals(name)) {
                target[0] = animal;
            }
        });
        return target[0];
    }

    public static AnimalPen findAnimalPen(String name, final String filePath) {
        ArrayList<AnimalPen> fileOutput = readFromAnimalPenFile(filePath);
        AnimalPen[] target = new AnimalPen[1];
        fileOutput.stream().forEach(pen -> {
            if (pen.getName().equals(name)) {
                target[0] = pen;
            }
        });
        return target[0];
    }

    public static ZooKeeper findZooKeeper(String name, final String filePath) {
        ArrayList<ZooKeeper> fileOutput = readFromZooKeeperFile(filePath);
        ZooKeeper[] target = new ZooKeeper[1];
        fileOutput.stream().forEach(keeper -> {
            if (keeper.getName().equals(name)) {
                target[0] = keeper;
            }
        });
        return target[0];
    }
}