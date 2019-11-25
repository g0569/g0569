package com.example.g0569.bossgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

public interface BossContract {
  interface View extends BaseView<Presenter> {
    void initView();
    void drawBossPlayer();
    void drawEnemy();
    void drawHealthBar();
  }

  interface Presenter extends BasePresenter {
    void pause();
    void shoot();
    void showMenu();
  }
}
