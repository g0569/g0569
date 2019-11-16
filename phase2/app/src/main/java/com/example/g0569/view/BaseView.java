package com.example.g0569.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.g0569.MainActivity;

/** The Base of other views. */
public class BaseView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
  /** The Scale of the background. */
  protected float scalex;

  protected float scaley;

  /** The Screen size. */
  protected float screen_width;

  protected float screen_height;

  /** The Thread flag. */
  protected boolean threadFlag;

  protected Paint paint;
  protected Canvas canvas;

  protected SurfaceHolder sfh;
  protected Thread thread;
  protected MainActivity mainActivity;

  /**
   * Instantiates a new Baseview.
   *
   * @param context the context
   */
  public BaseView(Context context) {
    super(context);
    sfh = this.getHolder();
    sfh.addCallback(this);
    paint = new Paint();
    mainActivity = (MainActivity) context;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    screen_width = this.getWidth();
    screen_height = this.getHeight();
    threadFlag = true;
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    threadFlag = false;
  }

  /** Draw this view. */
  public void draw() {}

  @Override
  public void run() {}

  /**
   * Sets thread flag.
   *
   * @param threadFlag the thread flag
   */
  public void setThreadFlag(boolean threadFlag) {
    this.threadFlag = threadFlag;
  }
}
