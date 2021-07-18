package com.example.xmlbuilding.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.xmlbuilding.model.Building;
import com.example.xmlbuilding.service.BuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping(value = "/buildings", produces = { MediaType.APPLICATION_JSON_VALUE })
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
  public Building create(@Valid Building building, BindingResult result, HttpServletResponse response)
      throws IOException {
    if (result.hasErrors()) {
      response.sendRedirect("/");
      return null;
    }

    return service.create(building);
  }

  @GetMapping("/delete")
  public void delete(@RequestParam int id) {
    service.delete(id);
  }

  @GetMapping("/count")
  public int count() {
    return service.findAll().size();
  }
}
