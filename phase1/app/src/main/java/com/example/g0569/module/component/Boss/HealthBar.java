package com.example.g0569.module.component.Boss;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class HealthBar extends NonPlayerItem {

  private float screenWidth;
  private float screenHeight;
  private Rect src_rect;
  private Rect dest_rect;
  private Bitmap healthbar;

  public HealthBar(Game game, float screenWidth, float screenHeight, Resources resources) {
    super(game);
    screenHeight = game.getGameManager().getScreen_height();
    screenWidth = game.getGameManager().getScreen_width();
    size = (int) screenWidth / 5;
    float x = (int) (screenWidth / 2 - size / 2);
    float y = (int) (screenHeight - screenWidth * 3 / 32 - 2.5 * size);
    coordinate = new Coordinate(x, y);
    appearance = BitmapFactory.decodeResource(resources, R.drawable.bar);
    src_rect = new Rect(0, 0, appearance.getWidth() + size/2, appearance.getHeight());
    dest_rect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);
    healthbar = BitmapFactory.decodeResource(resources, R.drawable.redbar);
  }

  public void draw(Canvas canvas, Paint paint) {
    canvas.drawBitmap(appearance, src_rect, dest_rect, paint);
    canvas.drawBitmap(healthbar, src_rect, dest_rect, paint);

  }

  @Override
  public void action() {

    //      Should use the Enemy Boss's health
    // Should call draw to draw the new health bar

  }
}
