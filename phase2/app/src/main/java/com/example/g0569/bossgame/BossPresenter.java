package com.example.g0569.bossgame;

import com.example.g0569.bossgame.model.BossGame;
import com.example.g0569.utils.Inventory;

import java.util.Currency;

/** The type Boss presenter. */
public class BossPresenter implements BossContract.Presenter {
  private BossContract.View bossView;
  private BossGame bossGame;
  private Inventory inventory;

  /**
   * Instantiates a new Boss presenter.
   *
   * @param bossView the boss view
   * @param inventory
   */
  public BossPresenter(BossContract.View bossView, Inventory inventory) {
    this.bossView = bossView;
    bossView.setPresenter(this);
    bossGame = new BossGame();
    bossGame.onStart();
    bossGame.setEnemyMovement(bossView.getWidth());
    this.inventory = inventory
    ;
  }

  @Override
  public void start() {
    //    bossView.initView();
    //    String npc = bossGame.initBossTeam().getName();
    //    bossView.setCurrentProjectileBitmap(npc);
    bossGame.setBossTeam(this.inventory.getAvailableItem());
    String currentNPC = bossGame.getCurrentNPC().getType();
    String currentPower = bossGame.getCurrentNPC().getPower();
    bossView.setCurrentNPCBitmap(currentNPC);
    bossView.setCurrentProjectileBitmap(currentPower);
    //    bossView.setCurrentProjectileBitmap(inventory.getAvailableItem().get(0).getType());
    //    bossView.setCurrentNPCBitmap(inventory.getAvailableItem().get(0).getName());
    //    bossView.setCurrentProjectileBitmap("ice");
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
    // TODO may want to use getCurrentNPC instead, segregate methods
    String nextType = bossGame.getNextNPC().getType();
    String nextNPC = bossGame.getNextNPC().getName();
    bossView.setCurrentProjectileBitmap("ice");
    bossView.setCurrentNPCBitmap("npc1");
    //    String typeProjectile = bossGame.getNextNPC().getType();
    //    bossView.setCurrentProjectileBitmap(typeProjectile);
  }

  @Override
  public void pause() {}

  public void update() {}

  @Override
  public void shoot() {
//    bossGame.throwProjectile();
//    String typeProjectile = bossGame.getNextNPC().toString();
//    bossView.setCurrentProjectileBitmap(typeProjectile);
    bossView.setThrown(true);
  }

  @Override
  public void showMenu() {}

  public void attackBoss() {
    bossGame.attackBoss();
    // if there is contact, set the map to nothing
    //    bossView.setCurrentProjectileBitmap("null");

    bossView.updateMovementHealthBar(bossGame.getRatioOfHealth(), bossGame.getHealth());
    boolean end = bossGame.determineEnd();
    bossView.end(end);
  }
}
