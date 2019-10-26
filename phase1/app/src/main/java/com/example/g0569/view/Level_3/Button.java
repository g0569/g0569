package com.example.g0569.view.Level_3;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;

public class Button extends ItemInPlatform{

    private float button_r;

    public Button(float screen_width, float screen_height){
        button_r = screen_width / 16;
        x = screen_width * 5 / 6;
        y = screen_height - button_r * 3 / 2;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, button_r, paint);
    }

    public float getR(){
        return button_r;
    }
}
