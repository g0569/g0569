package com.example.g0569.bossgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.R;
import com.example.g0569.base.GameView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
// TODO use factory method to determine which npc
// TODO find out how to input npc
// TODO implement a timer for VIEW

/** The Bossview for the bossgame. */
public class BossView extends GameView implements BossContract.View {
  private BossContract.Presenter bossPresenter;
  private Bitmap background;
  private Bitmap bossPlayer;
  private Bitmap enemyRight;
  private Bitmap enemyLeft;
  private Bitmap enemyAppearance;
  private Bitmap menuButton;
  private Bitmap pauseButton;
  private Bitmap switchButton;
  private Bitmap shootButton;
  private Bitmap healthBar;
  private Coordinate switchButtonCoordinates;
  private Coordinate healthBarHolderCoordinate;
  private Coordinate menuButtonCoordinates;
  private Bitmap healthBarHolder;
  private boolean thrown;
  private Bitmap currentProjectile;
  private Coordinate enemyCoordinate;
  private Coordinate pauseButtonCoordinates;
  private Coordinate shootButtonCoordinate;
  private Coordinate bossPlayerCoordinate;
  private Coordinate healthBarCoordinate;
  private Coordinate currentProjectileCoordinate;
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

  @Override
  /* Initializes everything needed when the surface is created */
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    initBitmaps();
    //    initView();

    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();
    //    bossGame = (BossGame) mainActivity.getGameManager().getCurrentGame();
    //    bossGame.createItems(getResources());
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
      // this should technically be called updateView()
      initView();
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      if (canvas != null) {
        sfh.unlockCanvasAndPost(canvas);
      }
    }
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
          shootButton.getWidth(),
          shootButton.getHeight())) {
        System.out.println("shoot out");
        Toast.makeText(activity, "Throw", Toast.LENGTH_SHORT).show();
        //        bossPresenter.shoot();

      } else if (inRange(
          x,
          y,
          switchButtonCoordinates.getX(),
          switchButtonCoordinates.getY(),
          switchButton.getWidth(),
          switchButton.getHeight())) {
        System.out.println("switch team");
        Toast.makeText(activity, "Switch", Toast.LENGTH_SHORT).show();

        //        bossPresenter.switchTeam();
      }
      else if (inRange(x,y, pauseButtonCoordinates.getX(), pauseButtonCoordinates.getY(), pauseButton.getWidth(), pauseButton.getHeight())){
        Toast.makeText(activity, "Paused", Toast.LENGTH_SHORT).show();
      }
      else if(inRange(x,y,menuButtonCoordinates.getX(),menuButtonCoordinates.getY(), menuButton.getWidth(),menuButton.getHeight())){
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
      //      bossGame.update();
      bossPresenter.update();
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 1) Thread.sleep(1 - (endTime - startTime));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }

  /** Initializes all the bitmaps needed for the game. Called upon creation */
  private void initBitmaps() {

    background = BitmapFactory.decodeResource(getResources(), R.drawable.bossforest);
    pauseButton = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    pauseButton = Bitmap.createScaledBitmap(pauseButton, (int) (getWidth()*0.08f), (int)(getHeight()*0.10f), false);
    menuButton = BitmapFactory.decodeResource(getResources(), R.drawable.homebutton);
    menuButton = Bitmap.createScaledBitmap(menuButton, (int) (getWidth()*0.08f), (int)(getHeight()*0.11f), false);
    bossPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.aim);
    healthBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbar);
    healthBar = Bitmap.createScaledBitmap(healthBar, getWidth() / 3, getHeight() / 3, false);
    healthBarHolder = BitmapFactory.decodeResource(getResources(), R.drawable.bar);
    healthBarHolder =
        Bitmap.createScaledBitmap(healthBarHolder, getWidth() / 3, getHeight() / 3, false);
    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();

    enemyRight = BitmapFactory.decodeResource(getResources(), R.drawable.enemyright);
    enemyLeft = BitmapFactory.decodeResource(getResources(), R.drawable.enemyleft);

    enemyLeft =
        Bitmap.createScaledBitmap(
            enemyLeft, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
    enemyRight =
        Bitmap.createScaledBitmap(
            enemyLeft, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
    enemyAppearance = enemyRight;
    //    bossPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.aim);
    bossPlayer =
        Bitmap.createScaledBitmap(
            bossPlayer, (int) (getWidth() * 0.25f), (int) (getHeight() * 0.30f), false);
    shootButton = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_button);
    shootButton =
        Bitmap.createScaledBitmap(
            shootButton, (int) (getWidth() * 0.13f), (int) (getHeight() * 0.20f), false);
    switchButton = BitmapFactory.decodeResource(getResources(), R.drawable.red_button);
    switchButton =
        Bitmap.createScaledBitmap(
            switchButton, (int) (getWidth() * 0.13f), (int) (getHeight() * 0.20f), false);
    initCoordinates();
  }

  /** Initializes all the coordinates on the surface relative to bitmaps */
  public void initCoordinates() {
    bossPresenter.setEnemyMovement(screenWidth);
    enemyDirection = bossPresenter.getEnemyMovement();
    bossPlayerCoordinate = new Coordinate(0, 0);
    enemyCoordinate = new Coordinate(0, 0);
    healthBarCoordinate = new Coordinate(0, 0);
    healthBarHolderCoordinate = new Coordinate(0, 0);

    currentProjectileCoordinate = new Coordinate(0, 0);
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    shootButtonCoordinate = new Coordinate(screenWidth - 4 * unitX, screenHeight - 5 * unitY);
    switchButtonCoordinates = new Coordinate(screenWidth - 7 * unitX, screenHeight - 5 * unitY);
    pauseButtonCoordinates = new Coordinate(screenWidth*0.01f, screenHeight*0.01f);
    menuButtonCoordinates = new Coordinate(screenWidth*0.01f, screenHeight*0.11f);
    bossPlayerCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.10f, screenHeight / 2 - screenHeight * 0.20f);
    enemyCoordinate.setXY(0, screenHeight / 2 - screenHeight * 0.20f);
    healthBarCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.15f, screenHeight / 2 - screenHeight * 0.50f);
    currentProjectileCoordinate.setXY(
        screenWidth / 2, (float) (screenHeight / 2 + screenHeight * 0.5));
    healthBarHolderCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.15f, screenHeight / 2 - (screenHeight * 0.50f));
  }

  @Override
  public void setPresenter(BossContract.Presenter presenter) {
    this.bossPresenter = presenter;
  }

  @Override
  public void initView() {
    //    initBitmaps();

    drawEnemy();
    drawBossPlayer();
    drawHealthBar();
    drawShootButton();
    drawCurrentProjectile();
  }

  @Override
  public void drawBossPlayer() {
    try {
      canvas.drawBitmap(
          bossPlayer, bossPlayerCoordinate.getX(), bossPlayerCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  @Override
  public void drawEnemy() {
    try {
      canvas.drawBitmap(enemyAppearance, enemyCoordinate.getX(), enemyCoordinate.getY(), paint);
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  @Override
  public void drawHealthBar() {
    try {
      canvas.drawBitmap(
          healthBarHolder, healthBarHolderCoordinate.getX(), healthBarCoordinate.getY(), paint);
      canvas.drawBitmap(healthBar, healthBarCoordinate.getX(), healthBarCoordinate.getY(), paint);

    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }

  public void drawShootButton() {
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    canvas.drawBitmap(shootButton, screenWidth - 4 * unitX, screenHeight - 5 * unitY, paint);
    canvas.drawBitmap(switchButton, screenWidth - 7 * unitX, screenHeight - 5 * unitY, paint);
    canvas.drawBitmap(pauseButton, pauseButtonCoordinates.getX(), pauseButtonCoordinates.getY(), paint);
    canvas.drawBitmap(menuButton, menuButtonCoordinates.getX(), menuButtonCoordinates.getY(), paint);
  }

  /**
   * Updates the movement of the enemy. Changes to appearance as well depending on where the enemy
   * is and which direction it is going in.
   */
  public void updateMovementEnemy() {
    // Updates the movement of the enemy

    if (enemyCoordinate.getX() <= 0) {
      enemyDirection = Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.enemyright);
      enemyAppearance =
          Bitmap.createScaledBitmap(
              enemyAppearance, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
      //      enemyAppearance = enemyLeft;
    } else if (enemyCoordinate.getX() >= screenWidth - enemyLeft.getWidth()) {
      enemyDirection = -Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.enemyleft);
      //      enemyAppearance = enemyRight;
      enemyAppearance =
          Bitmap.createScaledBitmap(
              enemyAppearance, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
    }

    enemyCoordinate.setX(enemyCoordinate.getX() + enemyDirection);
  }

  /**
   * Updates the healthbar based on the ratio of the health left. Simply multiply the health bar
   * size with the ratio to determine new size
   *
   * @param ratio of the health left to health total
   */
  public void updateMovementHealthBar(double ratio) {}

  @Override
  /*
   Called when the game gets to an end, as in the player has won. Should also record a time as
   well.
  */
  public void end(boolean end) {
    paint.setColor(Color.DKGRAY);
    paint.setTextSize(30);
    canvas.drawText("YOU WIN!!!", screenWidth / 2, screenHeight / 2, paint);
  }

  // TODO

  /** Changes the movement of a projectile */
  public void updateMovementProjectile() {
    int yDirection = (int) (-screenHeight / 100 + screenWidth / 12 * (1 - 0.99));
    int xDirection = (int) (screenWidth / 2 - (screenWidth / 12) / 2);
    if (thrown) {
      currentProjectileCoordinate.setXY(
          currentProjectileCoordinate.getX() + xDirection,
          currentProjectileCoordinate.getY() + yDirection);
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
    return (itemX > rangeX
        && itemX < rangeX + rangeDx
        && itemY > rangeY
        && itemY < rangeY + rangeDy);
  }
  // TODO
  //  public void detectCollision() {
  //    if (inRange(
  //        bossCoordinate.getX(),
  //        bossCoordinate.getY(),
  //        currentProjectileCoordinate.getX(),
  //        currentProjectileCoordinate.getY(),
  //        star.getWidth(),
  //        star.getHeight())) {
  //      bossPresenter.attackBoss();
  //    }
  //  }

  public void setThrown(boolean thrown) {
    this.thrown = thrown;
  }

  @Override
  public void setCurrentProjectileBitmap(String typeProjectile) {
    // use hashtable to find appropriate image
    // then set it properly
  }

  public void drawCurrentProjectile() {
    if (currentProjectile != null) {

      //            canvas.drawBitmap(currentProjectile);
    }
  }

  public void update() {
    updateMovementEnemy();
    updateMovementProjectile();
    //    detectCollision();
  }
}
