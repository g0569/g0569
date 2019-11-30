package com.example.g0569.chessgame.model;

import com.example.g0569.utils.Constants;

/** A factory for making chessPiece instance. */
class ChessPieceFactory {
  ChessPiece getChessPiece(float x, float y, String type) {
    if (type.equals(Constants.STAR_TYPE)) {
      return new StarChessPiece(x, y);
    } else if (type.equals(Constants.TRIANGLE_TYPE)) {
      return new TriangleChessPiece(x, y);
    } else if (type.equals(Constants.CIRCLE_TYPE)) {
      return new CirecleChessPiece(x, y);
    } else if (type.equals(Constants.DIAMOND_TYPE)) {
      return new DiamondChessPiece(x, y);
    } else if (type.equals(Constants.HEART_TYPE)) {
      return new HeartChessPiece(x, y);
    } else {
      return new SquareChessPiece(x, y);
    }
  }
}
