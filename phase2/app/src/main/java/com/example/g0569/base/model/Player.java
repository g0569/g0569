package com.example.g0569.base.model;

import java.util.ArrayList;
import java.util.List;

/** The item that is controlled by user. */
public abstract class Player extends Item {

  private List<Item> inventory = new ArrayList<>();

  /**
   * Instantiates a new Player.
   *
   * @param game the game
   */
  public Player(BaseGame game) {
    super(game);
  }

  public abstract void update();

  /**
   * Gets inventory.
   *
   * @return the inventory
   */
  public List<Item> getInventory() {
    return inventory;
  }
}
