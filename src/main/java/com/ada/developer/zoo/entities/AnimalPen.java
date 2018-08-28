package com.ada.developer.zoo.entities;

import com.ada.developer.zoo.entities.Animal;
import com.ada.developer.zoo.entities.ZooKeeper;

import org.springframework.data.annotation.Persistent;
import org.springframework.lang.NonNull;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "animal_pen")
public class AnimalPen {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String penType;
    private Integer landSpace;
    private Integer waterSpace;
    private Integer airSpace;
    private Integer capacity;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Animal> animals;

    public AnimalPen() {
    }

    public AnimalPen(String name, String penType, Integer landSpace, Integer waterSpace, Integer airSpace,
            Integer capacity) {
        this.name = name;
        this.animals = Collections.<Animal>emptySet();
        this.penType = penType;
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
        this.airSpace = airSpace;
        this.capacity = capacity;
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

    public String getPenType() {
        return penType;
    }

    public void setPenType(String penType) {
        this.penType = penType;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public void assignAnimal(Animal animal) {
        ArrayList<Animal> animals = new ArrayList<>();
        if (this.animals != null) {
            this.animals.stream().forEach(x -> animals.add(x));
        }
        animals.add(animal);
        this.animals = animals.stream().collect(Collectors.toSet());
        this.capacity = this.capacity - 1;
    }

    public ArrayList<ZooKeeper> findAvailableStaff(ArrayList<ZooKeeper> existingStaff) {
        ArrayList<ZooKeeper> availableStaff = existingStaff.stream()
                .filter(staff -> (staff.getPenTypes().contains(this.penType)))
                .collect(Collectors.toCollection(ArrayList::new));
        return availableStaff;
    }

    public ArrayList<String> getAssignedAnimalTypes() {
        Set<Animal> animals = this.animals;
        ArrayList<String> assignedAnimalTypes = new ArrayList<String>();
        if (animals == null) {
            return assignedAnimalTypes;
        } else {
            animals.stream().forEach(animal -> assignedAnimalTypes.add(animal.getSpecies()));
        }
        return assignedAnimalTypes;
    }

    @Override
    public String toString() {
        return "Pen{" + "id=" + id + ", name='" + name + ", animals='" + animals + '\'' + '}';
    }
}