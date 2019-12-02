package com.example.g0569.chessgame.model;

/** A class for the chess piece represented by type6 NPC. */
class TriangleChessPiece extends ChessPiece {
  /**
   * Instantiates a new Triangle chess piece.
   *
   * @param x the x
   * @param y the y
   */
  TriangleChessPiece(float x, float y) {
    super(x, y);
  }
    // note: Triangle chess pieces can deal double damage when engaged as an attacking role.
    // this special ability is implemented in the characterAttack method of class ChessGame.
  @Override
  public Integer[][] createTargetList() {
    Integer[][] target = new Integer[1][2];
    float column = this.getCoordinate().getX();
    if (column == (1 | 2)) {// this means this chess piece is on player's side.
        // Triangle piece can only attack enemy chess piece in "frontline"
        target[0][0] = 3;
    } else {// this means this chess piece is on NPC's side.
      target[0][0] = 2;
    }
      // Triangle piece can only attack chess piece in the same row
    target[0][1] = this.getCoordinate().getIntY();
    return target;
  }
}
