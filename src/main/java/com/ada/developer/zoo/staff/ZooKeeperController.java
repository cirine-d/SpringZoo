// package com.ada.developer.zoo.staff;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.stream.Collectors;

// @RestController
// public class ZooKeeperController {
// private ZooKeeperRepository repository;

// public ZooKeeperController(ZooKeeperRepository repository) {
// this.repository = repository;
// }

// @GetMapping("/good-keepers")
// public Collection<ZooKeeper> zooKeepers() {
// return
// repository.findAll().stream().filter(this::isGreat).collect(Collectors.toList());
// }

// private boolean isGreat(ZooKeeper keeper) {
// return !keeper.getName().equals("Budweiser") &&
// !keeper.getName().equals("Coors Light")
// && !keeper.getName().equals("PBR");
// }
// }