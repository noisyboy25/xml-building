package com.example.xmlbuilding.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.xmlbuilding.model.Building;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class MainController {
  List<Building> buildings = new ArrayList<>();

  @GetMapping("/all")
  public List<Building> getAllBuildings() {
    return buildings;
  }

  @GetMapping("/find")
  public Building getById(@RequestParam int id) {
    for (var building : buildings) {
      if (building.getId() == id)
        return building;
    }

    return null;
  }

  @GetMapping("/add")
  public Building create(Building building) {
    buildings.add(building);
    return building;
  }

  @GetMapping("/delete")
  public Building delete(@RequestParam int id) {
    buildings.removeIf(building -> building.getId() == id);
    return null;
  }
}
