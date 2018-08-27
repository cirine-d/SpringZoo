package com.ada.developer.zoo.web;

import com.ada.developer.zoo.FileHandler;
import com.ada.developer.zoo.entities.Animal;
import com.ada.developer.zoo.entities.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
class ZooKeeperController {

    private final Logger log = LoggerFactory.getLogger(AnimalController.class);

    File file = new File("C:\\Code\\GitKraken\\SpringZoo\\staff.json");

    @GetMapping("/staff")
    ArrayList<ZooKeeper> staff() {
        return FileHandler.readFromZooKeeperFile(file.getPath());
    }

    @GetMapping("/staff/{name}")
    ResponseEntity<?> getStaffMember(@PathVariable String name) {
        ZooKeeper keeper = FileHandler.findZooKeeper(name, file.getPath());
        if (!keeper.equals(null)) {
            return ResponseEntity.ok().body(keeper);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/staff")
    ResponseEntity<ZooKeeper> createZooKeeper(@Valid @RequestBody ZooKeeper zooKeeper) throws URISyntaxException {
        log.info("Request to create zooKeeper: {}", zooKeeper);
        ArrayList<ZooKeeper> keeperList = new ArrayList<ZooKeeper>();
        FileHandler.readFromZooKeeperFile(file.getPath()).stream().forEach(item -> keeperList.add(item));
        keeperList.add(zooKeeper);
        FileHandler.writeZooKeeperToFile(keeperList, file.getPath());
        ZooKeeper result = FileHandler.findZooKeeper(zooKeeper.getName(), file.getPath());
        return ResponseEntity.created(new URI("/api/zooKeeper/" + result.getName())).body(result);
    }

    @PutMapping("/staff/{name}")
    ResponseEntity<ZooKeeper> updateZooKeeper(@PathVariable String name, @Valid @RequestBody ZooKeeper zooKeeper)
            throws URISyntaxException {
        log.info("Request to update zooKeeper: {}", zooKeeper);
        ArrayList<ZooKeeper> keeperList = new ArrayList<ZooKeeper>();
        FileHandler.readFromZooKeeperFile(file.getPath()).stream().forEach(item -> keeperList.add(item));
        ArrayList<ZooKeeper> updatedList = keeperList.stream().filter(keeper -> !keeper.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new));
        updatedList.add(zooKeeper);
        FileHandler.writeZooKeeperToFile(updatedList, file.getPath());
        ZooKeeper result = FileHandler.findZooKeeper(zooKeeper.getName(), file.getPath());
        return ResponseEntity.created(new URI("/api/zooKeeper/" + result.getName())).body(result);
    }

    @DeleteMapping("/staff/{name}")
    public ResponseEntity<?> deleteZooKeeper(@PathVariable String name) {
        log.info("Request to delete keeper: {}", name);
        FileHandler.retrieveZooKeeper(name, file.getPath());
        return ResponseEntity.ok().build();
    }
}