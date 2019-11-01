package com.example.g0569.module.component.Boss;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Boss.BossGame;
import com.example.g0569.module.utils.Coordinate;

public class Enemy extends NonPlayerItem {
  // The health and initial health of the Enemy
  private int health;
  private int initialHealth;

  // The direction it moves
  private int x_direction;
  private int y_direction;

  // The rectangles used for drawing
  private Rect src_rect;
  private Rect dest_rect;

  // Screen Width and Height
  private float screen_width;
  private float screen_height;

  public Enemy(BossGame game, float screenWidth, float screenHeight, Resources resource) {
    super(game);
    this.screen_height = screenHeight;
    this.screen_width = screenWidth;

    // Appearance of the enemy
    appearance = BitmapFactory.decodeResource(resource, R.drawable.boss);

    // Size of the Enemy
    size = (int) screen_width / 6;

    // Sets the coordinate of the Enemy
    float y = (int) (screen_height / 2 - screen_width / 18 - size / 2);
    coordinate = new Coordinate(0, y);

    // Sets the direction of the Enemy
    x_direction = (int) screen_width / 100;
    y_direction = 0;

    // Sets the health of the boss
    health = 100;
    initialHealth = health;

    // Sets the rectangles to be drawn (size and stuff)
    src_rect = new Rect(0, 0, appearance.getWidth(), appearance.getHeight());
    dest_rect =
        new Rect(
            (int) coordinate.getX(),
            (int) coordinate.getY(),
            (int) coordinate.getX() + size,
            (int) coordinate.getY() + size);
  }

  /**
   * Moves the Boss around based on its position
   *
   * @param screen_width of the screen to know the limit of movement
   */
  private void action(float screen_width) {
    if (coordinate.getX() <= 0) {
      x_direction = Math.abs(x_direction);
    } else if (coordinate.getX() >= screen_width - size) {
      x_direction = -Math.abs(x_direction);
    }
    //        action();
  }

  /**
   * Draws the Enemy
   *
   * @param canvas of the Enemy being drawn on
   * @param paint the style of the enemy
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    canvas.drawBitmap(appearance, src_rect, dest_rect, paint);
  }

  /** Moves the Enemy around, left and right right now */
  @Override
  public void action() {
    action(screen_width);
    coordinate.setX(coordinate.getX() + x_direction);
    coordinate.setY(coordinate.getY() + y_direction);
    dest_rect.set(
        (int) coordinate.getX(),
        (int) coordinate.getY(),
        (int) coordinate.getX() + size,
        (int) coordinate.getY() + size);
  }

  /** Decreases the health of the enemy when Item hits it */
  public void attacked(int damageTaken) {
    health -= damageTaken;
  }

  public boolean isAttacked(float coordinateX, float coordinateY) {
    if ((getCoordinate().getX() < coordinateX && coordinateX < dest_rect.right)
        && (getCoordinate().getY() < coordinateY && coordinateY < dest_rect.bottom)) {
      System.out.println("I'm hit");
      return true;
    }
    return false;
  }

  /**
   * Getter of the size
   *
   * @return size
   */
  public int getSize() {
    return size;
  }

  /**
   * Getter for the health
   *
   * @return the current health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Returns the initalHealth
   *
   * @return the initial Health
   */
  public int getInitialHealth() {
    return initialHealth;
  }
}
