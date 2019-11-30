package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.InterchangeableBehavior;

/** The chess piece on the chess board. */
public class ChessPiece extends Item implements InterchangeableBehavior {
  private Coordinate coordinate;
  private int damage; // The amount for the chess piece can attack in one round.

  /**
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   * @param damage The damage of this chess piece.
   */
  ChessPiece(float x, float y, int damage) {
    super();
    this.coordinate = new Coordinate(x, y);
    this.damage = damage;
  }

  /**
   * A getter for variable damage.
   *
   * @return damage
   */
  int getDamage() {
    return damage;
  }

  @Override
  public Coordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }


  @Override
  public void update() {

  }

  @Override
  public void action() {
  }
}
