package com.example.g0569.module.component.Maze.MazeItem;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

public class Wall extends NonPlayerItem {

  private Bitmap appearence;

  public Wall(Game game, float x, float y) {
    super(game);
    this.coordinate.setXY(
        x * ((MazeGame) this.getGame()).getGrid_width(),
        y * ((MazeGame) this.getGame()).getGrid_height());
    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearence = BitmapFactory.decodeResource(resources, R.drawable.tile);
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.BLACK);
    canvas.drawBitmap(
            appearence,
        this.getX(),
        this.getY(),
//        this.getX() + ((MazeGame) this.getGame()).getGrid_width(),
//        this.getY() + ((MazeGame) this.getGame()).getGrid_height(),
        paint);
  }

  @Override
  public void action() {
    ;
  }
}
