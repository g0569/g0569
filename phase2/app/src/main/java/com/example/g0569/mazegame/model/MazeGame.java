package com.example.g0569.mazegame.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.base.BaseButton;
import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.mazegame.MazeContract;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MazeGame extends BaseGame {

  private MazeContract.Presenter presenter;
  private float gridWidth;
  private float gridHeight;
  private Coordinate startpoint;

  private Item[][] myMazeItem = new Item[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
  private int[][] mazeGrid;
  private MazePlayer mazePlayer;
  private BaseButton Button;

  public MazeGame(MazeContract.Presenter presenter) {
    super();
    this.presenter = presenter;

  }

  public MazePlayer getMazePlayer() {
    return mazePlayer;
  }

  public void onStart() {
    startpoint = Coordinate.create(0, 0);
    mazePlayer = new MazePlayer(this);
    mazeGrid = MazeGenerator.generate(Constants.GRID_HEIGHT, Constants.GRID_WIDTH);
//    Button = new BaseButton(this);
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}



  public Coordinate getStartPoint() {
    return this.startpoint;
  }

  public void setMyMazeItem() {
//    MazeHelper.loadMaze(myMazeItem, this);
  }

  public Item[][] getMyMazeItem() {
    return myMazeItem;
  }

  public void update() {
    mazePlayer.update();
  }

  public void stopMove() {
    mazePlayer.setDirection(Coordinate.create(0,0));
  }

//  public void showStatistic() {
//    // TODO
//    List<String> statistic = new ArrayList<String>();
//    statistic.add("Number of NPCs You catch: 1");
//    getGameManager().showStatistic(statistic);
//  }

  public int[][] getMazeGrid() {
    return mazeGrid;
  }

  public Coordinate getPlayerDimensions() {
    return presenter.getPlayerDimensions();
  }

  public void movePlayer(Coordinate coordinate) {
    getMazePlayer().setDirection(coordinate);
  }
}
