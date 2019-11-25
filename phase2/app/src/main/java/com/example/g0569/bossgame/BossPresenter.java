package com.example.g0569.bossgame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.bossgame.model.BossGame;

/** The type Boss presenter. */
public class BossPresenter implements BossContract.Presenter {
  private BossContract.View bossView;
  private BossGame bossGame;

  /**
   * Instantiates a new Boss presenter.
   *
   * @param bossView the boss view
   */
  public BossPresenter(BossContract.View bossView) {
    this.bossView = bossView;
    bossView.setPresenter(this);
    bossGame = new BossGame();
  }

  /**
   * Draw.
   *
   * @param canvas the canvas
   * @param paint the paint
   */
  public void draw(Canvas canvas, Paint paint) {
    bossGame.draw(canvas, paint);
  }

  /**
   * Create items.
   *
   * @param resources the resources
   */
  public void createItems(Resources resources) {
    bossGame.createItems(resources);
  }

  /**
   * Touch.
   *
   * @param xCoordinate the x coordinate
   * @param yCoordinate the y coordinate
   */
  public void touch(float xCoordinate, float yCoordinate) {
    bossGame.touch(xCoordinate, yCoordinate);
  }

  /** Action. */
  public void action() {
    bossGame.action();
  }

  @Override
  public void start() {
    bossView.initView();
  }
}
