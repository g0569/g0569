package com.example.g0569.bossgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Coordinate;

public interface BossContract {
  interface View extends BaseView<Presenter> {
    void initView();
    void drawBossPlayer();
    void drawEnemy();
    void drawHealthBar();

    void setThrown(boolean b);

    void setCurrentProjectileBitmap(String typeProjectile);
  }

  interface Presenter extends BasePresenter {
    void pause();
    void shoot();
    void showMenu();
//    Coordinate getBossPlayerCoordinate();
//    Coordinate getEnemeyCoordinate();
//    Coordinate getHealthBarCoordinate();

    void update();

    void attackBoss();
  }
}
