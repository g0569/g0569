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

import com.example.g0569.R;
import com.example.g0569.model.Boss;
import com.example.g0569.view.BaseView;

public abstract class ItemInPlatform {

    public float x;
    public float y;

    public void draw(Canvas canvas, Paint paint){}

    public float getX(){return x;}

    public float getY(){return y;}
}
