package com.example.g0569.bossgame;

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
    bossGame.onStart();
    bossGame.setEnemyMovement(bossView.getWidth());
  }

  /** Action. */
  public void action() {
    bossGame.action();
  }

  @Override
  public void start() {
    //    bossView.initView();
  }

  public int getEnemyMovement() {
    return bossGame.getEnemyMovement();
  }

  @Override
  public void setEnemyMovement(float screenWidth) {
    bossGame.setEnemyMovement((int) screenWidth);
  }

  @Override
  public void switchTeam() {
//    String typeProjectile = bossGame.getNextProjectile().getType();
//    bossView.setCurrentProjectileBitmap(typeProjectile);
  }

  @Override
  public void pause() {}

  public void update() {}

  @Override
  public void shoot() {
    bossGame.throwProjectile();
    String typeProjectile = bossGame.getNextProjectile().toString();
    bossView.setCurrentProjectileBitmap(typeProjectile);
    bossView.setThrown(true);
  }

  @Override
  public void showMenu() {}

  public void attackBoss() {
    bossGame.attackBoss();
    // if there is contact, set the map to nothing
    bossView.setCurrentProjectileBitmap("null");

    bossView.updateMovementHealthBar(bossGame.getHealthBar().getHealthRatio());
  }
}
