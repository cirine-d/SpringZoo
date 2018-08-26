package com.ada.developer.zoo.repositories;

import java.io.Serializable;
import java.util.Optional;

import com.ada.developer.zoo.entities.AnimalPen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface AnimalPenRepository extends JpaRepository<AnimalPen, Long> {
    AnimalPen findByName(String name);

    AnimalPen findByCapacity(Integer capacity);

}
