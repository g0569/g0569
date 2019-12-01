package com.example.g0569.chessgame.model;

/** A class for the chess piece represented by a chessgame_component_star. */
class StarChessPiece extends ChessPiece {
  /**
   * Instantiates a new Star chess piece.
   *
   * @param x the x
   * @param y the y
   */
StarChessPiece(float x, float y) {
    super(x, y);
  }
  @Override
  public Integer[][] createTargetList() {
    Integer[][] target = new Integer[2][2];
    float column = this.getCoordinate().getX();
    if(column == (1 | 2)){
      target[0][0] = 3;
      target[1][0] = 4;
    }
    else {
      target[0][0] = 2;
      target[1][0] = 1;
    }
    target[0][1] = this.getCoordinate().getIntY();
    target[1][1] = this.getCoordinate().getIntY();
    return target;
  }
}
