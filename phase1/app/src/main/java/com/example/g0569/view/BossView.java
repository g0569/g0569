package com.example.g0569.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.MainActivity;
import com.example.g0569.module.component.Boss.Star;
import com.example.g0569.module.component.Boss.BossPlayer;
import com.example.g0569.module.component.Boss.Button;
import com.example.g0569.module.component.Boss.Enemy;

import com.example.g0569.R;
import com.example.g0569.module.game.Boss.BossGame;

public class BossView extends BaseView {
    private BossGame bossGame;
    private Bitmap background;
//  private Button button;
//  private BossPlayer aim;
//  private Enemy enemy;
    //    private ThrownItems item;
    //    private ThrownItems item2;

    public BossView(Context context) {
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
//        button = new Button(bossGame, screen_width, screen_height);
        //    aim = new BossPlayer(bossGame, screen_width, screen_height);
        //    enemy = new Enemy(bossGame, screen_width, screen_height, getResources());

        bossGame = (BossGame) mainActivity.getGameManager().getCurrentGame();
        bossGame.createItems(getResources());
        // needs modification so that itemlist is applied.
        //    Star item = new Star(bossGame, screen_width, screen_height, getResources());
        //    Star item2 = new Star(bossGame, screen_width, screen_height, getResources());
        //    aim.getInventory().add(item);
        //    aim.getInventory().add(item2);
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

            //      button.draw(canvas, paint);
            //      enemy.action();
            //      enemy.draw(canvas, paint);
            //      Star star = (Star) aim.getInventory().get(0);
            //      Star star2 = (Star) aim.getInventory().get(1);
            //      star.draw(canvas, paint);
            //      star.action();
            //      star2.draw(canvas, paint);
            //      star.action();
            //      aim.draw(canvas, paint);
            //            if(!item.inTheScreen(screen_height)){item = null;}
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
