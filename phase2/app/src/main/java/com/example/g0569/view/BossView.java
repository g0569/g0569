package com.example.g0569.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.g0569.R;
import com.example.g0569.module.game.Boss.BossGame;

/** The Bossview for the bossgame. */
public class BossView extends BaseView {
  private BossGame bossGame;
  private Bitmap background;
  //  private Button button;
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
    background = BitmapFactory.decodeResource(getResources(), R.drawable.bossforest);
    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();
    bossGame = (BossGame) mainActivity.getGameManager().getCurrentGame();
    bossGame.createItems(getResources());
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
      bossGame.draw(canvas, paint);
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
      bossGame.touch(x, y);
    }
    return false;
  }

  @Override
  public void run() {
    while (threadFlag) {
      long startTime = System.currentTimeMillis();
      draw();
      bossGame.action();
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 1) Thread.sleep((long) (1 - (endTime - startTime)));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }
}
