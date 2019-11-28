package com.example.g0569.chessgame.model;

/** A factory for making chessPiece instance. */
class ChessPieceFactory {
  ChessPiece getChessPiece(float x, float y, ChessGame game, String type) {
    if (type == null) {
      return null;
    }
    if (type.equals("chessgame_component_star")) {
      return new StarChessPiece(x, y, game);
    } else if (type.equals("chessgame_component_triangle")) {
      return new TriangleChessPiece(x, y, game);
    }
    return null;
  }
}
