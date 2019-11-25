package com.example.g0569.base.model;

import com.example.g0569.base.model.NonPlayerItem;
import com.example.g0569.module.game.GameManager;

import java.util.ArrayList;
import java.util.List;

/** The BaseGame Manager for each game. */
public abstract class BaseGame {

  private List<NonPlayerItem> gameInventory = new ArrayList<>();
  // An array list for storing the NonPlayerItem.

  /**
   * Gets game inventory.
   *
   * @return the game inventory
   */
  List<NonPlayerItem> getGameInventory() {
    return gameInventory;
  }

  /** Pause. */
  public abstract void pause();

  /** Load. */
  public abstract void load();

  public void showStatistic() {}
}
