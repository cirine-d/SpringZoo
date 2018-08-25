package com.ada.developer.zoo.pen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface AnimalPenRepository extends JpaRepository<AnimalPen, Long> {
    AnimalPen findByName(String name);

    AnimalPen findByCapacity(Integer capacity);

}
