package com.ada.developer.zoo.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface ZooKeeperRepository extends JpaRepository<ZooKeeper, Long> {
    ZooKeeper findByName(String name);

}
