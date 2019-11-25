package com.example.g0569.bossgame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.bossgame.model.BossGame;
import com.example.g0569.bossgame.model.ThrownItems;

public class BossPresenter implements BossContract.Presenter{
  private BossGame bossGame;

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

    @Override
    public void start() {

    }
}
