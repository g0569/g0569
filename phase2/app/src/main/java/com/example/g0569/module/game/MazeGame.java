package com.example.g0569.module.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.Button;
import com.example.g0569.module.component.Maze.MazePlayer;
import com.example.g0569.module.component.Maze.MazeHelper;
import com.example.g0569.module.utils.Coordinate;
import com.example.g0569.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MazeGame extends Game {

  private float gridWidth;
  private float gridHeight;
  private Coordinate startpoint;

  private Item[][] myMazeItem = new Item[Constants.GRID_NUM][Constants.GRID_NUM];
  private MazePlayer mazePlayer;
  private Button Button;

  public MazeGame(GameManager gameManager) {
    super(gameManager);
    startpoint = new Coordinate(2, 2);
    gridWidth = gameManager.getScreenWidth() / Constants.GRID_NUM;
    gridHeight = gameManager.getScreenHeight() / Constants.GRID_NUM;
    onStart();
  }

  public MazePlayer getMazePlayer() {
    return mazePlayer;
  }

  private void onStart() {
    mazePlayer = new MazePlayer(this);
    Button = new Button(this);
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}

  public float getGridWidth() {
    return gridWidth;
  }

  public float getGridHeight() {
    return gridHeight;
  }

  public Coordinate getStartpoint() {
    return this.startpoint;
  }

  public void move(float x, float y) {
    this.inRange(x, y);
  }

  public void inRange(float x, float y) {
    int unitX = (int) (this.getGameManager().getScreenWidth() * 0.13 / 3);
    int unitY = (int) (this.getGameManager().getScreenHeight() * 0.13 / 3);
    float screen_width = this.getGameManager().getScreenWidth();
    float screen_height = this.getGameManager().getScreenHeight();
    if (x >= screen_width - 2 * unitX
        && x <= screen_width - unitX
        && y >= screen_height - 3 * unitY
        && y <= screen_height - 2 * unitY) {
      mazePlayer.getDirection()[0] += 0.5f;
    } else if (x >= screen_height - 4 * unitX
        && x <= screen_width - 3 * unitX
        && y >= screen_height - 3 * unitY
        && y <= screen_height - 2 * unitY) {
      mazePlayer.getDirection()[0] -= 0.5f;
    } else if (x >= screen_width - 3 * unitX
        && x <= screen_width - 2 * unitX
        && y >= screen_height - 4 * unitY
        && y <= screen_height - 3 * unitY) {
      mazePlayer.getDirection()[1] -= 0.5f;
    } else if (x >= screen_width - 3 * unitX
        && x <= screen_width - 2 * unitX
        && y >= screen_height - 2 * unitY
        && y <= screen_height - unitY) {
      mazePlayer.getDirection()[1] += 0.5f;
    } else {
    }
  }

  public void setMyMazeItem() {
    MazeHelper.loadMaze(myMazeItem, this);
  }

  public void draw(Canvas canvas, Paint paint) {
    for (int i = 0; i < Constants.GRID_NUM; i++) {
      for (int j = 0; j < Constants.GRID_NUM; j++) {
        if (myMazeItem[i][j] != null) {
          myMazeItem[i][j].draw(canvas, paint);
        }
      }
    }
    mazePlayer.draw(canvas, paint);
    Button.draw(canvas, paint);
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

  public void showStatistic() {
    // TODO
    List<String> statistic = new ArrayList<String>();
    statistic.add("Number of NPCs You catch: 1");
    getGameManager().showStatistic(statistic);
  }
}
