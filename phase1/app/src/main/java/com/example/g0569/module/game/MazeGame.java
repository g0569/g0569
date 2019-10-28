package com.example.g0569.module.game;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.utils.Coordinate;
import com.example.g0569.utils.Constants;

import java.sql.Array;
import java.util.ArrayList;

public class MazeGame extends Game {

  private float grid_width;

  private float grid_height;

  private Coordinate startpoint;

  public Item[][] MyMazeItem = new Item[Constants.GRID_NUM][Constants.GRID_NUM];

  public MazeGame(GameManager gameManager) {
      super(gameManager);
      startpoint = new Coordinate(10, 10);
      grid_width = gameManager.getScreen_width() / Constants.GRID_NUM;
      grid_height = gameManager.getScreen_height() / Constants.GRID_NUM;
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

  public void move(float x, float y){}

//  public void createItem(float a, float b) {}

  public void setMyMazeItem() {
  }
}
