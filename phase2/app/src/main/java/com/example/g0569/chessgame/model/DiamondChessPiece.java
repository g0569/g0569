package com.example.g0569.chessgame.model;

/** A class for the chess piece represented by type2 NPC */
public class DiamondChessPiece extends ChessPiece {
  /**
   * Instantiates a new Diamond chess piece.
   *
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   */
  DiamondChessPiece(float x, float y) {
    super(x, y);
  }

  @Override
  public Integer[][] createTargetList() {
    Integer[][] target = new Integer[1][2];
    float column = this.getCoordinate().getX();
    if (column == (1 | 2)) {
      target[0][0] = 3;
    } else {
      target[0][0] = 2;
    }
    target[0][1] = this.getCoordinate().getIntY();
    return target;
  }
}
