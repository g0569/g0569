package com.example.g0569.bossgame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.base.model.NonPlayerItem;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

public class HealthBar extends NonPlayerItem implements Observer{

  private Observable observable;
  // The screenWidth and Screen Height
  private float screenWidth;
  private float screenHeight;

  // The rectangles for the bar to be drawn
  private Rect dest_rect;

  // The image of the bar
  private Bitmap healthbar;

  // The rectangle for the red bar which represents health
  private Rect healthRect;

  public HealthBar(BaseGame game, Resources resources, Observable observable) {
    super(game);
    // Sets the height and width
    screenHeight = game.getGameManager().getScreenHeight();
    screenWidth = game.getGameManager().getScreenWidth();

    this.observable = observable;
    observable.attach(this);
    // Sets the size of the bar
    size = (int) screenWidth / 5;

    // Sets the coordinate of the bar
    float x = (int) (screenWidth / 2 - size / 2);
    float y = (int) (screenHeight - screenWidth * 3 / 32 - 2.5 * size);
    coordinate = new Coordinate(x, y);

    // Sets the appearance of the empty bar
    appearance = BitmapFactory.decodeResource(resources, R.drawable.bar);

    // Sets the rectangle of the empty bar
    dest_rect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);

    // Sets the rectangle of the red bar
    healthRect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);

    // Sets the appearance of the red bar
    healthbar = BitmapFactory.decodeResource(resources, R.drawable.redbar);
  }

  /**
   * Draws the bar as well as the red bar
   *
   * @param canvas of the bar and red bar
   * @param paint the style of them
   */
  public void draw(Canvas canvas, Paint paint) {
    canvas.drawBitmap(appearance, null, dest_rect, paint);

    canvas.drawBitmap(healthbar, null, healthRect, paint);
  }

  /**
   * Determines how much to change the rectangle to draw remaining health by
   *
   * @param healthRemaining the health left
   * @param totalHealth the health it started off with
   */
  public void action(int healthRemaining, int totalHealth) {
    int health = determineHealth(healthRemaining, totalHealth);
    healthRect.right = healthRect.right - health;
  }

  public void action() {}

  /**
   * Determines how much health the boss should have left based on the ratio of his health with the
   * bar length
   *
   * @param health of the boss
   * @param totalHealth of the boss
   * @return the health we should take away
   */
  private int determineHealth(int health, int totalHealth) {
    // Determine the health of the bar relative to the health and totalHealth of the Boss
    int totalBar = getSize();
    int healthToDraw = health * totalBar / totalHealth;
    return totalBar - healthToDraw;
  }

  @Override
  public void update() {
    action(observable.getState(), observable.getInitialState());
  }
}
