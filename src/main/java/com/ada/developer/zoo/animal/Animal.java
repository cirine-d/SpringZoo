package com.ada.developer.zoo.animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ada.developer.zoo.pen.AnimalPen;

import fj.data.Array;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Animal {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String species;
    private Array<String> penType;
    private Array<String> animalsCompatibleWith;
    private Integer landSpace;
    private Integer waterSpace;
    private Integer airSpace;
    private Boolean inPen;

    public Animal() {
    }

    public Animal(String name, String species, Array<String> penType, Array<String> animalsCompatibleWith,
            Integer landSpace, Integer waterSpace, Integer airSpace) {
        this.name = name;
        this.species = species;
        this.penType = penType;
        this.animalsCompatibleWith = animalsCompatibleWith;
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

    public Array<String> getPenType() {
        return penType;
    }

    public void setPenType(Array<String> penType) {
        this.penType = penType;
    }

    public Array<String> getAnimalsCompatibleWith() {
        return animalsCompatibleWith;
    }

    public void setAnimalsCompatibleWith(Array<String> animalsCompatibleWith) {
        this.animalsCompatibleWith = animalsCompatibleWith;
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
            if (!this.animalsCompatibleWith.exists(x -> x.equals(species))) {
                canShare[0] = false;
            }
        });
        return canShare[0];
    }

    public Array<AnimalPen> findAvailablePens(Array<AnimalPen> existingPens) {
        final Array<AnimalPen> availablePens = existingPens
                .filter(pen -> (this.penType.exists(x -> x.equals(pen.getPenType()))
                        && this.landSpace.equals(pen.getLandSpace()) && this.waterSpace.equals(pen.getWaterSpace())
                        && this.airSpace.equals(pen.getAirSpace()) && this.canShare(pen)
                        && !(pen.getCapacity().equals(0))));
        return availablePens;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", name='" + name + ", penTypes='" + penType + '\'' + '}';
    }
}