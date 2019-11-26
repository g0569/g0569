package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;

/** A class for the chess piece represented by a star. */
public class StarChessPiece extends ChessPiece {
  public StarChessPiece(float x, float y, BaseGame game) {
    super(x, y, 10, 10, game);
  }
}
