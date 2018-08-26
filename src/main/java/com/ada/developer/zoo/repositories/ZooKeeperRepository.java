package com.ada.developer.zoo.repositories;

import com.ada.developer.zoo.entities.ZooKeeper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ZooKeeperRepository extends JpaRepository<ZooKeeper, Long> {
    ZooKeeper findByName(String name);

}
