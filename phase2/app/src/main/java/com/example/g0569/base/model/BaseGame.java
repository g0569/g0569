package com.example.g0569.base.model;

import com.example.g0569.base.model.NonPlayerItem;
import com.example.g0569.module.game.GameManager;

import java.util.ArrayList;
import java.util.List;

/** The BaseGame Manager for each game. */
public abstract class BaseGame {

  private GameManager gameManager;
  private List<NonPlayerItem> gameInventory = new ArrayList<>();
  // An array list for storing the NonPlayerItem.

  /**
   * Instantiates a new BaseGame.
   *
   * @param gameManager the game manager
   */
  public BaseGame(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  /**
   * Gets game manager.
   *
   * @return the game manager
   */
  public GameManager getGameManager() {
    return gameManager;
  }

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
