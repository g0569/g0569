package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;

/** A class for the chess piece represented by a chessgame_component_star. */
class StarChessPiece extends ChessPiece {
  StarChessPiece(float x, float y, BaseGame game) {
    super(x, y, 10, 10, game);
  }
}
