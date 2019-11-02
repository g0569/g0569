package com.example.g0569.module.component;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

/** The smallest component in each game. */
public abstract class Item {
  /** The Coordinate of this item. */
  public Coordinate coordinate;
  /** The Appearance of this item. */
  public Bitmap appearance;
  /** The Size of this item. */
  public int size;

  private Game game;

  /**
   * Instantiates a new Item.
   *
   * @param game the game it corresponding to
   */
  public Item(Game game) {
    this.game = game;
  }

  /**
   * Draw this item.
   *
   * @param canvas the canvas
   * @param paint the paint
   */
  public abstract void draw(Canvas canvas, Paint paint);

  /** Update */
  public abstract void action();

  /**
   * Gets x coordinate.
   *
   * @return the x coordinate
   */
  public float getX() {
    return coordinate.getX();
  }

  /**
   * Gets y coordinate.
   *
   * @return the y coordinate
   */
  public float getY() {
    return coordinate.getY();
  }

  /**
   * Gets game it corresponding to.
   *
   * @return the game it corresponding to
   */
  public Game getGame() {
    return game;
  }

  /**
   * Gets coordinate of this item.
   *
   * @return the coordinate of this item
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * Gets appearance of this item.
   *
   * @return the appearance of this item
   */
  public Bitmap getAppearance() {
    return appearance;
  }

  /**
   * Gets size of this item.
   *
   * @return the size of this item
   */
  public int getSize() {
    return size;
  }
}
