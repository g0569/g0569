package com.example.g0569.base.model;

import java.util.ArrayList;
import java.util.List;

/** The BaseGame Manager for each game. */
public abstract class BaseGame {

  private List<Item> gameInventory = new ArrayList<>();
  // An array list for storing the Item.

  /**
   * Gets game inventory.
   *
   * @return the game inventory
   */
  List<Item> getGameInventory() {
    return gameInventory;
  }

  /** Pause. */
  public abstract void pause();

  /** Load. */
  public abstract void load();

  public void showStatistic() {}
}
