package com.example.g0569.bossgame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.bossgame.model.BossGame;
import com.example.g0569.utils.Coordinate;

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

  /** Action. */
  public void action() {
    bossGame.action();
  }

  @Override
  public void start() {
    bossView.initView();
  }

//  public Coordinate getPlayerCoor() {
//    Coordinate playerCoor = Coordinate.create(0,0);
//    try{
//      playerCoor = bossGame.getBossPlayer().getCoordinate();
//    }
//    catch (NullPointerException e) {
//    }
//    finally{
//      return playerCoor;
//    }
//  }

//  public Coordinate getEnemyCoordinate() {
//    Coordinate playerCoor = Coordinate.create(0,0);;
//    try{
//      playerCoor = bossGame.getEnemy().getCoordinate();
//    }
//    catch (NullPointerException e) {
//    }
//    finally{
//      return playerCoor;
//    }
//  }

  @Override
  public void pause() {

  }

  public void update() {

  }

  @Override
  public void shoot() {

  }

  @Override
  public void showMenu() {

  }

//  @Override
//  public Coordinate getBossPlayerCoordinate() {
//    return null;
//  }
//
//  @Override
//  public Coordinate getEnemeyCoordinate() {
//    return null;
//  }
//
//  public Coordinate getHealthBarCoordinate() {
//    Coordinate playerCoor = Coordinate.create(0,0);;
//    try{
//      playerCoor = bossGame.getHealthBar().getCoordinate();
//    }
//    catch (NullPointerException e) {
//    }
//    finally{
//      return playerCoor;
//    }
//  }

  public void attackBoss(){
    bossGame.attackBoss();
  }
}
