package com.example.g0569.module.component.Boss;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class Button extends NonPlayerItem {

  private float button_r;

  public Button(Game game, float screenWidth, float screenHeight) {
    // Radius of Button
    super(game);
    button_r = screenWidth / 16;

    // Sets coordinates of the button
    float x = screenWidth * 5 / 6;
    float y = screenHeight - button_r * 3 / 2;
    coordinate = new Coordinate(x, y);
  }

  /**
   * Draws the button, red and big
   *
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
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
  // Not needed right now so not sure what to do with it
  @Override
  public void action() {}
}
