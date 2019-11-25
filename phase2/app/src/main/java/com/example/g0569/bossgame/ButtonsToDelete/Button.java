package com.example.g0569.bossgame.ButtonsToDelete;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.NonPlayerItem;

public class Button extends NonPlayerItem {

  public float button_r;
  int red = Color.RED;
  int yellow = Color.YELLOW;
  int blue = Color.BLUE;
  int gray = Color.GRAY;
  int colorChanged;
  float x;
  float y;

  public Button(BaseGame game, float screenWidth, float screenHeight) {
    // Radius of BaseButton
    super(game);
  }

  /**
   * Draws the button, red and big
   *
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {}

  /**
   * Changes the color of the launch button
   */
  public void changeColor(){
    colorChanged ++;
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
