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
  private BossContract.Presenter bossPresenter;
  private boolean pause;
  private Bitmap background;
  private Bitmap aim;
  private Bitmap enemyRight;
  private Bitmap enemyLeft;
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

  private Coordinate switchButtonCoordinates;
  private Coordinate menuButtonCoordinates;
  private Coordinate enemyCoordinate;
  private Coordinate pauseButtonCoordinates;
  private Coordinate shootButtonCoordinate;
  private Coordinate aimCoordinate;
  private Coordinate healthBarHolderCoordinate;
  private Coordinate currentProjectileCoordinate;
  private Coordinate NPCCoordinate;

  private float initialCurrentProjectileY;

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
  private boolean end;
  Map<String, Bitmap> powerTable = new HashMap<String, Bitmap>() {{
    put("ice", ice);
    put("fire", fire);
  }};
  Map<String, Bitmap> typeTable = new HashMap<String, Bitmap>() {{
    put("type1", getNpc1());
    put("type2", getNpc2());
    put("type3", getNpc3());
    put("type4", getNpc4());
    put("type5", getNpc5());
    put("type6", getNpc6());
  }};
  private boolean thrown;

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
    enemyRight = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_r);
    enemyLeft = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_l);
    enemyLeft = Bitmap.createScaledBitmap(enemyLeft, enemySize, enemySize, false);
    enemyRight = Bitmap.createScaledBitmap(enemyLeft, enemySize, enemySize, false);
    enemyAppearance = enemyRight;
    //    bossPlayer = BitmapFactory.decodeResource(getResources(),
    // R.drawable.bossgame_component_aim);
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
        new Coordinate(
            screenWidth / 2 - (float) aimSize / 2, screenHeight / 2 - (float) aimSize / 2);
    enemyCoordinate = new Coordinate(0, screenHeight / 2 - (float) enemySize / 2);
    NPCCoordinate = new Coordinate(screenWidth / 2 - screenWidth * 0.27f, screenHeight - 5 * unitY);
    currentProjectileCoordinate =
        new Coordinate(
            screenWidth / 2 - (float) currentProjectileSize / 2, screenHeight - 6 * unitY);
    initialCurrentProjectileY = screenHeight - 6 * unitY;
    shootButtonCoordinate = new Coordinate(screenWidth - 4 * unitX, screenHeight - 5 * unitY);
    switchButtonCoordinates = new Coordinate(screenWidth - 7 * unitX, screenHeight - 5 * unitY);
    pauseButtonCoordinates = new Coordinate(screenWidth * 0.01f, screenHeight * 0.01f);
    menuButtonCoordinates = new Coordinate(screenWidth * 0.01f, screenHeight * 0.20f);
    healthBarHolderCoordinate =
        new Coordinate(
            screenWidth / 2 - (float) healthBarHolderSize / 2, -(float) healthBarHolderSize / 4);
  }

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

  public void drawAim() {
    try {
      canvas.drawBitmap(aim, aimCoordinate.getX(), aimCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  public void drawEnemy() {
    try {
      canvas.drawBitmap(enemyAppearance, enemyCoordinate.getX(), enemyCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

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

  public void drawFrame() {
    canvas.drawBitmap(NPCFrame, NPCCoordinate.getX(), NPCCoordinate.getY(), paint);
  }

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
      //      enemyAppearance = enemyLeft;
    } else if (enemyCoordinate.getX() >= screenWidth - enemyLeft.getWidth()) {
      enemyDirection = -Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_l);
    }
    enemyAppearance = Bitmap.createScaledBitmap(enemyAppearance, enemySize, enemySize, false);

    enemyCoordinate.setX(enemyCoordinate.getX() + enemyDirection);
  }

  public void updateMovementHealthBar(float initialHealth, float remainingHealth) {
    if (remainingHealth == 0) {
      healthBar = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_bar);
      healthBar =
          Bitmap.createScaledBitmap(
              healthBarHolder, healthBarHolderSize, healthBarHolderSize, false);
      end(true);
    } else {
      healthBar =
          Bitmap.createScaledBitmap(
              healthBar,
              (int) (healthBarSize * remainingHealth / initialHealth),
              healthBarSize,
              false);
    }
  }
  /*
   Called when the game gets to an end, as in the player has won. Should also record a time as
   well.
  */
  public void end(boolean end) {
    if (end) {
      this.end = true;
      paint.setColor(Color.RED);
      paint.setTextSize(600);
      canvas.drawText("YOU WIN!!!", screenWidth / 2, screenHeight / 2, paint);
      pause();
      }
    }

  public int getFinalScore(){
    if (end){
    return bossPresenter.getScore();
  }
    return 0;
    }

  public void setCurrentNPCBitmap(String name) {
    NPC = typeTable.get(name);
  }

  public void drawStats() {
    String resist = bossPresenter.getResistance();
    paint.setTextSize(50);
    paint.setColor(Color.DKGRAY);
    canvas.drawText(
        "Current Resistance:" + resist, screenWidth * 0.4f, screenHeight * 0.70f, paint);
  }

  public void drawNPC() {
    try {
      NPC = Bitmap.createScaledBitmap(NPC, NPCSize, NPCSize, false);
      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  /** Changes the movement of a projectile */
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

  public void setThrown(boolean thrown) {
    this.thrown = thrown;
  }

  public void resetCurrentProjectile() {
    thrown = false;
    currentProjectileCoordinate.setXY(
        currentProjectileCoordinate.getX(), initialCurrentProjectileY);
  }

  @Override
  public void setCurrentProjectileBitmap(String typeProjectile) {
    currentProjectile =  powerTable.get(typeProjectile);
  }

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

  public void update() {
    if (!pause) {
      bossPresenter.update();
      updateMovementEnemy();
      updateMovementProjectile();
      detectCollision();
    }
  }

  public void pause() {
    pause = !pause;
  }

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
}
