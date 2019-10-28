package com.example.g0569.module.component.Maze.Components;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.NonPlayerItem;

public class DownButton extends NonPlayerItem {
    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    @Override
    public void action() {}


    public DownButton() {
    }

    public void move(MazePlayer player){
        player.getDirection()[1] += 1;
    }
}
