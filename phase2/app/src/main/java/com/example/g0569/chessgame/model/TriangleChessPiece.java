package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;

/** A class for the chess piece represented by a triangle. */
public class TriangleChessPiece extends ChessPiece {
  public TriangleChessPiece(float x, float y, BaseGame game) {
    super(x, y, 5, 15, game);
  }
}
