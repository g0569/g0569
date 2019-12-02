package com.example.g0569.chessgame.model;

/** A class for the chess piece represented by type3 NPC */
public class HeartChessPiece extends ChessPiece {
  /**
   * Instantiates a new Heart chess piece.
   *
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   */
  HeartChessPiece(float x, float y) {
    super(x, y);
  }

  @Override
  public Integer[][] createTargetList() {
    Integer[][] target = new Integer[3][2];
    float column = this.getCoordinate().getX();
    if (column == (1 | 2)) {
      target[0][0] = 3;
      target[1][0] = 3;
      target[2][0] = 3;
    } else {
      target[0][0] = 2;
      target[1][0] = 2;
      target[2][0] = 2;
    }
    target[0][1] = 1;
    target[1][1] = 2;
    target[2][1] = 3;
    return target;
  }
}
