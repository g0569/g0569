package com.example.g0569.module.component.Boss;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class PauseButton extends Button {

  private float width;
  private float height;

  public PauseButton(Game game, float screenWidth, float screenHeight) {
    super(game, screenWidth, screenHeight);
    x = screenWidth / 12;
    y = screenHeight - screenWidth / 8;
    coordinate = new Coordinate(x, y);
    width = screenWidth / 8;
    height = screenWidth / 32;
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.WHITE);

    //    paint.setColor(Color.RED);
    canvas.drawRoundRect(
        coordinate.getX(),
        coordinate.getY(),
        coordinate.getX() + width,
        coordinate.getY() + height,
        height / 4,
        height / 4,
        paint);
      paint.setColor(Color.BLACK);
      paint.setTextSize(50);
      float textWidth = paint.measureText("Pause");
      canvas.drawText(
              "Pause",
              coordinate.getX() + (width - textWidth) / 2,
              coordinate.getY() + (height + 50) / 2,
              paint);
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }
}
