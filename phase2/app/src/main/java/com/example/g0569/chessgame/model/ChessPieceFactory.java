package com.example.g0569.chessgame.model;

import com.example.g0569.utils.Constants;

/** A factory for making chessPiece instance. */
class ChessPieceFactory {
  /**
   * Gets chess piece.
   *
   * @param x the x
   * @param y the y
   * @param type the type
   * @return the chess piece
   */
  ChessPiece getChessPiece(float x, float y, String type) {
    switch (type) {
      case Constants.STAR_TYPE:
        return new StarChessPiece(x, y);
      case Constants.TRIANGLE_TYPE:
        return new TriangleChessPiece(x, y);
      case Constants.CIRCLE_TYPE:
        return new CircleChessPiece(x, y);
      case Constants.DIAMOND_TYPE:
        return new DiamondChessPiece(x, y);
      case Constants.HEART_TYPE:
        return new HeartChessPiece(x, y);
      default:
        return new SquareChessPiece(x, y);
    }
  }
}
