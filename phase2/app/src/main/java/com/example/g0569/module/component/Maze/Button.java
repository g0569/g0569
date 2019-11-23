package com.example.g0569.module.component.Maze;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.module.component.Item;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class Button extends Item {
  private Bitmap appearence;
  private int unitX;
  private int unitY;

  public Button(Game game) {
    super(game);
    this.unitX = (int) (this.getGame().getGameManager().getScreenWidth() * 0.13 / 3);
    this.unitY = (int) (this.getGame().getGameManager().getScreenHeight() * 0.13 / 3);
    this.coordinate = new Coordinate(16, 16);
    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearence = BitmapFactory.decodeResource(resources, R.drawable.move_button);
    appearence =
        Bitmap.createScaledBitmap(
            appearence,
            (int) ((this.getGame()).getGameManager().getScreenWidth() * 0.13),
            (int) ((this.getGame()).getGameManager().getScreenHeight() * 0.13),
            false);
  }
  /**
   * Draws the button, red and big
   *
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    float screenWidth = this.getGame().getGameManager().getScreenWidth();
    float screenHeight = this.getGame().getGameManager().getScreenHeight();
    paint.setColor(Color.WHITE);
    canvas.drawBitmap(appearence, screenWidth - 4 * unitX, screenHeight - 4 * unitY, paint);
  }

  /** Update */
  @Override
  public void action() {}
}
