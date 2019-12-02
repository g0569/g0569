package com.example.g0569.bossgame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Observable;
import com.example.g0569.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Item implements Observable {

  private List<Observer> observers = new ArrayList<>();
  // The health and initial health of the Enemy
  private float health;
  private float initialHealth;
  private boolean resistFire;
  private boolean resistWater;

  // The direction it moves
  private int xDirection;
  private int counterToUpdate;

  private boolean defeated;

  Enemy() {
    super();

    // Sets the health of the boss
    health = 100;
    initialHealth = health;
  }

  /** Moves the Boss around based on its position */
  void action() {
    if (counterToUpdate == 100) {
      double d = Math.random();
      if (d > 0.7) {
        resistFire = true;
        resistWater = false;
      } else if (d > 0.5) {
        resistFire = false;
        resistWater = false;
      } else if (d > 0.3) {
        resistFire = false;
        resistWater = true;
      } else {
        resistFire = false;
        resistWater = false;
      }
      counterToUpdate = 0;
    } else {
      counterToUpdate++;
    }
  }

  /** Decreases the health of the enemy when Item hits it */
  void attacked(int damageTaken, String attackType) {
    if (resistFire && attackType.equals("fire")) {
      health -= damageTaken / 2;
    } else if (resistWater && attackType.equals("ice")) {
      health -= damageTaken / 2;
    } else {
      health -= damageTaken;
    }
    health = Math.max(0.0f, health);
  }

  String getResist() {
    if (resistWater) {
      return "ice";
    } else if (resistFire) {
      return "fire";
    } else {
      return null;
    }
  }

  /**
   * Sets the direction x factor of the enemy depending on the screen size of the android device
   * being used.
   *
   * @param directionMovement the screen size and based on that decides amount of movmement
   */
  void setxDirection(int directionMovement) {
    this.xDirection = directionMovement / 100;
  }

  /**
   * Returns the amount it moves in the x direction
   *
   * @return movement in x direction
   */
  int getXDirection() {
    return this.xDirection;
  }

  /**
   * a Getter for the health
   *
   * @return the current health
   */
  float getHealth() {
    return health;
  }

  /**
   * Returns the initalHealth
   *
   * @return the initial Health
   */
  float getInitialHealth() {
    return initialHealth;
  }

  @Override
  public boolean getState() {
    return defeated;
  }

  public void setState(boolean defeated) {
    this.defeated = defeated;
    if (defeated) notifyAllObservers();
  }

  @Override
  public void attach(Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  @Override
  public void update() {}
}
