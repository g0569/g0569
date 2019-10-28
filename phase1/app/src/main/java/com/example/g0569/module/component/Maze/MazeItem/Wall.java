package com.example.g0569.module.component.Maze.MazeItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

public class Wall extends NonPlayerItem {
  public Wall(float x, float y) {
    this.coordinate.setXY(
        x * ((MazeGame) this.getManager()).getGrid_width(),
        y * ((MazeGame) this.getManager()).getGrid_height());
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.BLACK);
    canvas.drawRect(
        this.getX(),
        this.getY(),
        this.getX() + ((MazeGame) this.getManager()).getGrid_width(),
        this.getY() + ((MazeGame) this.getManager()).getGrid_height(),
        paint);
  }

  @Override
  public void action() {
    ;
  }
}
