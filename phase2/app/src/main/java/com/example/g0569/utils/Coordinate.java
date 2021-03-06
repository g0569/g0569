package com.example.g0569.utils;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/** The type Coordinate. */
public class Coordinate implements Serializable {
  private float x;
  private float y;

  /**
   * Instantiates a new Coordinate.
   *
   * @param x the x
   * @param y the y
   */
  public Coordinate(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Create coordinate.
   *
   * @param x the x
   * @param y the y
   * @return the coordinate
   */
  public static Coordinate create(float x, float y) {
    return new Coordinate(x, y);
  }

  /**
   * Create coordinate.
   *
   * @param x the x
   * @param y the y
   * @return the coordinate
   */
  public static Coordinate create(int x, int y) {
    return new Coordinate(x, y);
  }
  /**
   * Gets x.
   *
   * @return the x
   */
  public float getX() {
    return x;
  }

  /**
   * Sets x.
   *
   * @param x the x
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Gets int x.
   *
   * @return the int x
   */
  public int getIntX() {
    return (int) x;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  public float getY() {
    return y;
  }

  /**
   * Sets y.
   *
   * @param y the y
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Gets int y.
   *
   * @return the int y
   */
  public int getIntY() {
    return (int) y;
  }

  /**
   * Sets xy.
   *
   * @param x the x
   * @param y the y
   */
  public void setXY(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Sets xy.
   *
   * @param coordinate the coordinate we can set xy together This method mainly provide a way we can
   *     avoid the aliasing in coordinate.
   */
  public void setXY(Coordinate coordinate) {
    this.x = coordinate.getX();
    this.y = coordinate.getY();
  }

  /**
   * Offset x and y.
   *
   * @param x the x
   * @param y the y
   */
  public void offsetXY(float x, float y) {
    this.x += x;
    this.y += y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinate that = (Coordinate) o;
    return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
  }

  /**
   * Test if a coordinate is around this coordinate by a unit
   *
   * @param o the coordinate to be test
   * @param unit the unit length
   * @return true if o is around this
   */
  public boolean around(Coordinate o, float unit) {
    if (this.equals(o)) return true;
    Coordinate that = o;
    return Math.abs(that.x - x) <= unit && Math.abs(that.y - y) <= unit;
  }

  @NonNull
  @Override
  public String toString() {
    return "(" + String.valueOf(this.x) + "," + String.valueOf(this.y) + ")";
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
