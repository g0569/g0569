package com.example.g0569.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.g0569.R;

import java.util.HashMap;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

  /** The Scale of the background. */
  protected float scalex;

  protected float scaley;

  /** The Screen size. */
  protected float screenWidth;

  protected float screenHeight;

  /** The Thread flag. */
  protected boolean threadFlag;

  protected Paint paint;
  protected Canvas canvas;

  protected SurfaceHolder sfh;
  protected Thread thread;
  protected Activity activity;

  private HashMap<String, Bitmap> typeLookUpTable;

  private Bitmap npc1;
  private Bitmap npc2;
  private Bitmap npc3;
  private Bitmap npc4;
  private Bitmap npc5;
  private Bitmap npc6;

  /**
   * Instantiates a new Baseview.
   *
   * @param context the context
   */
  public GameView(Context context) {
    super(context);
    init((Activity) context);
  }

  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init((Activity) context);
  }

  private void init(Activity context) {
    sfh = this.getHolder();
    sfh.addCallback(this);
    paint = new Paint();
    activity = context;

    npc1 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l1);
    npc2 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l2);
    npc3 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l3);
    npc4 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l4);
    npc5 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l5);
    npc6 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l6);
  }

  public HashMap<String, Bitmap> getTypeLookUpTable() {
    return typeLookUpTable;
  }

  public void setTypeLookUpTable(HashMap<String, Bitmap> typeLookUpTable) {
    this.typeLookUpTable = typeLookUpTable;
  }

  protected Bitmap getNpc1() {
    return npc1;
  }

  protected Bitmap getNpc2() {
    return npc2;
  }

  protected Bitmap getNpc3() {
    return npc3;
  }

  protected Bitmap getNpc4() {
    return npc4;
  }

  protected Bitmap getNpc5() {
    return npc5;
  }

  protected Bitmap getNpc6() {
    return npc6;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    screenWidth = this.getWidth();
    screenHeight = this.getHeight();
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
