package com.example.g0569.module.game.Boss;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.widget.Toast;

import com.example.g0569.module.component.Boss.BossPlayer;
import com.example.g0569.module.component.Boss.Button;
import com.example.g0569.module.component.Boss.MenuButton;
import com.example.g0569.module.component.Boss.PauseButton;
import com.example.g0569.module.component.Boss.ShootButton;
import com.example.g0569.module.component.Boss.Enemy;
import com.example.g0569.module.component.Boss.HealthBar;
import com.example.g0569.module.component.Boss.Star;
import com.example.g0569.module.component.Boss.ThrownItems;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.GameManager;

import java.util.ArrayList;
import java.util.List;

public class BossGame extends Game {
  BossPlayer bossPlayer;
  Enemy enemy;
  MenuButton menuButton;
  PauseButton pauseButton;
  ShootButton shootButton;
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
            this, getGameManager().getScreenWidth(), getGameManager().getScreenHeight());
    enemy =
        new Enemy(
            this,
            getGameManager().getScreenWidth(),
            getGameManager().getScreenHeight(),
            resources);
    menuButton =
        new MenuButton(this, getGameManager().getScreenWidth(), getGameManager().getScreenHeight());
    pauseButton =
            new PauseButton(this, getGameManager().getScreenWidth(), getGameManager().getScreenHeight());
    shootButton =
            new ShootButton(this, getGameManager().getScreenWidth(), getGameManager().getScreenHeight());
    healthBar = new HealthBar(this, resources);
    Star star =
        new Star(
            this,
            getGameManager().getScreenWidth(),
            getGameManager().getScreenHeight(),
            resources);
    Star star1 =
        new Star(
            this,
            getGameManager().getScreenWidth(),
            getGameManager().getScreenHeight(),
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
    menuButton.draw(canvas, paint);
    pauseButton.draw(canvas, paint);
    shootButton.draw(canvas, paint);
    healthBar.draw(canvas, paint);

    for (int i = 0; i < bossPlayer.getInventory().size(); i++) {
      ThrownItems projectile;
      projectile = (ThrownItems) bossPlayer.getInventory().get(i);
      projectile.draw(canvas, paint);
    }
    // Draws the button to change colors
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.RED);
    canvas.drawCircle(50, 50, getGameManager().getScreenWidth()/20, paint);

    // Draws the text to display stats and messages

    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
    canvas.drawText(
            "Items Left: " + items,
            getGameManager().getScreenWidth() / 2,
            getGameManager().getScreenHeight(),
            paint);
    canvas.drawText(
            "Health Left: " + StrictMath.max(0, enemy.getHealth()),
            healthBar.getX(),
            getGameManager().getScreenHeight() / 2 + 50,
            paint);
    paint.setColor(Color.BLACK);
    canvas.drawText("Change Color!!", 50, 50, paint);
    if(items<1 && enemy.getHealth() > 0){
      paint.setColor(Color.RED);
      paint.setTextSize(300);
      float width = paint.measureText("You Lose!!!");
      canvas.drawText("You Lose!!!", getGameManager().getScreenWidth() / 2 - width / 2, getGameManager().getScreenHeight() / 2, paint);
    }
  }

  /** Updates all the components that are part of the lab */
  public void action() {
    if (!paused) {
      enemy.action();
      for (int i = 0; i < bossPlayer.getInventory().size(); i++) {
        ThrownItems projectile;
        projectile = (ThrownItems) bossPlayer.getInventory().get(i);
        projectile.action();
        if (!projectile.inTheScreen(getGameManager().getScreenHeight())) {
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

  private boolean inRange(float item_x, float item_y, float range_x, float range_y, float range_dx, float range_dy) {
    return (item_x > range_x
            && item_x < range_x + range_dx
            && item_y > range_y
            && item_y < range_y + range_dy);
  }


  /**
   * To do after the button has been pressed
   *
   * @param x of the button
   * @param y of the button
   */
  public void touch(float x, float y) {
    if (enemy.getHealth() > 0 && items >= 1) {
      if (inRange(x, y, shootButton.getX(), shootButton.getY(), shootButton.getR())) {
        Toast.makeText(getGameManager().getMainActivity(), "Throw!!!!", Toast.LENGTH_SHORT).show();
        this.hit();
        // If we press the button on top is changes the color of the button
      } else if (inRange(x, y, 50, 50, getGameManager().getScreenWidth() / 20)) {
        shootButton.changeColor();
      } else if (inRange(
          x,
          y,
          pauseButton.getX(),
          pauseButton.getY(),
          pauseButton.getWidth(),
          pauseButton.getHeight())) {
        // Pauses the game if anywhere else is paused. Update later to include a pause button for
        // all games
        pause();
      } else if (inRange(
          x,
          y,
          menuButton.getX(),
          menuButton.getY(),
          menuButton.getWidth(),
          menuButton.getHeight())) {
        List<String> statistic = new ArrayList<String>();
        getGameManager().showStatistic(statistic);
      }
    }
    else if(enemy.getHealth() <= 0){
      List<String> statistic = new ArrayList<String>();
      statistic.add("YOU WON!!!");
      getGameManager().showStatistic(statistic);
    }
    else if(items < 1){
      List<String> statistic = new ArrayList<String>();
      statistic.add("YOU LOSE!!!");
      getGameManager().showStatistic(statistic);
    }
  }

}
