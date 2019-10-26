package com.example.g0569.view.Level_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.model.Boss;
import com.example.g0569.view.BaseView;

public class Enemy extends BitmapItem{

    private int health;

    public Enemy(float screen_width, float screen_height, Resources resource){
        appearance = BitmapFactory.decodeResource(resource, R.drawable.boss);
        size = (int) screen_width / 8;
        x = 0;
        y = (int) (screen_height / 2 - screen_width / 18 - size / 2);
        dx = (int) screen_width / 100;
        dy = 0;
        health = 100;
        src_rect = new Rect(0, 0, appearance.getWidth(), appearance.getHeight());
        dest_rect = new Rect(x, y, x + size, y + size);
    }

    public void move(float screen_width){
        if(x <= 0){dx = Math.abs(dx);}
        else if (x >= screen_width - size){dx = - Math.abs(dx);}
        basicMove();
    }
}
