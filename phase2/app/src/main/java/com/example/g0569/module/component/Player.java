package com.example.g0569.module.component;

import com.example.g0569.module.game.Game;

import java.util.ArrayList;
import java.util.List;

/** The item that is controlled by user. */
public abstract class Player extends Item {

  private List<NonPlayerItem> inventory = new ArrayList<>();

  /**
   * Instantiates a new Player.
   *
   * @param game the game
   */
  public Player(Game game) {
    super(game);
  }

  public abstract void action();

  /**
   * Gets inventory.
   *
   * @return the inventory
   */
  public List<NonPlayerItem> getInventory() {
    return inventory;
  }
}
