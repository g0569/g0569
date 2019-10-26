package com.example.g0569.view.Level_3;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class ItemInPlatform {

    public float x;
    public float y;

    public void draw(Canvas canvas, Paint paint){}

    public float getX(){return x;}

    public float getY(){return y;}
}
