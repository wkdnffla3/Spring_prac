package com.example.basic.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Computer {
  String cpu;
  int power;
  int ram;
  int ssd;
}
