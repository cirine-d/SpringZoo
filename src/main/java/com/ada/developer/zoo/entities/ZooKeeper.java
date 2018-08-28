package com.ada.developer.zoo.entities;

import javax.persistence.*;

import com.ada.developer.zoo.entities.AnimalPen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "pen_keeper")
public class ZooKeeper {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private ArrayList<String> penTypes;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AnimalPen> assignedPens;

    public ZooKeeper() {
    }

    public ZooKeeper(String name) {
        this.name = name;
        this.penTypes = new ArrayList<String>();
        this.assignedPens = Collections.<AnimalPen>emptySet();
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

    public ArrayList<String> getPenTypes() {
        return penTypes;
    }

    public void setPenTypes(ArrayList<String> penTypes) {
        ArrayList<String> arr = new ArrayList<String>();
        penTypes.forEach(x -> arr.add(x));
        this.penTypes = arr;
    }

    public Set<AnimalPen> getassignedPens() {
        return assignedPens;
    }

    public void setassignedPens(Set<AnimalPen> assignedPens) {
        this.assignedPens = assignedPens;
    }

    public void assignPen(AnimalPen pen) {
        ArrayList<AnimalPen> pens = new ArrayList<>();
        if (this.assignedPens != null) {
            this.assignedPens.stream().forEach(x -> pens.add(x));
        }
        pens.add(pen);
        this.assignedPens = pens.stream().collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "ZooKeeper{" + "id=" + id + ", name='" + name + ", assignedPens='" + assignedPens + '\'' + '}';
    }
}