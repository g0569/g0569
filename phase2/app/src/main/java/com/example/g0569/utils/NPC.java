package com.example.g0569.utils;

import com.example.g0569.base.model.Item;

import java.io.Serializable;

public class NPC extends Item implements Serializable, Observer {

  private int npcId;
  private String name;
  private int damage;
  private String power;
  private Coordinate coordinate;
  private InterchangeableBehavior behavior;
  private String difficulty;
  private String type;
  private String chessLayout;
  /** Instantiates a new NPC. */
  public NPC(String type) {
    super();
    this.type = type;
  }

  public NPC(
      int npcId,
      String name,
      int damage,
      String power,
      String difficulty,
      String type,
      String chessLayout) {
    super();
    this.npcId = npcId;
    this.name = name;
    this.damage = damage;
    this.power = power;
    this.difficulty = difficulty;
    this.type = type;
    this.chessLayout = chessLayout;
  }

  /**
   * Returns the name of the NPC
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the damage it has
   *
   * @return damage
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Returns the NPC's power/ability
   *
   * @return the NPCS power
   */
  public String getPower() {
    return power;
  }

  /**
   * Returns the type of the NPC
   *
   * @return type
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the position on the chess layout
   *
   * @return position in chessGame
   */
  public String getChessLayout() {
    return chessLayout;
  }

  /**
   * Sets it's coordinates
   *
   * @param coordinate of where it is
   */
  public void setCoordinate(Coordinate coordinate) {
    behavior.setCoordinate(coordinate);
  }

  /**
   * Returns the coordinates
   *
   * @return the coordinates of where it is
   */
  public Coordinate getCoordinate() {
    return behavior.getCoordinate();
  }

  /** Does its behavior */
  public void action() {
    behavior.action();
  }

  /**
   * Sets the behavior of the NPC
   *
   * @param behavior of the NPC
   */
  public void setBehavior(InterchangeableBehavior behavior) {
    this.behavior = behavior;
  }

  /**
   * Returns the behaviour of the NPC
   *
   * @return the behavior
   */
  public InterchangeableBehavior getBehavior() {
    return behavior;
  }

  /**
   * Update If the boss is defeated, then the game is concluded and so the damage of the NPC is
   * increased
   */
  @Override
  public void update() {
    damage++;
  }
}
