package com.example.xmlbuilding.model;

import java.util.Random;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

public class Building {
  @Getter
  private int id;

  @Getter
  @Setter
  @NotBlank
  private String propertyType;

  @Getter
  @Setter
  @NotBlank
  private String street;

  @Getter
  @Setter
  @NotBlank
  private String number;

  @Getter
  @Setter
  @NotBlank
  private String date;

  @Getter
  @Setter
  private int storeys = 0;

  @Getter
  @Setter
  @NotBlank
  private String owner;

  public Building() {
    var random = new Random();
    this.id = random.nextInt() & Integer.MAX_VALUE;
  }
}
