package com.example.g0569.module.game.Boss;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.component.Boss.BossPlayer;
import com.example.g0569.module.component.Boss.Button;
import com.example.g0569.module.component.Boss.Enemy;
import com.example.g0569.module.component.Boss.HealthBar;
import com.example.g0569.module.component.Boss.Star;
import com.example.g0569.module.component.Boss.ThrownItems;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.GameManager;

import java.util.ArrayList;

public class BossGame extends Game {
  BossPlayer bossPlayer;
  Enemy enemy;
  Button button;
  HealthBar healthBar;

  public BossGame(GameManager gameManager) {
    super(gameManager);
  }

  public void createItems(Resources resources){
    bossPlayer = new BossPlayer(this, getGameManager().getScreen_width(), getGameManager().getScreen_height());
    enemy = new Enemy(this, getGameManager().getScreen_width(),getGameManager().getScreen_height(), resources);
//    button = new Button(this, getGameManager().getScreen_width(),getGameManager().getScreen_height());
    healthBar = new HealthBar(this, getGameManager().getScreen_width(),getGameManager().getScreen_height());
    Star star = new Star(this,getGameManager().getScreen_width(),getGameManager().getScreen_height(),resources);
    Star star1 = new Star(this,getGameManager().getScreen_width(),getGameManager().getScreen_height(),resources);

    bossPlayer.getInventory().add(star);
    bossPlayer.getInventory().add(star1);

  }

  public void draw(Canvas canvas, Paint paint){
//    paint.setColor(Color.BLACK);
//    canvas.drawCircle(20, 30, 30, paint);
    bossPlayer.draw(canvas, paint);
    enemy.draw(canvas, paint);
//    button.draw(canvas, paint);
    healthBar.draw(canvas,paint,enemy.getHealth());
    for (int i=0; i < bossPlayer.getInventory().size();i++){
      Star star;
      star = (Star) bossPlayer.getInventory().get(i);
      star.draw(canvas, paint);
    }

  }

  public void action(){
//    bossPlayer.action();
    enemy.action();
    for (int i = 0; i < bossPlayer.getInventory().size(); i++) {
      Star star;
      star = (Star) bossPlayer.getInventory().get(i);
      star.action();
    }
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}

  private boolean inRange(float item_x, float item_y, float range_x, float range_y, float range_r) {
    return item_x > range_x - range_r
        && item_x < range_x + range_r
        && item_y > range_y - range_r
        && item_y < range_y + range_r;
  }

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
      float projectile_radius = projectile.getRadius();
      projectile.thrown();
      if (inRange(projectile_x, projectile_y, enemy_x, enermy_y, enemy_range)) {
        enemy.attacked(projectile.getDamage());

      }
      bossPlayer.getInventory().remove(projectile);
      // Want to remove the star after it has been thrown
      // Problem where one star is being thrown but the next is not
    }
  }

}
