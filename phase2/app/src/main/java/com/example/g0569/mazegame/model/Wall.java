package com.example.g0569.mazegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.NonPlayerItem;
import com.example.g0569.utils.Coordinate;

public class Wall extends NonPlayerItem {

  private Bitmap appearence;

  Wall(BaseGame game, float x, float y) {
    super(game);
    this.coordinate = new Coordinate(x, y);
    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearence = BitmapFactory.decodeResource(resources, R.drawable.tile);
    appearence =
        Bitmap.createScaledBitmap(
            appearence,
            (int) ((MazeGame) this.getGame()).getGridWidth(),
            (int) ((MazeGame) this.getGame()).getGridHeight(),
            false);
  }

  /**
   * Draw the bitmap wall
   *
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.WHITE);

    canvas.drawBitmap(
        appearence,
        this.getX() * ((MazeGame) this.getGame()).getGridWidth(),
        this.getY() * ((MazeGame) this.getGame()).getGridHeight(),
        paint);
  }

  @Override
  public void action() {
  }
}
