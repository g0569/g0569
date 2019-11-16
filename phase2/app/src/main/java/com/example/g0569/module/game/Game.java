package com.example.g0569.module.game;

import com.example.g0569.module.component.NonPlayerItem;

import java.util.ArrayList;
import java.util.List;

/** The Game Manager for each game. */
public abstract class Game {

  private GameManager gameManager;
  private List<NonPlayerItem> gameInventory = new ArrayList<>();
  // An array list for storing the NonPlayerItem.

  /**
   * Instantiates a new Game.
   *
   * @param gameManager the game manager
   */
  public Game(GameManager gameManager) {
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
