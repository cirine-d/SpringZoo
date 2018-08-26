package com.ada.developer.zoo.entities;

import javax.persistence.*;

import com.ada.developer.zoo.entities.AnimalPen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class Animal {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String species;
    private ArrayList<String> penType;
    private ArrayList<String> animalsCompatibleWith;
    private Integer landSpace;
    private Integer waterSpace;
    private Integer airSpace;
    private Boolean inPen;

    public Animal() {
    }

    public Animal(String name, String species, Integer landSpace, Integer waterSpace, Integer airSpace) {
        this.name = name;
        this.species = species;
        this.penType = new ArrayList<String>();
        this.animalsCompatibleWith = new ArrayList<String>();
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
        this.airSpace = airSpace;
        this.inPen = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public ArrayList<String> getPenType() {
        return penType;
    }

    public void setPenType(ArrayList<String> penType) {
        ArrayList<String> arr = new ArrayList<String>();
        penType.forEach(x -> arr.add(x));
        this.penType = arr;
    }

    public ArrayList<String> getAnimalsCompatibleWith() {
        return animalsCompatibleWith;
    }

    public void setAnimalsCompatibleWith(ArrayList<String> animalsCompatibleWith) {
        ArrayList<String> arr = new ArrayList<String>();
        animalsCompatibleWith.forEach(x -> arr.add(x));
        this.animalsCompatibleWith = arr;
    }

    public Integer getLandSpace() {
        return landSpace;
    }

    public void setLandSpace(Integer landSpace) {
        this.landSpace = landSpace;
    }

    public Integer getWaterSpace() {
        return waterSpace;
    }

    public void setWaterSpace(Integer waterSpace) {
        this.waterSpace = waterSpace;
    }

    public Integer getAirSpace() {
        return airSpace;
    }

    public void setAirSpace(Integer airSpace) {
        this.airSpace = airSpace;
    }

    public Boolean getInPen() {
        return inPen;
    }

    public void setInPen(Boolean inPen) {
        this.inPen = inPen;
    }

    private Boolean canShare(AnimalPen pen) {
        Boolean[] canShare = { true };
        pen.getAssignedAnimalTypes().forEach(species -> {
            if (!this.animalsCompatibleWith.contains(species)) {
                canShare[0] = false;
            }
        });
        return canShare[0];
    }

    public ArrayList<AnimalPen> findAvailablePens(ArrayList<AnimalPen> existingPens) {
        final ArrayList<AnimalPen> availablePens = existingPens.stream()
                .filter(pen -> (this.penType.contains(pen.getPenType()) && this.landSpace.equals(pen.getLandSpace())
                        && this.waterSpace.equals(pen.getWaterSpace()) && this.airSpace.equals(pen.getAirSpace())
                        && this.canShare(pen) && !(pen.getCapacity().equals(0))))
                .collect(Collectors.toCollection(ArrayList::new));
        return availablePens;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", name='" + name + ", penTypes='" + penType + '\'' + '}';
    }
}