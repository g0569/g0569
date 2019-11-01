package com.example.g0569.module.game.Boss;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
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
  int items;
  boolean paused;

  public BossGame(GameManager gameManager) {
    super(gameManager);
    paused = false;
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
    items = bossPlayer.getInventory().size();
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
    // Draws teh button to change colors
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.RED);
    canvas.drawCircle(50, 50, getGameManager().getScreen_width()/20, paint);

    // Draws the text to display stats and messages

    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
    canvas.drawText(
            "Items Left: " + items,
            getGameManager().getScreen_width() / 2,
            getGameManager().getScreen_height(),
            paint);
    canvas.drawText(
            "Health Left: " + enemy.getHealth(),
            healthBar.getX(),
            getGameManager().getScreen_height() / 2 + 50,
            paint);
    paint.setColor(Color.BLACK);
    canvas.drawText("Change Color!!", 50, 50, paint);
  }

  /** Updates all the components that are part of the lab */
  public void action() {
    if (!paused) {
      enemy.action();
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
          // Want the explosion pic to come in to play before we get rid of the projectile
          bossPlayer.getInventory().remove(projectile);
        }
      }
    }
  }

  /** Pauses the game or unpauses the game depending on the state it is in */
  @Override
  public void pause() {
    paused = !paused;
  }

  /** Loads teh game after it has been saved */
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
      projectile.thrown();
      items--;
      // Seems not to be accurate yet
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
      // If we press the button on top is changes the color of the button
    } else if(inRange(x,y,50,50, getGameManager().getScreen_width()/20)){
      button.changeColor();
    }
    else {
      // Pauses the game if anywhere else is paused. Update later to include a pause button for all games
      pause();
    }
  }
}
