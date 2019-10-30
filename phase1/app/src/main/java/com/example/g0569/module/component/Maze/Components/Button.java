package com.example.g0569.module.component.Maze.Components;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

public class Button extends NonPlayerItem {
    private Bitmap appearence;
    public Button(Game game) {
        super(game);
        this.coordinate = new Coordinate(16,16);
        Resources resources = getGame().getGameManager().getMainActivity().getResources();
        this.appearence = BitmapFactory.decodeResource(resources, R.drawable.move_button);
        appearence = Bitmap.createScaledBitmap(appearence, (int)((MazeGame) this.getGame()).getGrid_width()*3,
                (int) ((MazeGame) this.getGame()).getGrid_height()*3, false);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);

        canvas.drawBitmap(
                appearence,
                this.getX() * ((MazeGame) this.getGame()).getGrid_width(),
                this.getY() * ((MazeGame) this.getGame()).getGrid_height(),
                paint);
    }

    /**
     * Update
     */
    @Override
    public void action() {

    }

//    public String isInRange(float x, float y) {
//        if(x >= 18f * ((MazeGame) getGame()).getGrid_width()
//                && x <= 19f * ((MazeGame) getGame()).getGrid_width()
//                && y >= 18f * ((MazeGame) getGame()).getGrid_height()
//                && y <= 19f * ((MazeGame) getGame()).getGrid_height()
//        )  {return "right";}
//        else if(x >= 15f * ((MazeGame) getGame()).getGrid_width()
//                && x <= 16f * ((MazeGame) getGame()).getGrid_width()
//                && y >= 18f * ((MazeGame) getGame()).getGrid_height()
//                && y <= 19f * ((MazeGame) getGame()).getGrid_height()){
//            return "left";
//        }
//        else if(x >= 16.5 * ((MazeGame) getGame()).getGrid_width()
//                && x <= 17.5f * ((MazeGame) getGame()).getGrid_width()
//                && y >= 18f * ((MazeGame) getGame()).getGrid_height()
//                && y <= 19f * ((MazeGame) getGame()).getGrid_height()){
//            return "down";
//        }
//        else if(x >= 16.5 * ((MazeGame) getGame()).getGrid_width()
//                && x <= 17.5f * ((MazeGame) getGame()).getGrid_width()
//                && y >= 16.5f * ((MazeGame) getGame()).getGrid_height()
//                && y <= 17.5f * ((MazeGame) getGame()).getGrid_height()){
//            return "up";
//        }
//        else{
//            return null;
//        }
//
//    }

    public void move(MazePlayer player, float x, float y){

        if(x >= 18f * ((MazeGame) getGame()).getGrid_width()
                && x <= 19f * ((MazeGame) getGame()).getGrid_width()
                && y >= 17f * ((MazeGame) getGame()).getGrid_height()
                && y <= 18f * ((MazeGame) getGame()).getGrid_height()
        )  {player.getDirection()[0] += 1;}
        else if(x >= 16f * ((MazeGame) getGame()).getGrid_width()
                && x <= 17f * ((MazeGame) getGame()).getGrid_width()
                && y >= 17f * ((MazeGame) getGame()).getGrid_height()
                && y <= 18f * ((MazeGame) getGame()).getGrid_height()){
            player.getDirection()[0] -= 1;
        }
        else if(x >= 17f * ((MazeGame) getGame()).getGrid_width()
                && x <= 18f * ((MazeGame) getGame()).getGrid_width()
                && y >= 18f * ((MazeGame) getGame()).getGrid_height()
                && y <= 19f * ((MazeGame) getGame()).getGrid_height()){
            player.getDirection()[1] += 1;
        }
        else if(x >= 17f * ((MazeGame) getGame()).getGrid_width()
                && x <= 18f * ((MazeGame) getGame()).getGrid_width()
                && y >= 16f * ((MazeGame) getGame()).getGrid_height()
                && y <= 17f * ((MazeGame) getGame()).getGrid_height()){
            player.getDirection()[1] -= 1;
        }
        else{
        }
    }
}
