package com.example.g0569.bossgame;

import com.example.g0569.bossgame.model.BossGame;
import com.example.g0569.utils.Inventory;

/** The type Boss presenter. */
public class BossPresenter implements BossContract.Presenter {
  private BossView bossView;
  private BossGame bossGame;
  private Inventory inventory;

  /**
   * Instantiates a new Boss presenter.
   *
   * @param bossView the boss view
   * @param inventory from the last levels, inventory is everything collected from mazeGame and chessGame
   */
  BossPresenter(BossView bossView, Inventory inventory) {
    this.bossView = bossView;
    bossView.setPresenter(this);
    bossGame = new BossGame();
    bossGame.onStart();
    bossGame.setEnemyMovement(bossView.getWidth());
    this.inventory = inventory;
  }

  @Override
  public void start() {
    bossGame.setBossTeam(this.inventory.getAvailableItem());
    String currentNPC = bossGame.getCurrentNPC().getType();
    String currentPower = bossGame.getCurrentNPC().getPower();
    bossView.setCurrentNPCBitmap(currentNPC);
    bossView.setCurrentProjectileBitmap(currentPower);
  }


  /**
   * Returns how much the enemy should move based on the screen size, decides the speed.
   * @return the unit amount the enemy moves on the screen.
   */
  public int getEnemyMovement() {
    return bossGame.getEnemyMovement();
  }

  @Override
  public void setEnemyMovement(float screenWidth) {
    bossGame.setEnemyMovement((int) screenWidth);
  }

  @Override
  public void switchTeam() {
    bossGame.loadNextNPC();
    String nextType = bossGame.getCurrentNPC().getPower();
    String nextNPC = bossGame.getCurrentNPC().getType();
    bossView.setCurrentProjectileBitmap(nextType);
    bossView.setCurrentNPCBitmap(nextNPC);
  }

  /**
   * Gives the current resistance of the enemy, which affects how much health it loses
   * @return the current resistance of the enemy
   */
  public String getResistance(){
    String resist = bossGame.getEnemyResistance();
    if (resist == null){
      return "none";
    }
    return resist;
  }

  /**
   * Returns the score of the game, which is calculated based on how many moves was taken. The least
   * the better.
   * @return how many times a projectile was shot out.
   */
  public int getScore(){
    return bossGame.getScore();
  }

  @Override
  public void pause() {
  }

  /**
   * Updates the bossGame, which allows components to update.
   */
  public void update() {
    bossGame.update();
  }

  @Override
  public void shoot() {
    bossView.setThrown(true);
    bossGame.shotFired();
  }

  @Override
  public void showMenu() {}

  /**
   * Decreases the health of the boss based on which projectile and NPC was being used while shooting out
   * Also updates the healthbar in bossView and checks whether the boss has zero health left.
   */
  public void attackBoss() {
    bossGame.attackBoss();
    bossView.updateMovementHealthBar(bossGame.getInitialHealth(), bossGame.getHealth());
    System.out.println(bossGame.getHealth());
    boolean end = bossGame.determineEnd();
    bossView.end(end);
  }
}
