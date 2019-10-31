package com.example.g0569.module.game.Boss;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import com.example.g0569.module.component.Boss.BossPlayer;
import com.example.g0569.module.component.Boss.Button;
import com.example.g0569.module.component.Boss.Enemy;
import com.example.g0569.module.component.Boss.HealthBar;
import com.example.g0569.module.component.Boss.Star;
import com.example.g0569.module.component.Boss.ThrownItems;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.GameManager;

public class BossGame extends Game {
  BossPlayer bossPlayer;
  Enemy enemy;
  Button button;
  HealthBar healthBar;

  public BossGame(GameManager gameManager) {
    super(gameManager);
  }

  /**
   * Creates the items for the game
   *
   * @param resources for drawing some of the items
   */
  public void createItems(Resources resources) {
    bossPlayer =
        new BossPlayer(
            this, getGameManager().getScreen_width(), getGameManager().getScreen_height());
    enemy =
        new Enemy(
            this,
            getGameManager().getScreen_width(),
            getGameManager().getScreen_height(),
            resources);
    button =
        new Button(this, getGameManager().getScreen_width(), getGameManager().getScreen_height());
    healthBar = new HealthBar(this, resources);
    Star star =
        new Star(
            this,
            getGameManager().getScreen_width(),
            getGameManager().getScreen_height(),
            resources);
    Star star1 =
        new Star(
            this,
            getGameManager().getScreen_width(),
            getGameManager().getScreen_height(),
            resources);

    // Adds some stars for now just to show the game works
    bossPlayer.getInventory().add(star);
    bossPlayer.getInventory().add(star1);
  }

  /**
   * Draws all the components necessary
   *
   * @param canvas of the things being drawn
   * @param paint the style of teh things being drawm
   */
  public void draw(Canvas canvas, Paint paint) {
    enemy.draw(canvas, paint);
    bossPlayer.draw(canvas, paint);

    button.draw(canvas, paint);
    healthBar.draw(canvas, paint);
    for (int i = 0; i < bossPlayer.getInventory().size(); i++) {
      ThrownItems projectile;
      projectile = (ThrownItems) bossPlayer.getInventory().get(i);
      projectile.draw(canvas, paint);
    }
  }

  /** Updates all the components that are part of the lab */
  public void action() {
    //    bossPlayer.action();
    enemy.action();
    //        Star star = (Star) bossPlayer.getInventory().get(0);
    //        star.action();
    for (int i = 0; i < bossPlayer.getInventory().size(); i++) {
      ThrownItems projectile;
      projectile = (ThrownItems) bossPlayer.getInventory().get(i);
      projectile.action();
      if (!projectile.inTheScreen(getGameManager().getScreen_height())) {
        bossPlayer.getInventory().remove(projectile);
      }
      if (enemy.isAttacked(projectile.getX(), projectile.getY())
          || projectile.isAttacking(enemy.getX(), enemy.getY())) {
        enemy.attacked(projectile.getDamage());
        healthBar.action(enemy.getHealth(), enemy.getInitialHealth());
        bossPlayer.getInventory().remove(projectile);
      }
    }
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}

  /**
   * Check if the item has hit the enemy
   *
   * @param
   * @param
   */
  public void hit() {

    if (!bossPlayer.getInventory().isEmpty() && bossPlayer.getInventory().get(0) != null) {
      ThrownItems projectile = (ThrownItems) bossPlayer.getInventory().get(0);
      float enemy_x = enemy.getX();
      float enermy_y = enemy.getY();
      int enemy_range = enemy.getSize();

      float projectile_x = projectile.getX();
      float projectile_y = projectile.getY();
      projectile.thrown();
      // Seems not to be accurate yet
      //      inRange(projectile_x, projectile_y, enemy_x, enermy_y, enemy_range
      if (enemy.isAttacked(projectile.getX(), projectile.getY())
          || projectile.isAttacking(enemy.getX(), enemy.getY())) {
        enemy.attacked(projectile.getDamage());
        healthBar.action(enemy.getHealth(), enemy.getInitialHealth());
      }
    }
  }

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

  /**
   * To do after the button has been pressed
   *
   * @param x of the button
   * @param y of the button
   */
  public void touch(float x, float y) {
    if (inRange(x, y, button.getX(), button.getY(), button.getR())) {
      Toast.makeText(getGameManager().getMainActivity(), "Throw!!!!", Toast.LENGTH_SHORT).show();
      this.hit();
    }
  }
}
