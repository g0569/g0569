package com.example.g0569.module.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.Components.DownButton;
import com.example.g0569.module.component.Maze.Components.LeftButton;
import com.example.g0569.module.component.Maze.Components.MazePlayer;
import com.example.g0569.module.component.Maze.Components.RightButton;
import com.example.g0569.module.component.Maze.Components.UpButton;
import com.example.g0569.module.component.Maze.MazeHelper;
import com.example.g0569.module.utils.Coordinate;
import com.example.g0569.utils.Constants;

public class MazeGame extends Game {

    private float grid_width;
    private float grid_height;

    private Coordinate startpoint;

    private Item[][] myMazeItem = new Item[Constants.GRID_NUM][Constants.GRID_NUM];
    private MazePlayer mazePlayer;
    private DownButton downButton;
    private RightButton rightButton;
    private LeftButton leftButton;
    private UpButton upButton;

    public MazeGame(GameManager gameManager) {
        super(gameManager);
        startpoint = new Coordinate(10, 10);
        grid_width = gameManager.getScreen_width() / Constants.GRID_NUM;
        grid_height = gameManager.getScreen_height() / Constants.GRID_NUM;
        onStart();
    }

    private void onStart() {
        mazePlayer = new MazePlayer(this);
        downButton = new DownButton(this);
        upButton = new UpButton(this);
        leftButton = new LeftButton(this);
        rightButton = new RightButton(this);

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
        if (rightButton.isInRange(x, y)) {
            rightButton.move(mazePlayer);
        } else if (leftButton.isInRange(x, y)) {
            leftButton.move(mazePlayer);
        } else if (upButton.isInRange(x, y)) {
            upButton.move(mazePlayer);
        } else if (downButton.isInRange(x, y)) {
            downButton.move(mazePlayer);
        }
    }

//  public void createItem(float a, float b) {}

    public void setMyMazeItem() {
        MazeHelper.loadMaze(myMazeItem, this);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (myMazeItem[i][j] != null) {
                    myMazeItem[i][j].draw(canvas, paint);
                }
            }
        }
        mazePlayer.draw(canvas, paint);
        upButton.draw(canvas, paint);
        downButton.draw(canvas, paint);
        leftButton.draw(canvas, paint);
        rightButton.draw(canvas, paint);
    }

    public Item[][] getMyMazeItem() {
        return myMazeItem;
    }

    public void action() {
        mazePlayer.action();
    }

    public void stopMove() {
        mazePlayer.setDirection(0, 0);
    }
}
