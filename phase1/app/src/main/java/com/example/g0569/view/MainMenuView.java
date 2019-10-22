package com.example.g0569.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.R;
import com.example.g0569.utils.Constants;

public class MainMenuView extends BaseView {

  private Bitmap background;
  private Bitmap button;
  private float button_x;
  private float button_y;
  private float button_y2;

  public MainMenuView(Context context) {
    super(context);
    paint.setTextSize(40);
    thread = new Thread(this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    button = BitmapFactory.decodeResource(getResources(), R.drawable.bt_new);
    scalex = screen_width / background.getWidth();
    scaley = screen_height / background.getHeight();

    button_x = screen_width / 2 - button.getWidth() / 2;
    button_y = screen_height / 2 + button.getHeight();
    button_y2 = button_y + button.getHeight() + 40;
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
      canvas.drawBitmap(button, button_x, button_y, paint);

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
      if (x > button_x
          && x < button_x + button.getWidth()
          && y > button_y
          && y < button_y + button.getHeight()) {
        Toast.makeText(mainActivity, "Button was pressed", Toast.LENGTH_SHORT).show();
        mainActivity.getHandler().sendEmptyMessage(Constants.TO_LOGIN_VIEW);
      }
      return true;
    }
    return false;
  }

  @Override
  public void run() {
    while (threadFlag) {
      long startTime = System.currentTimeMillis();
      draw();
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 400) Thread.sleep(400 - (endTime - startTime));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }
}
