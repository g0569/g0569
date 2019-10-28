package com.example.g0569.module.component.Maze.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;

public class DownButton extends NonPlayerItem {
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.YELLOW);
        canvas.drawRect(16.5f * ((MazeGame) getGame()).getGrid_width(),
                18f * ((MazeGame) getGame()).getGrid_height(),
                17.5f * ((MazeGame) getGame()).getGrid_width(),
                19f * ((MazeGame) getGame()).getGrid_height(),
                paint);
    }

    @Override
    public void action() {}

    public boolean isInRange(float x, float y) {
        return (x >= 16.5 * ((MazeGame) getGame()).getGrid_width()
                && x <= 17.5f * ((MazeGame) getGame()).getGrid_width()
                && y >= 18f * ((MazeGame) getGame()).getGrid_height()
                && y <= 19f * ((MazeGame) getGame()).getGrid_height()
        );
    }

    public DownButton(Game game) {
        super(game);
    }

    public void move(MazePlayer player){
        player.getDirection()[1] += 1;
    }
}
