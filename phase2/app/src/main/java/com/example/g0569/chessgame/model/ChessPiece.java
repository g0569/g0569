package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.InterchangeableBehavior;

/** The chess piece on the chess board. */
public abstract class ChessPiece extends Item implements InterchangeableBehavior {
  private Coordinate coordinate;
  private Coordinate coordinateBackUp; // BackUp for reset the chess piece.

  /**
   * Instantiates a new Chess piece.
   *
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   */
  ChessPiece(float x, float y) {
    super();
    this.coordinate = Coordinate.create(x, y);
    this.coordinateBackUp = Coordinate.create(x, y);
  }
  /**
   * Match coordinate boolean. // TODO Add description here.
   *
   * @param coordinateArray the coordinateArray
   * @return the boolean
   */
  boolean matchCoordinate(Integer[] coordinateArray) {
    boolean isMatch = false;
    if (this.getCoordinate().getIntX() == coordinateArray[0]
        && this.getCoordinate().getIntY() == coordinateArray[1]) {
      isMatch = true;
    }
    return isMatch;
  }

  /**
   * Create target list integer [ ] [ ]. TODO add description here
   *
   * @return the integer [ ] [ ]
   */
  public abstract Integer[][] createTargetList();

  @Override
  public Coordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  /** Reset coordinate. */
  void resetCoordinate() {
    this.coordinate.setXY(coordinateBackUp.getX(), coordinateBackUp.getY());
  }

  @Override
  public void update() {}

  @Override
  public void action() {}
}
