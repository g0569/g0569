package com.example.g0569.bossgame.model;

import com.example.g0569.base.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Item implements Observable {

  private List<Observer> observers = new ArrayList<Observer>();
  // The health and initial health of the Enemy
  private float health;
  private float initialHealth;
  private boolean resistFire;
  private boolean resistWater;
  private boolean resistLightning;

  // The direction it moves
  private int xDirection;
  private int yDirection;
  private float ratioOfHealth;
  private int counterToUpdate;

  public Enemy() {
    super();

    // Sets the direction of the Enemy
    //    xDirection = (int) this.screenWidth / 100;
    yDirection = 0;

    // Sets the health of the boss
    health = 100;
    initialHealth = health;
  }

  /** Moves the Boss around based on its position */
  public void action() {
    if (counterToUpdate == 100) {
      double d = Math.random();
      if (d > 0.7) {
        resistFire = true;
        resistLightning = false;
        resistWater = false;
      } else if (d > 0.5) {
        resistFire = false;
        resistLightning = true;
        resistWater = false;
      } else if (d > 0.3) {
        resistFire = false;
        resistLightning = false;
        resistWater = true;
      } else {
        resistFire = false;
        resistLightning = false;
        resistWater = false;
      }
      counterToUpdate = 0;
    } else{
      counterToUpdate ++;
    }
    //    if (health > 0) {
    //      if (coordinate.getX() <= 0) {
    //        xDirection = Math.abs(xDirection);
    //        appearance = BitmapFactory.decodeResource(resource, R.drawable.bossgame_enemy_r);
    //      } else if (coordinate.getX() >= screen_width - size) {
    //        xDirection = -Math.abs(xDirection);
    //        appearance = BitmapFactory.decodeResource(resource, R.drawable.bossgame_enemy_l);
    //      }
    //      //        action();
    //    }
  }

  /**
   * Draws the Enemy
   *
   * @param canvas of the Enemy being drawn on
   * @param paint the style of the enemy
   */

  /** Decreases the health of the enemy when Item hits it */
  public void attacked(int damageTaken, String attackType) {
    if (resistFire && attackType.equals("fire")) {
      health -= damageTaken / 2;
    } else if (resistWater && attackType.equals("ice")) {
      health -= damageTaken / 2;
    } else {
      health -= damageTaken;
      setState((int) (health - damageTaken));
    }

  }

  public String getResist(){
    if (resistWater){
      return "ice";
    }else if(resistFire){
      return "fire";
    }else{
      return null;
    }
  }

  //  public boolean isAttacked(float coordinateX, float coordinateY) {
  ////    if ((getCoordinate().getX() < coordinateX && coordinateX < destRect.right)
  ////        && (getCoordinate().getY() < coordinateY && coordinateY < destRect.bottom)) {
  ////      //      System.out.println("I'm hit");
  ////      return true;
  ////    }
  //    return false;
  //  }
  public void setxDirection(int directionMovement) {
    this.xDirection = directionMovement / 100;
  }

  public int getXDirection() {
    return this.xDirection;
  }

  /**a
   * Getter for the health
   *
   * @return the current health
   */
  public float getHealth() {
    return health;
  }

  /**
   * Returns the initalHealth
   *
   * @return the initial Health
   */
  public float getInitialHealth() {
    return initialHealth;
  }

  @Override
  public int getState() {
    return (int) getHealth();
  }

  @Override
  public void setState(int state) {

    health = state;
    notifyAllObservers();
  }

  @Override
  public void attach(Observer observer) {
    this.observers.add(observer);
  }

  public int getInitialState() {
    return (int) getInitialHealth();
  }

  @Override
  public void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  @Override
  public void update() {}

  public float getRatioOfHealth(){
    ratioOfHealth = health/initialHealth;
    return ratioOfHealth;
  }
}

