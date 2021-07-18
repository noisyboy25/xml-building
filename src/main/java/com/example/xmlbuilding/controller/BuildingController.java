package com.example.xmlbuilding.controller;

import java.io.IOException;
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
  public List<Building> getAllBuildings() throws IOException {
    return service.findAll();
  }

  @GetMapping("/find")
  public Building getById(@RequestParam int id) throws IOException {
    return service.findById(id);
  }

  @GetMapping("/add")
  public Building create(Building building) throws IOException {
    return service.create(building);
  }

  @GetMapping("/delete")
  public void delete(@RequestParam int id) throws IOException {
    service.delete(id);
  }
}
