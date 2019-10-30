package com.example.g0569.module.utils;

import java.util.Objects;

public class Coordinate {
  private float x;
  private float y;

  public Coordinate(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setXY(float x, float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinate that = (Coordinate) o;
    return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
  }

  public boolean around(Object o, float unit) {
    if (this.equals(o)) return true;
    Coordinate that = (Coordinate) o;
    return Math.abs(that.x - x) <= unit && Math.abs(that.y - y) <= unit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
