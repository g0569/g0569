package com.example.g0569.utils;

import java.io.Serializable;

public interface InterchangeableBehavior extends Serializable {
  void action();
  Coordinate getCoordinate();
  void setCoordinate(Coordinate coordinate);
}
