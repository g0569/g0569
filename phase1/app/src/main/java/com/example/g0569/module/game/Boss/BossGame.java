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
    healthBar =
        new HealthBar(
            this, getGameManager().getScreen_width(), getGameManager().getScreen_height(), resources);
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

    bossPlayer.getInventory().add(star);
    bossPlayer.getInventory().add(star1);
  }

  public void draw(Canvas canvas, Paint paint) {
    enemy.draw(canvas, paint);
    bossPlayer.draw(canvas, paint);

    button.draw(canvas, paint);
    healthBar.draw(canvas, paint, enemy.getHealth());
    for (int i = 0; i < bossPlayer.getInventory().size(); i++) {
      ThrownItems projectile;
      projectile = (ThrownItems) bossPlayer.getInventory().get(i);
      projectile.draw(canvas, paint);
    }
  }

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

    if (bossPlayer.getInventory().get(0) != null) {
      ThrownItems projectile = (ThrownItems) bossPlayer.getInventory().get(0);
      float enemy_x = enemy.getX();
      float enermy_y = enemy.getY();
      int enemy_range = enemy.getSize();

      float projectile_x = projectile.getX();
      float projectile_y = projectile.getY();
      projectile.thrown();
      if (inRange(projectile_x, projectile_y, enemy_x, enermy_y, enemy_range)) {
        enemy.attacked(projectile.getDamage());
      }
    }
  }

  private boolean inRange(float item_x, float item_y, float range_x, float range_y, float range_r) {
    return (item_x > range_x - range_r
        && item_x < range_x + range_r
        && item_y > range_y - range_r
        && item_y < range_y + range_r);
  }

  public void touch(float x, float y) {
    if (inRange(x, y, button.getX(), button.getY(), button.getR())) {
      Toast.makeText(getGameManager().getMainActivity(), "Throw!!!!", Toast.LENGTH_SHORT).show();
      this.hit();
    }
  }
}
