package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.InterchangeableBehavior;

/** The chess piece on the chess board. */
public class ChessPiece extends Item implements InterchangeableBehavior {
  private Coordinate coordinate;
  private String appearance;
  private int power; // The amount for the chess piece can attack in one round.
  private int health; // The amount of the hp for the chess piece.

  /**
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   * @param power The power of this chess piece.
   * @param health The health of this chess piece.
   */
  ChessPiece(float x, float y, int power, int health) {
    super();
    this.coordinate = new Coordinate(x, y);
    this.power = power;
    this.health = health;
  }

  /**
   * A getter for variable power.
   *
   * @return power
   */
  public int getPower() {
    return power;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }


  @Override
  public void update() {

  }

  @Override
  public void action() {
  }
}
