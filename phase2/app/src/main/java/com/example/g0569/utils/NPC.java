package com.example.g0569.utils;

import com.example.g0569.base.model.Item;

import java.io.Serializable;

public class NPC extends Item implements Serializable {

  private int npcId;
  private String name;
  private int damage;
  private String power;
  private Coordinate coordinate;
  private InterchangeableBehavior behavior;
  private String difficulty;
  private String type;
  private String chessLayout;
  /**
   * Instantiates a new Item.
   */
  public NPC(String Name) {
    super();
    this.name = Name;
  }

  public NPC(int npcId, String name, int damage, String power, String difficulty, String type, String chessLayout) {
    super();
    this.npcId = npcId;
    this.name = name;
    this.damage = damage;
    this.power = power;
    this.difficulty = difficulty;
    this.type = type;
    this.chessLayout = chessLayout;
  }

  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }

  public String getPower() {
    return power;
  }

  public String getType() {
    return type;
  }

  public String getChessLayout() {
    return chessLayout;
  }

  public Coordinate getCoordinate() {
    return behavior.getCoordinate();
  }

  public void action(){
    behavior.action();
  }

  public void setBehavior(InterchangeableBehavior behavior) {
    this.behavior = behavior;
  }

  /** Update */
  @Override
  public void update() {}

}

