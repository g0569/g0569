package com.example.g0569.bossgame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

public class HealthBar extends Item implements Observer {

  private Observable observable;
  // The screenWidth and Screen Height
  private float screenWidth;
  private float screenHeight;

  // The rectangles for the bossgame_component_bar to be drawn
  private Rect dest_rect;

  // The image of the bossgame_component_bar
  private Bitmap healthbar;

  // The rectangle for the red bossgame_component_bar which represents health
  private Rect healthRect;
  private int total;
  private int ratioOfHealth;

  public HealthBar(BaseGame game, Observable observable) {
    super(game);
    // Sets the height and width
    //    screenHeight = game.getGameManager().getScreenHeight();
    //    screenWidth = game.getGameManager().getScreenWidth();

    this.observable = observable;
    observable.attach(this);
    // Sets the size of the bossgame_component_bar
    size = (int) screenWidth / 5;

    // Sets the coordinate of the bossgame_component_bar
    //    float x = (int) (screenWidth / 2 - size / 2);
    //    float y = (int) (screenHeight - screenWidth * 3 / 32 - 2.5 * size);
    //    coordinate = new Coordinate(x, y);

    // Sets the appearance of the empty bossgame_component_bar
    //    appearance = BitmapFactory.decodeResource(resources, R.drawable.bossgame_component_bar);

    // Sets the rectangle of the empty bossgame_component_bar
    //    dest_rect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);
    //
    //    // Sets the rectangle of the red bossgame_component_bar
    //    healthRect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);
    //
    //    // Sets the appearance of the red bossgame_component_bar
    //    healthbar = BitmapFactory.decodeResource(resources, R.drawable.redbar);
  }

  /**
   * Draws the bossgame_component_bar as well as the red bossgame_component_bar
   *
   * @param canvas of the bossgame_component_bar and red bossgame_component_bar
   * @param paint the style of them
   */
  //  public void draw(Canvas canvas, Paint paint) {
  //    canvas.drawBitmap(appearance, null, dest_rect, paint);
  //
  //    canvas.drawBitmap(healthbar, null, healthRect, paint);
  //  }

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

  public double getHealthRatio() {
    return ratioOfHealth;
  }

  /**
   * Determines how much health the boss should have left based on the ratio of his health with the
   * bossgame_component_bar length
   *
   * @param health of the boss
   * @param totalHealth of the boss
   * @return the health we should take away
   */
  private int determineHealth(int health, int totalHealth) {
    // Determine the health of the bossgame_component_bar relative to the health and totalHealth of the Boss
    int totalBar = getSize();
    int healthToDraw = health * totalBar / totalHealth;
    return totalBar - healthToDraw;
  }

  @Override
  public void update() {
    action(observable.getState(), observable.getInitialState());
  }
}
