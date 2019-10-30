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
    this.coordinate = new Coordinate(x, y);
    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearence = BitmapFactory.decodeResource(resources, R.drawable.tile);
    appearence =
        Bitmap.createScaledBitmap(
            appearence,
            (int) ((MazeGame) this.getGame()).getGrid_width(),
            (int) ((MazeGame) this.getGame()).getGrid_height(),
            false);
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.WHITE);

    canvas.drawBitmap(
        appearence,
        this.getX() * ((MazeGame) this.getGame()).getGrid_width(),
        this.getY() * ((MazeGame) this.getGame()).getGrid_height(),
        paint);
  }

  @Override
  public void action() {
    ;
  }
}
