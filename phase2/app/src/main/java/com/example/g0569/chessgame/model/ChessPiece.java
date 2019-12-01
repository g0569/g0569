package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.InterchangeableBehavior;

/** The chess piece on the chess board. */
public class ChessPiece extends Item implements InterchangeableBehavior {
  private Coordinate coordinate;
  private Coordinate coordinateBackUp;

  /**
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   */
  ChessPiece(float x, float y) {
    super();
    this.coordinate = new Coordinate(x, y);
    this.coordinateBackUp = new Coordinate(x, y);
  }

  @Override
  public Coordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  void resetCoordinate() {
    this.coordinate.setXY(coordinateBackUp.getX(), coordinateBackUp.getY());
  }

  @Override
  public void update() {}

  @Override
  public void action() {}
}
