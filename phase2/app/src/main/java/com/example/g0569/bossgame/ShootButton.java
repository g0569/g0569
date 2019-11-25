package com.example.g0569.bossgame;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

public class ShootButton extends Button {

    public ShootButton(BaseGame game, float screenWidth, float screenHeight) {
        super(game, screenWidth, screenHeight);
        button_r = screenWidth / 16;

        // Sets coordinates of the button
        x = screenWidth * 5 / 6;
        y = screenHeight - button_r * 3 / 2;
        coordinate = new Coordinate(x, y);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        if (colorChanged % 3 == 0){
            paint.setColor(red);
        }else if (colorChanged % 3 == 1){
            paint.setColor(yellow);
        }
        else {
            paint.setColor(blue);
        }

//    paint.setColor(Color.RED);
        canvas.drawCircle(coordinate.getX(), coordinate.getY(), button_r, paint);
    }
}
