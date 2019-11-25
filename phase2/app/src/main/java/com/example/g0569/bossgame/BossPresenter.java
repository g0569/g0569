package com.example.g0569.bossgame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.bossgame.model.BossGame;
import com.example.g0569.bossgame.model.ThrownItems;

public class BossPresenter implements BossContract.Presenter {
  private BossGame bossGame;
  private BossContract.View bossView;

  BossPresenter(BossContract.View bossView, BossGame bossGame) {
    this.bossGame = bossGame;
    this.bossView = bossView;
  }

  public void draw(Canvas canvas, Paint paint) {
    bossGame.draw(canvas, paint);
  }

  public void createItems(Resources resources) {
    bossGame.createItems(resources);
  }

  public void touch(float xCoordinate, float yCoordinate) {
    bossGame.touch(xCoordinate, yCoordinate);
  }

  public void action() {
    bossGame.action();
  }

  public void attacked(){
      bossGame.attackBoss();
      bossView.drawHealthBar();
  }

  @Override
  public void start() {}
}
