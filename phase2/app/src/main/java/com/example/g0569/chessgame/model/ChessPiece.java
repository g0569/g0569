package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.InterchangeableBehavior;
import com.example.g0569.utils.NPC;

import java.util.List;

/** The chess piece on the chess board. */
public abstract class ChessPiece extends Item implements InterchangeableBehavior {
  private Coordinate coordinate;

  /**
   * @param x The x coordinate for chess piece.
   * @param y The y coordinate for chess piece.
   */
  ChessPiece(float x, float y) {
    super();
    this.coordinate = new Coordinate(x, y);
  }
  public boolean matchCoordinate(Integer[] coor){
    boolean isMatch = false;
    if(this.getCoordinate().getIntX() == coor[0] && this.getCoordinate().getIntY() == coor[1]){
      isMatch = true;
    }
    return isMatch;
  }

  public abstract Integer[][] createTargetList();
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
