package com.example.g0569.bossgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.g0569.R;
import com.example.g0569.base.GameView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;

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
  private Bitmap shootButton;
  private Bitmap healthBar;
  private Bitmap star;
  private boolean thrown;
  private Bitmap currentProjectile;
  private int enemyDirection = (int) this.screenWidth/100;
  private Coordinate bossCoordinate;
  private Coordinate shootButtonCoordinate;
  private Coordinate bossPlayerCoordinate;
  private Coordinate healthBarCoordinate;
  private Coordinate currentProjectileCoordinate;


  //  private BaseButton button;
  //  private BossPlayer aim;
  //  private Enemy enemy;
  //    private ThrownItems item;
  //    private ThrownItems item2;

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
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);



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

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1) {
      float x = event.getX();
      float y = event.getY();
      System.out.println(String.valueOf(x) + " " + String.valueOf(y));
      if (inRange(x,y,shootButtonCoordinate.getX(),shootButtonCoordinate.getY(),(int)(screenWidth * 0.13 / 3),(int)(screenWidth * 0.13 / 3))){
        bossPresenter.shoot();
      }
    }
    return false;
  }

  @Override
  public void run() {
    while (threadFlag) {
      long startTime = System.currentTimeMillis();
      draw();
      update();
      //      bossGame.update();
      bossPresenter.update();
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 1) Thread.sleep((long) (1 - (endTime - startTime)));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }



private void initBitmaps(){
  background = BitmapFactory.decodeResource(getResources(), R.drawable.bossforest);
  bossPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.aim);
  healthBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbar);
  healthBar = Bitmap.createScaledBitmap(healthBar, getWidth()/5, getHeight()/5, false);
  scalex = screenWidth / background.getWidth();
  scaley = screenHeight / background.getHeight();

  enemyRight = BitmapFactory.decodeResource(getResources(), R.drawable.enemyright);
  enemyLeft = BitmapFactory.decodeResource(getResources(), R.drawable.enemyleft);

  enemyLeft = Bitmap.createScaledBitmap(enemyLeft, getWidth() / 6, getHeight() / 6, false);
  enemyRight = Bitmap.createScaledBitmap(enemyLeft, getWidth() / 6, getHeight() / 6, false);

  bossPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.aim);
  bossPlayer = Bitmap.createScaledBitmap(bossPlayer, getWidth() / 36, getHeight() / 36, false);
  shootButton = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_button);
  shootButton =
          Bitmap.createScaledBitmap(
                  shootButton, (int) (getWidth() * 0.13f), (int) (getHeight() * 0.13f), false);


}
  @Override
  public void setPresenter(BossContract.Presenter presenter) {this.bossPresenter = presenter;}

  @Override
  public void initView() {
    drawBossPlayer();
    drawEnemy();
    drawHealthBar();
    drawShootButton();
    drawCurrentProjectile();
  }

  @Override
  public void drawBossPlayer() {
    canvas.drawBitmap(bossPlayer, bossCoordinate.getX(), bossCoordinate.getY(), paint);
  }

  @Override
  public void drawEnemy() {

    canvas.drawBitmap(enemyAppearance, bossCoordinate.getX(), bossCoordinate.getY(), paint);
  }

  @Override
  public void drawHealthBar() {
    canvas.drawBitmap(healthBar, healthBarCoordinate.getX(), healthBarCoordinate.getY(), paint);

  }

  public void drawShootButton(){
    int unitX = (int)(screenWidth * 0.13 / 3);
    int unitY = (int)(screenHeight * 0.13 / 3);
    canvas.drawBitmap(shootButton, screenWidth-4*unitX, screenHeight-4*unitY, paint);

  }

  public void updateMovementEnemy(){
    // Updates the movement of the enemy
    if (bossCoordinate.getX() <= 0){
      enemyDirection = Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.enemyright);
    } else if (bossCoordinate.getX() >= screenWidth - enemyAppearance.getWidth()){
      enemyDirection = -Math.abs(enemyDirection);
      enemyAppearance = BitmapFactory.decodeResource(getResources(), R.drawable.enemyleft);
    }
    bossCoordinate.setX(bossCoordinate.getX()+enemyDirection);
  }

  public void updateMovementHealthBar(int currentHealth, int initialHealth){

  }


  // TODO
  public void updateMovementProjectile(){
    int yDirection = (int) (-screenHeight / 100 + screenWidth/12 * (1 - 0.99));
    int xDirection= (int) (screenWidth/2 - (screenWidth/12)/2);
    if (thrown){
      currentProjectileCoordinate.setXY(currentProjectileCoordinate.getX()+xDirection, currentProjectileCoordinate.getY()+yDirection);
    }
  }

  private boolean inRange(
          float itemX,float itemY, float rangeX, float rangeY, float rangeDx, float rangeDy) {
    return (itemX > rangeX
            && itemX < rangeX + rangeDx
            && itemY > rangeY
            && itemY < rangeY + rangeDy);
  }
  //TODO
  public void detectCollision(){
    if(inRange(bossCoordinate.getX(), bossCoordinate.getY(), currentProjectileCoordinate.getX(), currentProjectileCoordinate.getY(), star.getWidth(), star.getHeight())){
      bossPresenter.attackBoss();
    }
  }

  public void setThrown(boolean thrown){
    this.thrown = thrown;
  }

  @Override
  public void setCurrentProjectileBitmap(String typeProjectile) {
    //use hashtable to find appropriate image
  }

  public void drawCurrentProjectile(){
    if (currentProjectile != null){
//      canvas.drawBitmap();
    }
  }

  public void update(){
    updateMovementEnemy();
    updateMovementProjectile();
    detectCollision();
  }


}
