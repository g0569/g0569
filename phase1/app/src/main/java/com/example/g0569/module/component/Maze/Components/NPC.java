package com.example.g0569.module.component.Maze.Components;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;

public class NPC extends NonPlayerItem {
    public NPC(Game game, float x, float y) {
        super(game);
        this.coordinate.setXY(x, y);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    @Override
    public void action() {

    }

//    public String pop(){}


}
