package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Coordinate;

/** The chess piece on the chess board. */
public class ChessPiece extends Item {
  private Coordinate coordinate;
  private String appearance;
  private int power; // The amount for the chess piece can attack in one round.
  private int health; // The amount of the hp for the chess piece.

  /**
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   * @param power The power of this chess piece.
   * @param health The health of this chess piece.
   * @param game Current game.
   */
  ChessPiece(float x, float y, int power, int health, BaseGame game) {
    super(game);
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

  @Override
  public void action() {}
}
