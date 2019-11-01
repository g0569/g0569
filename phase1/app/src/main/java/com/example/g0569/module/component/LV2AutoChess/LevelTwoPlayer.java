package com.example.g0569.module.component.LV2AutoChess;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Player;
import com.example.g0569.module.game.Game;

public class LevelTwoPlayer extends Player {

  public LevelTwoPlayer(Game game) {
    super(game);
    getInventory().add(new StarChessPiece(0, 0, game));
    getInventory().add(new TriangleChessPiece(0, 0, game));
    //        getInventory().add(new StarChessPiece(0,0));
    // Put three chess piece into the getInventory() area.
  }


  @Override
  public void draw(Canvas canvas, Paint paint) {}

  @Override
  public void action() {}
}
