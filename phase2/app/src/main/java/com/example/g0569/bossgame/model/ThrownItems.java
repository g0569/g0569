package com.example.g0569.bossgame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

public class ThrownItems extends Item implements Observer {
  // Some should be set outside the initalizer instead.

  // How much damange the item has
  public int damage;

  // Whether it has been thrown or not
  public boolean thrown;
  // The rectangles to draw them
  public Rect dest_rect;
  public Rect src_rect;
  // The rectangles to draw them
  public Bitmap explodingAppearance;
  // The shrinkage level to make it seem like its moving further away
  private double shrink;
  // The direction it should move
  private int x_direction;
  private int y_direction;
  private String type;

  public ThrownItems(BaseGame game) {
    super(game);
    // Sets the size
//    size = (int) screen_width / 12;

    // Sets the coordinate
//    float x = (int) (screen_width / 2 - size / 2);
//    float y = (int) (screen_height - screen_width * 3 / 32 - size / 2);
//    coordinate = new Coordinate(x, y);

    // Sets the shrinkage
    shrink = 0.99;

    // Sets the direction of items being thrown
    x_direction = (int) (size * (1 - shrink));
//    y_direction = (int) (-screen_height / 100 + size * (1 - shrink));

    // Sets that is has not been thrown
    thrown = false;

    // Sets its rectangle
//    dest_rect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);
    //        super.appearance = appearance;

  }

  /**
   * Draws the item being thrown
   *
   * @param canvas of the thing being drawn
   * @param paint the style of the item
//   */
//  @Override
//  public void draw(Canvas canvas, Paint paint) {
//    canvas.drawBitmap(appearance, src_rect, dest_rect, paint);
//  }

  /**
   * Returns how much damage it deals
   *
   * @return the damage
   */
  public int getDamage() {
    return damage;
  }

  /** Moves the item being thrown if it has been thrown */
  public void action() {
    //    System.out.println(thrown);
    if (thrown) {
      size = (int) (size * shrink);
//      actionHelp();
    }
  }

  /**
   * Checks to see if the item is still in the scree.
   *
   * @param screen_height of the phone
   * @return if it is in the screen
   */
//  public boolean inTheScreen(float screen_height) {
//    // THis can be done in GameBoss instead so move there in the future, for now it is also fine
//    return 0 <= (coordinate.getY() + size) && (coordinate.getY() + size) <= screen_height;
//  }

  /** Sets thrown to true */
  public void thrown() {
    thrown = true;
  }

  /** Helper so that it can tell the item to change coordinates */
//  private void actionHelp() {
//    coordinate.setX(coordinate.getX() + x_direction);
//    coordinate.setY(coordinate.getY() + y_direction - 50);
//    dest_rect.set(
//        (int) coordinate.getX(),
//        (int) coordinate.getY(),
//        (int) coordinate.getX() + size,
//        (int) coordinate.getY() + size);
//  }

  /**
   * Returns the radius
   *
   * @return radius
   */
  public float getRadius() {
    return size;
  }



//  public boolean isAttacking(float coordinateX, float coordinateY) {
//    if ((getCoordinate().getX() < coordinateX && coordinateX < dest_rect.right)
//        && (getCoordinate().getY() < coordinateY && coordinateY < dest_rect.bottom)) {
//      //      appearance = BitmapFactory.decodeResource(resource, R.drawable.star);
//      appearance = explodingAppearance;
//
//      return true;
//    }
//    //    System.out.println("Not attacked");
//    return false;
//  }

  @Override
  public void update() {
    // Could have experience section that updates
  }

    public String getType() {
      return type;
    }
}
