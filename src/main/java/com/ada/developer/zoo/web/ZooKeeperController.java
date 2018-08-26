package com.ada.developer.zoo.web;

import com.ada.developer.zoo.entities.ZooKeeper;
import com.ada.developer.zoo.repositories.ZooKeeperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class ZooKeeperController {

    private final Logger log = LoggerFactory.getLogger(AnimalController.class);
    private ZooKeeperRepository zooKeeperRepository;

    public ZooKeeperController(ZooKeeperRepository zooKeeperRepository) {
        this.zooKeeperRepository = zooKeeperRepository;
    }

    @GetMapping("/staff")
    Collection<ZooKeeper> animals() {
        return zooKeeperRepository.findAll();
    }

    @GetMapping("/staff/{id}")
    ResponseEntity<?> getAnimal(@PathVariable Long id) {
        Optional<ZooKeeper> zooKeeper = zooKeeperRepository.findById(id);
        return zooKeeper.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/staffMember")
    ResponseEntity<ZooKeeper> createAnimal(@Valid @RequestBody ZooKeeper zooKeeper) throws URISyntaxException {
        log.info("Request to create zooKeeper: {}", zooKeeper);
        ZooKeeper result = zooKeeperRepository.save(zooKeeper);
        return ResponseEntity.created(new URI("/api/zooKeeper/" + result.getId())).body(result);
    }

    @PutMapping("/staffMember/{id}")
    ResponseEntity<ZooKeeper> updateAnimal(@PathVariable Long id, @Valid @RequestBody ZooKeeper zooKeeper) {
        zooKeeper.setId(id);
        log.info("Request to update zooKeeper: {}", zooKeeper);
        ZooKeeper result = zooKeeperRepository.save(zooKeeper);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/staffMember/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        log.info("Request to delete group: {}", id);
        zooKeeperRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}