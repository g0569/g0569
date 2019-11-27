package com.example.g0569.bossgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BossGame extends BaseGame {
  BossPlayer bossPlayer;
  Enemy enemy;
  //  MenuButton menuButton;
  //  PauseButton pauseButton;
  //  ShootButton shootButton;
  HealthBar healthBar;
  int items;
  boolean paused;
  List<NPC> bossTeam;
  int currentTeam;

  public BossGame() {
    super();
    paused = false;
  }

  /** Creates the items for the game */
  public void onStart() {

    bossPlayer = new BossPlayer(this);
    enemy = new Enemy(this);
    healthBar = new HealthBar(this, enemy);
    items = bossPlayer.getInventory().size();
  }

  public HealthBar getHealthBar() {
    return healthBar;
  }

  public Enemy getEnemy() {
    return enemy;
  }

  public BossPlayer getBossPlayer() {
    return bossPlayer;
  }

  /** Updates all the components that are part of the lab */
  public void action() {}

  /** Pauses the game or unpauses the game depending on the state it is in */
  @Override
  public void pause() {
    paused = !paused;
  }

  /** Loads teh game after it has been saved */
  @Override
  public void load() {}

  /**
   * Checks to see if its within the range
   *
   * @param item_x of the item
   * @param item_y of the item
   * @param range_x of the item
   * @param range_y of the item
   * @param range_r of the item
   * @return true or false
   */
  private boolean inRange(float item_x, float item_y, float range_x, float range_y, float range_r) {
    return (item_x > range_x - range_r
        && item_x < range_x + range_r
        && item_y > range_y - range_r
        && item_y < range_y + range_r);
  }

  private boolean inRange(
      float item_x, float item_y, float range_x, float range_y, float range_dx, float range_dy) {
    return (item_x > range_x
        && item_x < range_x + range_dx
        && item_y > range_y
        && item_y < range_y + range_dy);
  }

  //  public void touch(float x, float y) {
  //    if (enemy.getHealth() > 0 && items >= 1) {
  //      if (inRange(x, y, shootButton.getX(), shootButton.getY(), shootButton.getR())) {
  //        Toast.makeText(getGameManager().getMainActivity(), "Throw!!!!",
  // Toast.LENGTH_SHORT).show();
  //        this.hit();
  //        // If we press the button on top is changes the color of the button
  //      } else if (inRange(x, y, 50, 50, getGameManager().getScreenWidth() / 20)) {
  //        shootButton.changeColor();
  //      } else if (inRange(
  //          x,
  //          y,
  //          pauseButton.getX(),
  //          pauseButton.getY(),
  //          pauseButton.getWidth(),
  //          pauseButton.getHeight())) {
  //        // Pauses the game if anywhere else is paused. Update later to include a pause button
  // for
  //        // all games
  //        pause();
  //      } else if (inRange(
  //          x,
  //          y,
  //          menuButton.getX(),
  //          menuButton.getY(),
  //          menuButton.getWidth(),
  //          menuButton.getHeight())) {
  //        List<String> statistic = new ArrayList<String>();
  //        getGameManager().showStatistic(statistic);
  //      }
  //    } else if (enemy.getHealth() <= 0) {
  //      List<String> statistic = new ArrayList<String>();
  //      statistic.add("YOU WON!!!");
  //      getGameManager().showStatistic(statistic);
  //    } else if (items < 1) {
  //      List<String> statistic = new ArrayList<String>();
  //      statistic.add("YOU LOSE!!!");
  //      getGameManager().showStatistic(statistic);
  //    }
  //  }

  public void attackBoss() {
    //      ThrownItems projectile = (ThrownItems) bossPlayer.getInventory().get(0);
    //      enemy.attacked(projectile.getDamage());
    ThrownItems projectile = (ThrownItems) bossPlayer.getInventory().get(0);
    enemy.attacked(projectile.getDamage(), projectile.getType());
  }

  public void throwProjectile() {
    ThrownItems projectile = (ThrownItems) bossPlayer.getInventory().get(0);
    projectile.thrown();
  }

  public NPC getNextProjectile() {
    currentTeam++;
    if (currentTeam > bossTeam.size()) {
      currentTeam = 0;
    }
    return bossTeam.get(currentTeam);
  }

  public void setEnemyMovement(int sizeOfScreen) {
    enemy.setxDirection(sizeOfScreen);
  }

  public int getEnemyMovement() {
    return enemy.getXDirection();
  }

  public void setBossTeam(List team) {
    currentTeam = 0;
    this.bossTeam = team;
  }

  public NPC initBossTeam() {
    return bossTeam.get(currentTeam);
  }
}
