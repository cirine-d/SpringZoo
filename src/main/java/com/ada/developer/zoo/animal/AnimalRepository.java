package com.ada.developer.zoo.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByName(String name);

    Animal findBySpecies(String species);

    Animal findOneBySpecies(String species);

}
