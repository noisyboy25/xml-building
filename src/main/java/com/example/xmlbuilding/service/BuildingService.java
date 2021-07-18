package com.example.xmlbuilding.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.xmlbuilding.model.Building;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Service;

@Service
public class BuildingService {
  private final File file = new File("database.xml");
  private final XmlMapper mapper = new XmlMapper();
  private final TypeReference<List<Building>> type = new TypeReference<>() {
  };

  private void createDatabase() {
    List<Building> buildings = new ArrayList<>();

    try {
      mapper.writeValue(file, buildings);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleIOException() {
    createDatabase();
  }

  public List<Building> findAll() {
    if (!file.exists()) {
      createDatabase();
    }

    List<Building> buildings = new ArrayList<>();

    try {
      buildings = mapper.readValue(file, type);
    } catch (IOException e) {
      e.printStackTrace();
      handleIOException();
    }

    return buildings;
  }

  public Building findById(int id) {
    if (!file.exists()) {
      createDatabase();
    }

    try {
      List<Building> buildings = mapper.readValue(file, type);

      for (var building : buildings) {
        if (building.getId() == id)
          return building;
      }
    } catch (IOException e) {
      e.printStackTrace();
      handleIOException();
    }

    return null;
  }

  public Building create(Building building) {
    if (!file.exists()) {
      createDatabase();
    }

    try {
      List<Building> buildings = mapper.readValue(file, type);
      buildings.add(building);
      mapper.writeValue(file, buildings);
      return building;
    } catch (IOException e) {
      e.printStackTrace();
      handleIOException();
    }

    return null;
  }

  public void delete(int id) {
    if (!file.exists()) {
      createDatabase();
    }

    try {
      List<Building> buildings = mapper.readValue(file, type);
      buildings.removeIf(building -> building.getId() == id);
      mapper.writeValue(file, buildings);
    } catch (IOException e) {
      e.printStackTrace();
      handleIOException();
    }
  }
}
