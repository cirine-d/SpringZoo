package com.ada.developer.zoo.staff;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ada.developer.zoo.pen.AnimalPen;

import fj.data.Array;
import static fj.data.Array.array;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class ZooKeeper {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Array<String> penTypes;
    @OneToMany(fetch = FetchType.EAGER)
    private Array<AnimalPen> assignedPens;

    public ZooKeeper() {
    }

    public ZooKeeper(String name, Array<String> penTypes) {
        this.name = name;
        this.penTypes = penTypes;
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

    public Array<String> getPenTypes() {
        return penTypes;
    }

    public void setPenTypes(Array<String> penTypes) {
        this.penTypes = penTypes;
    }

    public Array<AnimalPen> getassignedPens() {
        return assignedPens;
    }

    public void setassignedPens(Array<AnimalPen> assignedPens) {
        this.assignedPens = assignedPens;
    }

    public void assignPen(AnimalPen pen) {
        this.assignedPens.append(array(pen));
    }

    @Override
    public String toString() {
        return "ZooKeeper{" + "id=" + id + ", name='" + name + ", assignedPens='" + assignedPens + '\'' + '}';
    }
}