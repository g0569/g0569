package com.example.g0569.utils;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.mazegame.model.MazeGame;

public abstract class NPC extends Item {
  /**
   * the same NPC in diff levels can have diff type (to have diff behavior) but same name(to have same view)
   */
  private String name;
  private int damage;
  private String power;
  private Coordinate throwDirection;
  private DoBehavior behavior;
  private String type;
  /**
   * Instantiates a new Item.
   *
   * @param game the game it corresponding to
   */
  public NPC(BaseGame game, String Name, String type) {
    super(game);
    this.name = Name;
    this.type = type;
//    this.behavior = behave;
  }

  public void setBehavior(DoBehavior behavior) {
    this.behavior = behavior;
  }

  public String getType() {
    return type;
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

  // TODO: 2019-11-28 return a NPC instance of the name type
  public void getNPCType() {
    ; }
  public Coordinate getThrowDirection() {
    return throwDirection;
  }

  /** Update */
  @Override
  public void update() {}

  public interface DoBehavior {
    void action();
  }

}
