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
import java.util.Objects;
// TODO use factory method to determine which npc
// TODO find out how to input npc
// TODO implement a timer for VIEW

/** The Bossview for the bossgame. */
public class BossView extends GameView implements BossContract.View {
  private BossContract.Presenter bossPresenter;
  private boolean pause;
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
  private Bitmap NPC;
  private Bitmap healthBarHolder;
  private Bitmap currentProjectile;

  private Bitmap ice;
  private Bitmap fire;

  private int healthBarWidth;

  private Coordinate switchButtonCoordinates;
  private Coordinate healthBarHolderCoordinate;
  private Coordinate menuButtonCoordinates;
  private Coordinate enemyCoordinate;
  private Coordinate pauseButtonCoordinates;
  private Coordinate shootButtonCoordinate;
  private Coordinate bossPlayerCoordinate;
  private Coordinate healthBarCoordinate;
  private Coordinate currentProjectileCoordinate;
  private Coordinate NPCCoordinate;

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
    setTypeLookUpTable(new HashMap<String, Bitmap>());

  }

  public BossView(Context context, AttributeSet attrs) {
    super(context, attrs);
    paint.setTextSize(40);
    thread = new Thread(this);
    setTypeLookUpTable(new HashMap<String, Bitmap>());


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
          switchButton.getWidth(),
          switchButton.getHeight())) {
        if (!pause) {
          System.out.println("switch team");
          bossPresenter.switchTeam();
          resetCurrentProjectile();
          Toast.makeText(activity, "Switch", Toast.LENGTH_SHORT).show();
        }
//        bossPresenter.switchTeam();
      } else if (inRange(
          x,
          y,
          pauseButtonCoordinates.getX(),
          pauseButtonCoordinates.getY(),
          pauseButton.getWidth(),
          pauseButton.getHeight())) {
        Toast.makeText(activity, "Paused", Toast.LENGTH_SHORT).show();
        pause();
      } else if (inRange(
          x,
          y,
          menuButtonCoordinates.getX(),
          menuButtonCoordinates.getY(),
          menuButton.getWidth(),
          menuButton.getHeight())) {
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

    background = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_background_2);
    pauseButton = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    pauseButton =
        Bitmap.createScaledBitmap(
            pauseButton, (int) (getWidth() * 0.08f), (int) (getHeight() * 0.10f), false);
    menuButton = BitmapFactory.decodeResource(getResources(), R.drawable.homebutton);
    menuButton =
        Bitmap.createScaledBitmap(
            menuButton, (int) (getWidth() * 0.08f), (int) (getHeight() * 0.11f), false);
    bossPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_aim);

    healthBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbar);
    healthBar = Bitmap.createScaledBitmap(healthBar, getWidth() / 3, getHeight() / 3, false);
    healthBarWidth = healthBar.getWidth();
    healthBarHolder =
        BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_component_bar);
    healthBarHolder =
        Bitmap.createScaledBitmap(healthBarHolder, getWidth() / 3, getHeight() / 3, false);
    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();

    enemyRight = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_r);
    enemyLeft = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_l);

    enemyLeft =
        Bitmap.createScaledBitmap(
            enemyLeft, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
    enemyRight =
        Bitmap.createScaledBitmap(
            enemyLeft, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
    enemyAppearance = enemyRight;
    //    bossPlayer = BitmapFactory.decodeResource(getResources(),
    // R.drawable.bossgame_component_aim);
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
//    NPC =
//        Bitmap.createScaledBitmap(
//            NPC, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.2f), false);

    ice = BitmapFactory.decodeResource(getResources(), R.drawable.icespell);
    fire = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
//    fire = Bitmap.createScaledBitmap(fire, (int) (getWidth() *0.13f), (int)(getHeight()*0.20f), false);
    currentProjectile = Bitmap.createScaledBitmap(currentProjectile, (int) (getWidth() *0.20f), (int) (getHeight()*0.20f), false);

    getTypeLookUpTable().put("fire", fire);
    getTypeLookUpTable().put("ice", ice);

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
    NPCCoordinate = new Coordinate(0, 0);

    currentProjectileCoordinate = new Coordinate(0, 0);
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    shootButtonCoordinate = new Coordinate(screenWidth - 4 * unitX, screenHeight - 5 * unitY);
    switchButtonCoordinates = new Coordinate(screenWidth - 7 * unitX, screenHeight - 5 * unitY);
    pauseButtonCoordinates = new Coordinate(screenWidth * 0.01f, screenHeight * 0.01f);
    menuButtonCoordinates = new Coordinate(screenWidth * 0.01f, screenHeight * 0.11f);
    bossPlayerCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.10f, screenHeight / 2 - screenHeight * 0.20f);
    enemyCoordinate.setXY(0, screenHeight / 2 - screenHeight * 0.20f);
    healthBarCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.15f, screenHeight / 2 - screenHeight * 0.50f);

    healthBarHolderCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.15f, screenHeight / 2 - (screenHeight * 0.50f));
    //    currentProjectileCoordinate.setXY(
    //        screenWidth / 2, (float) (screenHeight / 2 + screenHeight * 0.5));
    currentProjectileCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.05f, screenHeight - 5 * unitY);
    NPCCoordinate.setXY(screenWidth / 2 - screenWidth * 0.20f, screenHeight - 5 * unitY);
  }

  @Override
  public void setPresenter(BossContract.Presenter presenter) {
    this.bossPresenter = presenter;
  }

  @Override
  public void initView() {
//  initBitmaps();

    drawEnemy();
    drawBossPlayer();
    drawHealthBar();
    drawShootButton();
    drawCurrentProjectile();
    drawNPC();
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
    canvas.drawBitmap(
        pauseButton, pauseButtonCoordinates.getX(), pauseButtonCoordinates.getY(), paint);
    canvas.drawBitmap(
        menuButton, menuButtonCoordinates.getX(), menuButtonCoordinates.getY(), paint);
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
      enemyAppearance =
          Bitmap.createScaledBitmap(
              enemyAppearance, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
      //      enemyAppearance = enemyLeft;
    } else if (enemyCoordinate.getX() >= screenWidth - enemyLeft.getWidth()) {
      enemyDirection = -Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.bossgame_enemy_l);
      //      enemyAppearance = enemyRight;
      enemyAppearance =
          Bitmap.createScaledBitmap(
              enemyAppearance, (int) (getWidth() * 0.20f), (int) (getHeight() * 0.25f), false);
    }

    enemyCoordinate.setX(enemyCoordinate.getX() + enemyDirection);
  }

  /**
   * Updates the healthbar based on the ratio of the health left. Simply multiply the health
   * bossgame_component_bar size with the ratio to determine new size
   *
   * @param ratio of the health left to health total
   */
  public void updateMovementHealthBar(float ratio, float remainingHealth) {
    if (ratio == 0) {
      end(true);
    } else {
      healthBar = Bitmap.createScaledBitmap(healthBar, (int) (healthBarWidth * ratio), 1, false);
    }
  }

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

  @Override
  public void setCurrentNPCBitmap(String name) {
    if (name.equals("type1")) {
      NPC = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l1);
      System.out.println("npc bitmap stored");
      //      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(),paint);
    } else if (name.equals("type2")) {
      NPC = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l2);
      //      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(),paint);
    } else if (name.equals("type3")) {
      NPC = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l3);
      //      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(),paint);
    } else if (name.equals("type4")) {
      NPC = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l4);
      //      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(),paint);
    } else if (name.equals("type5")) {
      NPC = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l5);
      //      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(),paint);
    } else if (name.equals("type6")) {
      NPC = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l6);
      //      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(),paint);
    }
    //    NPC = Bitmap.createScaledBitmap(NPC, (int)(getWidth()*0.2f), (int)(getHeight()*0.2f),
    // false);

  }

  public void drawNPC() {
    try {
      NPC =
          Bitmap.createScaledBitmap(
              NPC, (int) (getWidth() * 0.2f), (int) (getHeight() * 0.2f), false);
      canvas.drawBitmap(NPC, NPCCoordinate.getX(), NPCCoordinate.getY(), paint);
      //      System.out.println("drawing npc");
    } catch (NullPointerException e) {
      System.out.println(e.toString());
    }
  }
  // TODO

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
    int unitY = (int) (screenHeight * 0.13 / 3);
    currentProjectileCoordinate.setXY(
        screenWidth / 2 - screenWidth * 0.05f, screenHeight - 5 * unitY);
  }

  @Override
  public void setCurrentProjectileBitmap(String typeProjectile) {
    // use hashtable to find appropriate image
    // then set it properly
//    currentProjectile = Objects.requireNonNull(getTypeLookUpTable().get(typeProjectile));
//    Bitmap bitmap = Objects.requireNonNull(getTypeLookUpTable().get(typeProjectile));
//    currentProjectile = bitmap;
      //      currentProjectile =

      //          Bitmap.createScaledBitmap(
      //              currentProjectile, (int) (getWidth() * 0.13f), (int) (getHeight() * 0.13f),
      // false)
    if (typeProjectile.equals("fire")){
      currentProjectile = BitmapFactory.decodeResource(getResources(),R.drawable.fire);
//      currentProjectile = Bitmap.createScaledBitmap(currentProjectile, (int) (getWidth() *0.13f), (int) (getHeight()*0.15f), false);

    }else if(typeProjectile.equals("ice")){
      currentProjectile = BitmapFactory.decodeResource(getResources(),R.drawable.icespell);
    }
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
      updateMovementEnemy();
      updateMovementProjectile();
      detectCollision();
    }
  }

  public void pause() {
    pause = !pause;
  }

  private void detectCollision() {
    // TODO fix the ranges for collisions
    if (inRange(
            enemyCoordinate.getX(),
            enemyCoordinate.getY(),
            currentProjectileCoordinate.getX()
                + (currentProjectileCoordinate.getX() + currentProjectile.getWidth() / 2f),
            currentProjectileCoordinate.getY(),
            currentProjectile.getWidth(),
            currentProjectile.getHeight())
        || inRange(
            currentProjectileCoordinate.getX()
                + (currentProjectileCoordinate.getX() + currentProjectile.getWidth()),
            currentProjectileCoordinate.getY(),
            enemyCoordinate.getX(),
            enemyCoordinate.getY(),
            enemyAppearance.getWidth(),
            enemyAppearance.getHeight())) {
      System.out.println("HIT");
      bossPresenter.attackBoss();
      resetCurrentProjectile();
    } else if (currentProjectileCoordinate.getY() < 0) {
      resetCurrentProjectile();
    }
  }
}
