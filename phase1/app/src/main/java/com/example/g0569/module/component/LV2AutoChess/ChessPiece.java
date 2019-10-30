package com.example.g0569.module.component.LV2AutoChess;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class ChessPiece extends NonPlayerItem {
  //    should we implement classes for chess pieces?
  //    These classes to be the type of this instance variable?
  //    private Coordinate coordinate;
  private Coordinate coordinate;
  private String appearance;
  private boolean selected;
  //    private Coordinate coordinate;
  private int power; // The amount for the chess piece can attack in one round.
  private int health; // The amount of the hp for the chess piece.

  public ChessPiece(float x, float y, int power, int health, Game game) {
    super(game);
    //        super(new Coordinate(x,y));
    this.coordinate = new Coordinate(x, y);
    this.power = power;
    this.health = health;
  }

  //    public void change_selected(){
  //        selected = ! selected;
  //    }

  @Override
  public Coordinate getCoordinate() {
    return coordinate;
  }

  //    public void setCoordinate(float x, float y) {
  //        this.coordinate.setXY(x , y);
  //    }

  public int getPower() {
    return power;
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {}

  @Override
  public void action() {}

  //    @Override
  //    public boolean action(ChessPiece NPCchess) {
  //        return this.power >= NPCchess.power;// compare the power of two chesspieces. return
  // win(true)/lose(false).
  //    }
}
