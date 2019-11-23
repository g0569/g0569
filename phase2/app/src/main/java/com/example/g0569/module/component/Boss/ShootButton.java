package com.example.g0569.module.component.Boss;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class ShootButton extends Button {

  public float button_r;

  public ShootButton(Game game, float screenWidth, float screenHeight) {
    super(game, screenWidth, screenHeight);
    button_r = screenWidth / 16;

    // Sets coordinates of the button
    x = screenWidth * 5 / 6;
    y = screenHeight - button_r * 3 / 2;
    coordinate = new Coordinate(x, y);
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.RED);
    canvas.drawCircle(coordinate.getX(), coordinate.getY(), button_r, paint);
  }

  /**
   * Returns the radius of the circle
   *
   * @return the radius
   */
  public float getR() {
    return button_r;
  }
}
