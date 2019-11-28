package com.example.g0569.base.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.utils.Coordinate;

/** The smallest component in each game. */
public abstract class Item {

  /** The Size of this item. */
  public int size;

  private BaseGame game;

  /**
   * Instantiates a new Item.
   *
   * @param game the game it corresponding to
   */
  public Item(BaseGame game) {
    this.game = game;
  }

  /** Update */
  public abstract void update();

  /**
   * Gets game it corresponding to.
   *
   * @return the game it corresponding to
   */
  public BaseGame getGame() {
    return game;
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
