package com.example.g0569.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.g0569.R;
import com.example.g0569.module.game.MazeGame;

public class MazeView extends BaseView {

    private Bitmap background;
    private MazeGame mazeGame;

    public MazeView(Context context) {
        super(context);
        paint.setTextSize(40);
        thread = new Thread(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        scalex = screen_width / background.getWidth();
        scaley = screen_height / background.getHeight();
        mazeGame = (MazeGame) mainActivity.getGameManager().getCurrentGame();
        mazeGame.setMyMazeItem();
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
            mazeGame.draw(canvas, paint);

        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (canvas != null) {
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void run() {
        while (threadFlag) {
            long startTime = System.currentTimeMillis();
            draw();
            long endTime = System.currentTimeMillis();
            try {
                if (endTime - startTime < 1) Thread.sleep((long) (1 - (endTime - startTime)));
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }

    private boolean inRange(float item_x, float item_y, float range_x, float range_y, float range_r) {
        return item_x > range_x - range_r
                && item_x < range_x + range_r
                && item_y > range_y - range_r
                && item_y < range_y + range_r;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1) {
            float x = event.getX();
            float y = event.getY();
            mazeGame.move(x, y);
            return true;
        }
        return false;
    }
}
