package com.example.g0569.chessgame.model;

/** A class for the chess piece represented by a chessgame_component_triangle. */
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
  @Override
  public Integer[][] createTargetList() {
    Integer[][] target = new Integer[1][2];
    float column = this.getCoordinate().getX();
    if(column == (1 | 2)){target[0][0] = 3;}
    else {target[0][0] = 2;}
    target[0][1] = this.getCoordinate().getIntY();
    return target;
  }
}
