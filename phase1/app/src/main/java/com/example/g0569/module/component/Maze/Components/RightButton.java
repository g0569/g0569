package com.example.g0569.module.component.Maze.Components;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.component.Player;
import com.example.g0569.module.game.Game;

public class RightButton extends NonPlayerItem {
    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    @Override
    public void action() {

    }

    public RightButton(Game game) {
        super(game);
    }

    public void move(MazePlayer player){
        player.getDirection()[0] += 1;
    }
}
