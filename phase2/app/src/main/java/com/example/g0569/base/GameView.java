package com.example.g0569.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable  {

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

    /**
     * Instantiates a new Baseview.
     *
     * @param context the context
     */
    public GameView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        activity = (Activity) context;
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
