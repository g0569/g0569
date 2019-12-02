package com.example.g0569.bossgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.R;
import com.example.g0569.base.GameView;
import com.example.g0569.utils.Coordinate;

import java.util.HashMap;
import java.util.Map;

/** The Bossview for the bossgame. */
public class BossView extends GameView implements BossContract.View {
  // The hashmap containing the powers and their appearance. Initialized here since at times the
  // view
  // has not been created when we call setCurrentProjectileBitmap().
  Map<String, Bitmap> typeTable =
      new HashMap<String, Bitmap>() {
        {
          put("type1", getNpc1());
          put("type2", getNpc2());
          put("type3", getNpc3());
          put("type4", getNpc4());
          put("type5", getNpc5());
          put("type6", getNpc6());
        }
      };
  private BossContract.Presenter bossPresenter;
  private boolean pause;
  // All Bitmaps that are being used in the game
  private Bitmap background;
  private Bitmap aim;
  private Bitmap enemyAppearance;
  private Bitmap menuButton;
  private Bitmap pauseButton;
  private Bitmap switchButton;
  private Bitmap shootButton;
  private Bitmap NPC;
  private Bitmap NPCFrame;
  private Bitmap currentProjectile;
  private Bitmap healthBar;
  private Bitmap healthBarHolder;
  private Bitmap ice = BitmapFactory.decodeResource(getResources(), R.drawable.icespell);
  private Bitmap fire = BitmapFactory.decodeResource(getResources(), R.drawable.fire_spell);
  // The hashmap containing the powers and their appearance. Initialized here since at times the
  // view
  // has not been created when we call setCurrentProjectileBitmap()
  Map<String, Bitmap> powerTable =
      new HashMap<String, Bitmap>() {
        {
          put("ice", ice);
          put("fire", fire);
        }
      };
  // All coordinates that are being used in the game
  private Coordinate switchButtonCoordinates;
  private Coordinate menuButtonCoordinates;
  private Coordinate enemyCoordinate;
  private Coordinate pauseButtonCoordinates;
  private Coordinate shootButtonCoordinate;
  private Coordinate aimCoordinate;
  private Coordinate healthBarHolderCoordinate;
  private Coordinate currentProjectileCoordinate;
  private Coordinate NPCCoordinate;
  // The initial value of the projectile being thrown
  private float initialCurrentProjectileY;
  // The sizes of all the Bitmaps. Used for ease since we repeat the code to get sizes many time
  private int switchButtonSize;
  private int menuButtonSize;
  private int enemySize;
  private int pauseButtonSize;
  private int shootButtonSize;
  private int aimSize;
  private int currentProjectileSize;
  private int NPCSize;
  private int healthBarSize;
  private int healthBarHolderSize;
  // Whether the game has ended.
  private boolean end;
  private boolean endFlag = false;
  // Whether a projectile is being thrown
  private boolean thrown;

  // The direction that the enemy is moving at the moment.
  private int enemyDirection;

  /**
   * Instantiates a new Bossview.
   *
   * @param context the context
   */
  public BossView(Context context) {
    super(context);
    paint.setTextSize(40);
    thread = new Thread(this);
  }

  public BossView(Context context, AttributeSet attrs) {
    super(context, attrs);
    paint.setTextSize(40);
    thread = new Thread(this);
  }

  @Override
  /* Initializes everything needed when the surface is created */
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    initBitmaps();
    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();
    if (thread.isAlive()) {
      thread.start();
    } else {
      thread = new Thread(this);
      thread.start();
    }
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    super.surfaceChanged(holder, format, width, height);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    super.surfaceDestroyed(holder);
  }

  @Override
  /* Draws everything that has an appearance in BossGame */
  public void draw() {
    try {
      canvas = sfh.lockCanvas();
      canvas.drawColor(Color.BLACK);
      canvas.save();
      canvas.scale(scalex, scaley, 0, 0);
      canvas.drawBitmap(background, 0, 0, paint);
      canvas.restore();
      initView();
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      if (canvas != null) {
        sfh.unlockCanvasAndPost(canvas);
      }
    }
  }

  /** Draws the score during the game. The score is the amount of times a projectile was thrown. */
  public void drawScore() {
    paint.setTextSize(60);
    paint.setColor(Color.DKGRAY);
    int shotsFired = bossPresenter.getScore();
    canvas.drawText("Shots Fired:" + shotsFired, getWidth() / 2, getHeight() * 0.3f, paint);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1) {
      float x = event.getX();
      float y = event.getY();
      System.out.println(x + " " + y);
      if (inRange(
          x,
          y,
          shootButtonCoordinate.getX(),
          shootButtonCoordinate.getY(),
          shootButtonSize,
          shootButtonSize)) {
        if (!pause) {
          System.out.println("shoot out");
          Toast.makeText(activity, "Throw", Toast.LENGTH_SHORT).show();
          bossPresenter.shoot();
        }
      } else if (inRange(
          x,
          y,
          switchButtonCoordinates.getX(),
          switchButtonCoordinates.getY(),
          switchButtonSize,
          switchButtonSize)) {
        if (!pause) {
          bossPresenter.switchTeam();
          resetCurrentProjectile();
          Toast.makeText(activity, "Switch", Toast.LENGTH_SHORT).show();
        }
      } else if (inRange(
          x,
          y,
          pauseButtonCoordinates.getX(),
          pauseButtonCoordinates.getY(),
          pauseButtonSize,
          pauseButtonSize)) {
        Toast.makeText(activity, "Paused", Toast.LENGTH_SHORT).show();
        pause();
      } else if (inRange(
          x,
          y,
          menuButtonCoordinates.getX(),
          menuButtonCoordinates.getY(),
          menuButtonSize,
          menuButtonSize)) {
        Toast.makeText(activity, "Menu", Toast.LENGTH_SHORT).show();
      }
    }
    return false;
  }

  @Override
  public void run() {
    while (threadFlag) {
      long startTime = System.currentTimeMillis();
      draw();
      try {
        update();
      } catch (NullPointerException e) {
        System.out.println(e);
      }
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 100) Thread.sleep(100 - (endTime - startTime));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }

  /** Initializes all the bitmaps needed for the game. Called upon creation */
  private void initBitmaps() {
    initSizes();
    background = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_background_2);
    pauseButton = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    pauseButton = Bitmap.createScaledBitmap(pauseButton, pauseButtonSize, pauseButtonSize, false);
    menuButton = BitmapFactory.decodeResource(getResources(), R.drawable.homebutton);
    menuButton = Bitmap.createScaledBitmap(menuButton, menuButtonSize, menuButtonSize, false);
    aim = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_aim);
    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();
    healthBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbar);
    healthBar = Bitmap.createScaledBitmap(healthBar, healthBarSize, healthBarSize, false);
    healthBarHolder =
        BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_bar);
    healthBarHolder =
        Bitmap.createScaledBitmap(healthBarHolder, healthBarHolderSize, healthBarHolderSize, false);

    // Enemy starts from facing the right.
    enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_r);
    enemyAppearance = Bitmap.createScaledBitmap(enemyAppearance, enemySize, enemySize, false);
    aim = Bitmap.createScaledBitmap(aim, aimSize, aimSize, false);
    shootButton = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_button);
    shootButton = Bitmap.createScaledBitmap(shootButton, shootButtonSize, shootButtonSize, false);
    switchButton = BitmapFactory.decodeResource(getResources(), R.drawable.red_button);
    switchButton =
        Bitmap.createScaledBitmap(switchButton, switchButtonSize, switchButtonSize, false);
    NPCFrame = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_frame);
    NPCFrame = Bitmap.createScaledBitmap(NPCFrame, NPCSize, NPCSize, false);
    initCoordinates();
  }

  /** Initializes all the coordinates on the surface relative to bitmaps */
  public void initCoordinates() {
    bossPresenter.setEnemyMovement(screenWidth);
    enemyDirection = bossPresenter.getEnemyMovement();
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    aimCoordinate =
        Coordinate.create(
            screenWidth / 2 - (float) aimSize / 2, screenHeight / 2 - (float) aimSize / 2);
    enemyCoordinate = Coordinate.create(0, screenHeight / 2 - (float) enemySize / 2);
    NPCCoordinate = Coordinate.create(screenWidth / 2 - screenWidth * 0.27f, screenHeight - 5 * unitY);
    currentProjectileCoordinate =
        Coordinate.create(
            screenWidth / 2 - (float) currentProjectileSize / 2, screenHeight - 6 * unitY);
    initialCurrentProjectileY = screenHeight - 6 * unitY;
    shootButtonCoordinate = Coordinate.create(screenWidth - 4 * unitX, screenHeight - 5 * unitY);
    switchButtonCoordinates = Coordinate.create(screenWidth - 7 * unitX, screenHeight - 5 * unitY);
    pauseButtonCoordinates = Coordinate.create(screenWidth * 0.01f, screenHeight * 0.01f);
    menuButtonCoordinates = Coordinate.create(screenWidth * 0.01f, screenHeight * 0.20f);
    healthBarHolderCoordinate =
        Coordinate.create(
            screenWidth / 2 - (float) healthBarHolderSize / 2, -(float) healthBarHolderSize / 4);
  }

  /** Initializes the sizes of all the bitmaps, easier to detect collisions by using sizes */
  public void initSizes() {
    switchButtonSize = (int) (getWidth() * 0.13f);
    menuButtonSize = (int) (getWidth() * 0.08f);
    enemySize = (int) (getWidth() * 0.20f);
    pauseButtonSize = (int) (getWidth() * 0.08f);
    shootButtonSize = (int) (getWidth() * 0.13f);
    aimSize = (int) (getWidth() * 0.15f);
    currentProjectileSize = (int) (getWidth() * 0.10f);
    NPCSize = (int) (getWidth() * 0.10f);
    healthBarSize = (int) (getWidth() * 0.33f);
    healthBarHolderSize = (int) (getWidth() * 0.33f);
  }

  @Override
  public void setPresenter(BossContract.Presenter presenter) {
    this.bossPresenter = presenter;
  }

  @Override
  public void initView() {
    drawEnemy();
    drawAim();
    drawHealthBarHolder();
    drawHealthBar();
    drawButtons();
    drawCurrentProjectile();
    drawFrame();
    drawNPC();
    drawScore();
    drawStats();
  }

  /** Draws the aim that we see on the screen */
  public void drawAim() {
    try {
      canvas.drawBitmap(aim, aimCoordinate.getX(), aimCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /** Draws the enemy that we see on the screen */
  public void drawEnemy() {
    try {
      canvas.drawBitmap(enemyAppearance, enemyCoordinate.getX(), enemyCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /** Draws the buttons that we see on the screen. Contains pause, menu, switch, shoot buttons */
  public void drawButtons() {
    canvas.drawBitmap(
        shootButton, shootButtonCoordinate.getX(), shootButtonCoordinate.getY(), paint);
    canvas.drawBitmap(
        switchButton, switchButtonCoordinates.getX(), switchButtonCoordinates.getY(), paint);
    canvas.drawBitmap(
        pauseButton, pauseButtonCoordinates.getX(), pauseButtonCoordinates.getY(), paint);
    canvas.drawBitmap(
        menuButton, menuButtonCoordinates.getX(), menuButtonCoordinates.getY(), paint);
  }

  /** Draws the frame that the NPC sits in */
  public void drawFrame() {
    canvas.drawBitmap(NPCFrame, NPCCoordinate.getX(), NPCCoordinate.getY(), paint);
  }

  /** Draws the grey part of the healthbar */
  public void drawHealthBarHolder() {
    try {
      canvas.drawBitmap(
          healthBarHolder,
          healthBarHolderCoordinate.getX(),
          healthBarHolderCoordinate.getY(),
          paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /** Draws the red part of the healthbar */
  public void drawHealthBar() {
    try {
      canvas.drawBitmap(
          healthBar, healthBarHolderCoordinate.getX(), healthBarHolderCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Updates the movement of the enemy. Changes to appearance as well depending on where the enemy
   * is and which direction it is going in.
   */
  public void updateMovementEnemy() {
    // Updates the movement of the enemy
    if (enemyCoordinate.getX() <= 0) {
      enemyDirection = Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_r);
    } else if (enemyCoordinate.getX() >= screenWidth - enemyAppearance.getWidth()) {
      enemyDirection = -Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_l);
    }
    // Sets the new coordinates
    enemyAppearance = Bitmap.createScaledBitmap(enemyAppearance, enemySize, enemySize, false);
    enemyCoordinate.setX(enemyCoordinate.getX() + enemyDirection);
  }

  public void updateMovementHealthBar(float initialHealth, float remainingHealth) {
    if (remainingHealth == 0) {
      healthBar = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_bar);
      healthBar =
          Bitmap.createScaledBitmap(
              healthBarHolder, healthBarHolderSize, healthBarHolderSize, false);
      if (!endFlag) {
        end(true);
        endFlag = true;
      }
    } else {
      healthBar =
          Bitmap.createScaledBitmap(
              healthBar,
              (int) (healthBarSize * remainingHealth / initialHealth),
              healthBarSize,
              false);
    }
  }

  /**
   * Determines when the game has come to an end, (when the boss has no more health). Pauses teh
   * screen when teh game is done
   *
   * @param end whether or not is is ended
   */
  public void end(boolean end) {
    if (end) {
      this.end = true;
      paint.setColor(Color.RED);
      paint.setTextSize(600);
      canvas.drawText("YOU WIN!!!", screenWidth / 2, screenHeight / 2, paint);
      pause();
      toScoreBoard();
    }
  }

  /**
   * Sets what the current NPC that the game is using looks like This originally was supposed to use
   * the Hashmap that Chess and Maze uses, but because the presenter in this game sets the order of
   * methods differently, we were not able to implement it here
   *
   * @param name of the npc it is being switched to
   */
  public void setCurrentNPCBitmap(String name) {
    NPC = typeTable.get(name);
  }

  /** Draws what the boss is currently being resistant against. */
  public void drawStats() {
    String resist = bossPresenter.getResistance();
    paint.setTextSize(50);
    paint.setColor(Color.DKGRAY);
    canvas.drawText(
        "Current Resistance:" + resist, screenWidth * 0.4f, screenHeight * 0.70f, paint);
  }

  /** Draws the NPC onto the screen */
  public void drawNPC() {
    try {
      NPC = Bitmap.createScaledBitmap(NPC, NPCSize, NPCSize, false);
      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Changes the movement of a projectile if it has been thrown. If not it doesn't move from its
   * spot
   */
  public void updateMovementProjectile() {
    int yDirection = (int) (-screenHeight / 20 + screenWidth / 12 * (1 - 0.99));
    //    int xDirection = (int) (screenWidth / 2 - (screenWidth / 12) / 2);
    if (thrown) {
      currentProjectileCoordinate.setXY(
          currentProjectileCoordinate.getX(), currentProjectileCoordinate.getY() + yDirection);
    }
  }

  /**
   * Helper that determines whether or not two items are touching based on coordinates
   *
   * @param itemX the position of the item
   * @param itemY the position of the item
   * @param rangeX the position of the second item
   * @param rangeY the position of the second item
   * @param rangeDx range of x direction
   * @param rangeDy range of y direction
   * @return whether first item is in range of second item
   */
  private boolean inRange(
      float itemX, float itemY, float rangeX, float rangeY, float rangeDx, float rangeDy) {
    return (itemX >= rangeX
        && itemX <= rangeX + rangeDx
        && itemY >= rangeY
        && itemY <= rangeY + rangeDy);
  }

  /**
   * Sets thrown once the projectile has been thrown.
   *
   * @param thrown item is thrown or not
   */
  public void setThrown(boolean thrown) {
    this.thrown = thrown;
  }

  /**
   * Resets the projectile back to its originial spot once it hits the boss or goes out of range.
   */
  public void resetCurrentProjectile() {
    thrown = false;
    currentProjectileCoordinate.setXY(
        currentProjectileCoordinate.getX(), initialCurrentProjectileY);
  }

  /**
   * Sets the current projectile bitmap depending on the ability of the NPC. Looks for it in the
   * hashtable created
   */
  public void setCurrentProjectileBitmap(String typeProjectile) {
    currentProjectile = powerTable.get(typeProjectile);
  }

  /** Draws the curernt projectile that we are using */
  public void drawCurrentProjectile() {
    try {
      canvas.drawBitmap(
          currentProjectile,
          currentProjectileCoordinate.getX(),
          currentProjectileCoordinate.getY(),
          paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Updates all movement of everything in the view that can move. Also detects whether a collision
   * has happened.
   */
  public void update() {
    if (!pause) {
      bossPresenter.update();
      updateMovementEnemy();
      updateMovementProjectile();
      detectCollision();
    }
  }

  /** Pauses the game */
  public void pause() {
    pause = !pause;
  }

  /** Detects whether or not the projectile has collided with teh enemy. */
  private void detectCollision() {
    if (inRange(
        currentProjectileCoordinate.getX() + currentProjectile.getWidth() / 2f,
        currentProjectileCoordinate.getY() + currentProjectile.getHeight() / 2f,
        enemyCoordinate.getX(),
        enemyCoordinate.getY(),
        enemyAppearance.getWidth(),
        enemyAppearance.getHeight())) {
      bossPresenter.attackBoss();
      resetCurrentProjectile();
    } else if (currentProjectileCoordinate.getY() + currentProjectileSize < 0) {
      resetCurrentProjectile();
    }
  }

  @Override
  public void toScoreBoard() {
    ((BossActivity) activity).toScoreBoard();
  }
}
