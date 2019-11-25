package com.example.g0569.bossgame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.base.model.NonPlayerItem;
import com.example.g0569.utils.Coordinate;

import java.util.List;

public class Enemy extends NonPlayerItem implements Observable {

  private List<Observer> observers;
  // The health and initial health of the Enemy
  private int health;
  private int initialHealth;

  // The direction it moves
  private int xDirection;
  private int yDirection;

  // The rectangles used for drawing
  private Rect srcRect;
  private Rect destRect;

  // Screen Width and Height
  private float screenWidth;
  private float screenHeight;

  private Resources resource;
  private Bitmap appearance;


  public Enemy(BossGame game, float screenWidth, float screenHeight, Resources resource) {
    super(game);
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;

    // Appearance of the enemy
    this.resource = resource;
    appearance = BitmapFactory.decodeResource(resource, R.drawable.enemyright);


    // Size of the Enemy
    size = (int) this.screenWidth / 6;

    // Sets the coordinate of the Enemy
    float y = (int) (this.screenHeight / 2 - this.screenWidth / 18 - size / 2);
    coordinate = new Coordinate(0, y);

    // Sets the direction of the Enemy
    xDirection = (int) this.screenWidth / 100;
    yDirection = 0;

    // Sets the health of the boss
    health = 30;
    initialHealth = health;

    // Sets the rectangles to be drawn (size and stuff)
    srcRect = new Rect(0, 0, appearance.getWidth(), appearance.getHeight());
    destRect =
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
    if (health > 0) {
      if (coordinate.getX() <= 0) {
        xDirection = Math.abs(xDirection);
        appearance = BitmapFactory.decodeResource(resource, R.drawable.enemyright);
      } else if (coordinate.getX() >= screen_width - size) {
        xDirection = -Math.abs(xDirection);
        appearance = BitmapFactory.decodeResource(resource, R.drawable.enemyleft);
      }
      //        action();
    }
  }

  /**
   * Draws the Enemy
   *
   * @param canvas of the Enemy being drawn on
   * @param paint the style of the enemy
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    if (health > 0) {
      canvas.drawBitmap(appearance, srcRect, destRect, paint);
    } else {
      paint.setColor(Color.RED);
      paint.setTextSize(300);
      float width = paint.measureText("You Won!!!");
      canvas.drawText("You Won!!!", screenWidth / 2 - width / 2, screenHeight / 2, paint);
    }
  }

  /** Moves the Enemy around, left and right right now */
  @Override
  public void action() {
    action(screenWidth);
    coordinate.setX(coordinate.getX() + xDirection);
    coordinate.setY(coordinate.getY() + yDirection);
    destRect.set(
        (int) coordinate.getX(),
        (int) coordinate.getY(),
        (int) coordinate.getX() + size,
        (int) coordinate.getY() + size);
  }

  /** Decreases the health of the enemy when Item hits it */
  public void attacked(int damageTaken) {
    health -= damageTaken;
    if (health <= 0) {
      appearance = null;
    }
  }

  public boolean isAttacked(float coordinateX, float coordinateY) {
    if ((getCoordinate().getX() < coordinateX && coordinateX < destRect.right)
        && (getCoordinate().getY() < coordinateY && coordinateY < destRect.bottom)) {
      //      System.out.println("I'm hit");
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

  @Override
  public int getState() {
    return getHealth();
  }

  @Override
  public void setState(int state) {
    health = state;
  }

  @Override
  public void attach(Observer observer) {
    this.observers.add(observer);
  }

  public int getInitialState(){
    return getInitialHealth();
  }

  @Override
  public void notifyAllObservers() {
    for (Observer observer: observers){
      observer.update();
    }
  }
}
