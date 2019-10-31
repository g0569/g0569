package com.example.g0569.module.game;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.NonPlayerItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

  private GameManager gameManager;
  private List<NonPlayerItem> gameInventory = new ArrayList<>();
  // An array list for storing the NonPlayerItem.

  public Game(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  public GameManager getGameManager() {
    return gameManager;
  }

  List<NonPlayerItem> getGameInventory() {
    return gameInventory;
  }

  public abstract void pause();

  public abstract void load();
}
