package com.example.g0569.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.module.component.Boss.Star;
import com.example.g0569.module.component.Boss.ThrownItems;
import com.example.g0569.module.component.Boss.BossPlayer;
import com.example.g0569.module.component.Boss.Button;
import com.example.g0569.module.component.Boss.Enemy;








import com.example.g0569.R;
import com.example.g0569.view.BaseView;

public class BossView extends BaseView {

    private Bitmap background;
    private Button button;
    private BossPlayer aim;
    private Enemy enemy;
    private ThrownItems item;

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
        button = new Button(screen_width, screen_height);
        aim = new BossPlayer(screen_width, screen_height);
        enemy = new Enemy(screen_width, screen_height, getResources());
        // needs modification so that itemlist is applied.
        item = new Star(screen_width, screen_height, getResources());
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

    /**
     * Checks if the item is in range, inserting coordinates
     * @param item_x
     * @param item_y
     * @param range_x
     * @param range_y
     * @param range_r
     * @return
     */
    private boolean inRange(float item_x, float item_y, float range_x, float range_y, float range_r) {
        return item_x > range_x - range_r
                && item_x < range_x + range_r
                && item_y > range_y - range_r
                && item_y < range_y + range_r;
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
            button.draw(canvas, paint);
            enemy.action();
            enemy.draw(canvas, paint);
            // needs modification
            item.action(canvas, paint);
            item.draw(canvas, paint);
            aim.draw(canvas, paint);
            if(!item.inTheScreen(screen_height)){item = null;}
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
            if(item != null)
            {if (inRange(x, y, button.getX(), button.getY(), button.getR())) {
                Toast.makeText(mainActivity, "Throw!!!!", Toast.LENGTH_SHORT).show();
                item.thrown();
            }
                return true;
            }}
        return false;
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
}
