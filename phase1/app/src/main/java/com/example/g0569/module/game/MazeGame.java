package com.example.g0569.module.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.Components.MazePlayer;
import com.example.g0569.module.component.Maze.MazeHelper;
import com.example.g0569.module.utils.Coordinate;
import com.example.g0569.utils.Constants;

import java.sql.Array;
import java.util.ArrayList;

public class MazeGame extends Game {

    private float grid_width;
    private float grid_height;

    private Coordinate startpoint;

    private Item[][] myMazeItem = new Item[Constants.GRID_NUM][Constants.GRID_NUM];
    private MazePlayer mazePlayer;


    public MazeGame(GameManager gameManager) {
        super(gameManager);
        startpoint = new Coordinate(10, 10);
        grid_width = gameManager.getScreen_width() / Constants.GRID_NUM;
        grid_height = gameManager.getScreen_height() / Constants.GRID_NUM;
        mazePlayer = new MazePlayer(this);
    }

    @Override
    public void pause() {
    }

    @Override
    public void load() {
    }

    public float getGrid_width() {
        return grid_width;
    }

    public float getGrid_height() {
        return grid_height;
    }


    public Coordinate getStartpoint() {
        return this.startpoint;
    }

    public void move(float x, float y) {
    }

//  public void createItem(float a, float b) {}

    public void setMyMazeItem() {
        MazeHelper.loadMaze(myMazeItem, this);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                myMazeItem[i][j].draw(canvas, paint);
            }
        }
    }

    public Item[][] getMyMazeItem() {
        return myMazeItem;
    }
}
