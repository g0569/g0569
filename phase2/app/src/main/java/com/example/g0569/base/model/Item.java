package com.example.g0569.base.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.utils.Coordinate;

/** The smallest component in each game. */
public abstract class Item {

  /** The Size of this item. */
  public int size;

  /**
   * Instantiates a new Item.
   *
   */
  public Item() {
  }

  /** Update */
  public abstract void update();

  /**
   * Gets size of this item.
   *
   * @return the size of this item
   */
  public int getSize() {
    return size;
  }
}
