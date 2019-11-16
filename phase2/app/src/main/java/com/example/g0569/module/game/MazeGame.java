package com.example.g0569.module.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.Components.Button;
import com.example.g0569.module.component.Maze.Components.MazePlayer;
import com.example.g0569.module.component.Maze.MazeHelper;
import com.example.g0569.module.utils.Coordinate;
import com.example.g0569.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MazeGame extends Game {

  private float grid_width;
  private float grid_height;
  private Coordinate startpoint;

  private Item[][] myMazeItem = new Item[Constants.GRID_NUM][Constants.GRID_NUM];
  private MazePlayer mazePlayer;
  private Button Button;

  public MazeGame(GameManager gameManager) {
    super(gameManager);
    startpoint = new Coordinate(2, 2);
    grid_width = gameManager.getScreen_width() / Constants.GRID_NUM;
    grid_height = gameManager.getScreen_height() / Constants.GRID_NUM;
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
    this.inRange(x, y);
  }

  public void inRange(float x, float y) {
    int unitX = (int) (this.getGameManager().getScreen_width() * 0.13 / 3);
    int unitY = (int) (this.getGameManager().getScreen_height() * 0.13 / 3);
    float screen_width = this.getGameManager().getScreen_width();
    float screen_height = this.getGameManager().getScreen_height();
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
