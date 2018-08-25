package com.ada.developer.zoo.pen;

import com.ada.developer.zoo.animal.Animal;
import com.ada.developer.zoo.staff.ZooKeeper;

import fj.data.Array;
import static fj.data.Array.array;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class AnimalPen {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String penType;
    private Integer landSpace;
    private Integer waterSpace;
    private Integer airSpace;
    private Integer capacity;
    @OneToMany(fetch = FetchType.EAGER)
    private Array<Animal> animals;

    public AnimalPen() {
    }

    public AnimalPen(String name, String penType, Integer landSpace, Integer waterSpace, Integer airSpace) {
        this.name = name;
        this.penType = penType;
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
        this.airSpace = airSpace;
        this.capacity = 10;
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

    public Array<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Array<Animal> animals) {
        this.animals = animals;
    }

    public void assignAnimal(Animal animal) {
        this.animals.append(array(animal));
    }

    public Set<ZooKeeper> findAvailableStaff(Set<ZooKeeper> existingStaff) {

        final Set<ZooKeeper> availableStaff = existingStaff.stream()
                .filter(staff -> (staff.getPenTypes().exists(x -> x.equals(this.penType)))).collect(Collectors.toSet());
        return availableStaff;
    }

    public ArrayList<String> getAssignedAnimalTypes() {
        ArrayList<String> assignedAnimalTypes = new ArrayList<String>();
        this.animals.map(animal -> assignedAnimalTypes.add(animal.getSpecies()));
        return assignedAnimalTypes;
    }

    @Override
    public String toString() {
        return "Pen{" + "id=" + id + ", name='" + name + ", animals='" + animals + '\'' + '}';
    }
}