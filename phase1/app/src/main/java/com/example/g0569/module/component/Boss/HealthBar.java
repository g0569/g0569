package com.example.g0569.module.component.Boss;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;

public class HealthBar extends NonPlayerItem {

  private float screenWidth;
  private float screenHeight;

  public HealthBar(Game game, float screenWidth, float screenHeight) {
    super(game);
    screenHeight = game.getGameManager().getScreen_height();
    screenWidth = game.getGameManager().getScreen_width();
  }

  public void draw(Canvas canvas, Paint paint, int health) {
    // Draws the healthbar rectangle
    // Should be able to fill the color in based on it's health
    //        canvas.drawRect();
  }

  public void draw(Canvas canvas, Paint paint) {}

  @Override
  public void action() {
    //      Should use the Enemy Boss's health
    // Should call draw to draw the new health bar

  }
}
