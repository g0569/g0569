package com.example.g0569.module.component;

import com.example.g0569.module.game.Game;

/** The item that is not controlled by the User. */
public abstract class NonPlayerItem extends Item {

  /**
   * Instantiates a new NonplayerItem.
   *
   * @param game the game it corresponding to
   */
  public NonPlayerItem(Game game) {
    super(game);
  }
}
