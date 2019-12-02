package com.example.g0569.chessgame.model;

/** A class for the chess piece represented by type4 NPC */
public class SquareChessPiece extends ChessPiece {
  /**
   * Instantiates a new Square chess piece.
   *
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   */
SquareChessPiece(float x, float y) {
    super(x, y);
  }

  @Override
  public Integer[][] createTargetList() {
    Integer[][] target = new Integer[3][2];
    float column = this.getCoordinate().getX();
    if (column == (1 | 2)) { // this means this chess piece is on player's side.
      // Square piece can only attack enemy chess piece in the "backLine"
      target[0][0] = 4;
      target[1][0] = 4;
      target[2][0] = 4;
    } else { // this means this chess piece is on the side of NPC.
      target[0][0] = 1;
      target[1][0] = 1;
      target[2][0] = 1;
    }
    // Square piece can attack enemy chess piece in any row,
    target[0][1] = 1;
    target[1][1] = 2;
    target[2][1] = 3;
    return target;
  }
}
