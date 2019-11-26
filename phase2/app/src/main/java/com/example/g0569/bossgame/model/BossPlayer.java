package com.example.g0569.bossgame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class BossPlayer extends Item {
  // Radius of the Target which represents the Player
  private float r1;
  private float r2;

  // Appearance not used for now, is drawn in draw method instead
  private Bitmap appearance;

  // The inventory of the items the Player can throw
  private List<ThrownItems> inventory = new ArrayList<>();

  private Rect srcRect;
  private Rect destRect;

  public BossPlayer(BaseGame game, float screenWidth, float screenHeight, Resources resource) {
    super(game);
    r1 = screenWidth / 36;
    size = (int) r1;
    r2 = screenWidth / 200;
    float x = screenWidth / 2;
    float y = screenHeight / 2 - r1 * 2;
    super.coordinate = new Coordinate(x, y);
    appearance = BitmapFactory.decodeResource(resource, R.drawable.enemyright);

    srcRect = new Rect(0, 0, appearance.getWidth(), appearance.getHeight());
    destRect =
            new Rect(
                    (int) coordinate.getX(),
                    (int) coordinate.getY(),
                    (int) coordinate.getX() + size,
                    (int) coordinate.getY() + size);
  }

  /**
   * Draws the target that represents the player
   *
   * @param canvas
   * @param paint
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    canvas.drawBitmap(appearance, srcRect, destRect, paint);

//    paint.setColor(Color.BLACK);
//    canvas.drawCircle(coordinate.getX(), coordinate.getY(), r2, paint);
//    paint.setStyle(Paint.Style.STROKE);
//    paint.setStrokeWidth(r2);
//    canvas.drawCircle(coordinate.getX(), coordinate.getY(), r1, paint);
//    canvas.drawLine(
//        coordinate.getX(),
//        coordinate.getY() - r1 * 3 / 2,
//        coordinate.getX(),
//        coordinate.getY() - r1 * 1 / 2,
//        paint);
//    canvas.drawLine(
//        coordinate.getX(),
//        coordinate.getY() + r1 * 3 / 2,
//        coordinate.getX(),
//        coordinate.getY() + r1 * 1 / 2,
//        paint);
//    canvas.drawLine(
//        coordinate.getX() - r1 * 3 / 2,
//        coordinate.getY(),
//        coordinate.getX() - r1 * 1 / 2,
//        coordinate.getY(),
//        paint);
//    canvas.drawLine(
//        coordinate.getX() + r1 * 3 / 2,
//        coordinate.getY(),
//        coordinate.getX() + r1 * 1 / 2,
//        coordinate.getY(),
//        paint);
  }

  /**
   * Returns the radius
   *
   * @return radius
   */
  public float getR1() {
    return r1;
  }

  /**
   * Action so that it updates the inventory to show the next item to be thrown Updates the list
   * essentially
   */
  @Override
  public void action() {}

  /**
   * Returns the inventory of the player
   *
   * @return inventory
   */
  public List getInventory() {
    return inventory;
  }
}
