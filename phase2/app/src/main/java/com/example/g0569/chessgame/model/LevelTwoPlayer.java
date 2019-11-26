package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Player;

/** The player in level two. */
public class LevelTwoPlayer extends Player {

  /**
   * Initialize a player in level two.
   *
   * @param game the current game.
   */
  public LevelTwoPlayer(BaseGame game) {
    super(game);
    getInventory().add(new StarChessPiece(0, 0, game));
    getInventory().add(new TriangleChessPiece(0, 0, game));
    //        getInventory().add(new StarChessPiece(0,0));
    // Put three chess piece into the getInventory() area.
  }

  @Override
  public void action() {}
}
