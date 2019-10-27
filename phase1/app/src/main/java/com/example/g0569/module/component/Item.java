package com.example.g0569.module.component;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.utils.Coordinate;

public abstract class Item {
    // Coordinate of the Item
    public Coordinate coordinate;

    // Appearance of the Item
    public Bitmap appearance;

    //Size of the Item
    public int size;

    public abstract void draw(Canvas canvas, Paint paint);

    /**
     * Update
     */
    public abstract void action();

    public float getX(){
        return coordinate.getX();
    }

    public float getY(){
        return coordinate.getY();
    }
}
