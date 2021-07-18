package com.example.xmlbuilding.controller;

import java.util.List;

import com.example.xmlbuilding.model.Building;
import com.example.xmlbuilding.service.BuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class BuildingController {
  @Autowired
  BuildingService service;

  @GetMapping("/all")
  public List<Building> getAllBuildings() {
    return service.findAll();
  }

  @GetMapping("/find")
  public Building getById(@RequestParam int id) {
    return service.findById(id);
  }

  @GetMapping("/add")
  public Building create(Building building) {
    return service.create(building);
  }

  @GetMapping("/delete")
  public void delete(@RequestParam int id) {
    service.delete(id);
  }
}
