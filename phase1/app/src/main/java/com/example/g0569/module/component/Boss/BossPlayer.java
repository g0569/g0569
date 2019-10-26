package com.example.g0569.module.component.Boss;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Player;
import com.example.g0569.module.utils.Coordinate;
import com.example.g0569.view.Level_3.ThrownItem;

public class BossPlayer extends Player {
    private float r1;
    private float r2;
    private ThrownItem[] items;

    public BossPlayer(float screen_width, float screen_height){
        r1 = screen_width / 36;
        r2 = screen_width / 200;
        float x = screen_width / 2;
        float y = screen_height / 2 - r1 * 2;
        super.coordinate = new Coordinate(x,y);
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.BLACK);
        canvas.drawCircle(coordinate.getX(), coordinate.getY(), r2, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(r2);
        canvas.drawCircle(coordinate.getX(), coordinate.getY(), r1, paint);
        canvas.drawLine(coordinate.getX(), coordinate.getY() - r1 * 3 / 2, coordinate.getX(), coordinate.getY() - r1 * 1 / 2, paint);
        canvas.drawLine(coordinate.getX(), coordinate.getY() + r1 * 3 / 2, coordinate.getX(), coordinate.getY() + r1 * 1 / 2, paint);
        canvas.drawLine(coordinate.getX() - r1 * 3 / 2, coordinate.getY(), coordinate.getX() - r1 * 1 / 2, coordinate.getY(), paint);
        canvas.drawLine(coordinate.getX() + r1 * 3 / 2, coordinate.getY(), coordinate.getX() + r1 * 1 / 2, coordinate.getY(), paint);
    }

    public float getR1(){return r1;}

    @Override
    public void action() {

    }
}
