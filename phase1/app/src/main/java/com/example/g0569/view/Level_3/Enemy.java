//package com.example.g0569.view.Level_3;
//
//import android.content.res.Resources;
//import android.graphics.BitmapFactory;
//import android.graphics.Rect;
//
//import com.example.g0569.R;
//
//public class Enemy extends BitmapItem{
//
//    private int health;
//
//    public Enemy(float screen_width, float screen_height, Resources resource){
//        appearance = BitmapFactory.decodeResource(resource, R.drawable.boss);
//        size = (int) screen_width / 8;
//        x = 0;
//        y = (int) (screen_height / 2 - screen_width / 18 - size / 2);
//        dx = (int) screen_width / 100;
//        dy = 0;
//        health = 100;
//        src_rect = new Rect(0, 0, appearance.getWidth(), appearance.getHeight());
//        dest_rect = new Rect(x, y, x + size, y + size);
//    }
//
//    public void move(float screen_width){
//        if(x <= 0){dx = Math.abs(dx);}
//        else if (x >= screen_width - size){dx = - Math.abs(dx);}
//        basicMove();
//    }
//}
