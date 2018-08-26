package com.ada.developer.zoo.repositories;

import com.ada.developer.zoo.entities.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByName(String name);

    Animal findBySpecies(String species);

    Animal findOneBySpecies(String species);

}
