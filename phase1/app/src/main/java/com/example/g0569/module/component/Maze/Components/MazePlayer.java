package com.example.g0569.module.component.Maze.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.MazeItem.Wall;
import com.example.g0569.module.component.Player;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.utils.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MazePlayer extends Player {

    private int[] direction = new int[2];

    public MazePlayer() {
        float x = ((MazeGame)this.getGame()).getStartpoint().getX();
        float y = ((MazeGame)this.getGame()).getStartpoint().getY();
        this.coordinate.setXY(x, y);

        direction[0] = 0;
        direction[1] = 0;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    @Override
    public void action() {
        this.move();
    }

    public int[] atBoundary(){
        int[] sum = new int[2];
        if (this.coordinate.getX() == 0){
            sum[0] = Constants.AT_LEFT_BOUNDARY;
        }
        if (this.coordinate.getX() == Constants.GRID_NUM - 1){
            sum[0] = Constants.AT_RIGHT_BOUNDARY;
        }
        if (this.coordinate.getY() == 0){
            sum[1] = Constants.AT_TOP_BOUNDARY;
        }
        if (this.coordinate.getY() == Constants.GRID_NUM - 1){
            sum[1] = Constants.AT_BOTTOM_BOUNDARY;
        }
        return sum;


    }

    public void move(){
        float targetX = this.coordinate.getX() + (direction[0] + this.atBoundary()[0]);
        float targetY = this.coordinate.getY() + (direction[1] + this.atBoundary()[1]);
        if (!(((MazeGame)this.getGame()).MyMazeItem[(int)targetX][(int)targetY] instanceof Wall)){
        this.coordinate.setX(targetX);
        this.coordinate.setY(targetY);
        }

    }

    public ArrayList<NPC> getNPCAround(){
        int currX = (int)this.coordinate.getX();
        int currY = (int)this.coordinate.getY();
        Item currItem;
        ArrayList<NPC> NPCAround = new ArrayList<>();
        if (direction[0] != 1){
            currItem = ((MazeGame)this.getGame()).MyMazeItem[currX + 1][currY];
            if(currItem instanceof NPC){
                NPCAround.add((NPC)currItem);
            }
        }
        if (direction[0] != -1){
            currItem = ((MazeGame)this.getGame()).MyMazeItem[currX - 1][currY];
            if(currItem instanceof NPC){
                NPCAround.add((NPC)currItem);
            }
        }
        if (direction[1] != 1){
            currItem = ((MazeGame)this.getGame()).MyMazeItem[currX][currY + 1];
            if(currItem instanceof NPC){
                NPCAround.add((NPC)currItem);
            }
        }
        if (direction[1] != -1){
            currItem = ((MazeGame)this.getGame()).MyMazeItem[currX][currY - 1];
            if(currItem instanceof NPC){
                NPCAround.add((NPC)currItem);
            }
        }
        return NPCAround;

    }

    public int[] getDirection() {
        return direction;
    }

    public void interact(){
        for (NPC i: this.getNPCAround()){
//            i.pop();
            Toast.makeText(getGame().getGameManager().getMainActivity(), "test", Toast.LENGTH_SHORT).show();
        }
    }

}
