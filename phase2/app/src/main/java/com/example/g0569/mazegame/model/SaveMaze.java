package com.example.g0569.mazegame.model;

import com.example.g0569.utils.Coordinate;

import java.io.Serializable;

public class SaveMaze implements Serializable {
  private int[][] mazeGrid;
  private Coordinate playerCoordinate;
  private int remainTime;

  int[][] getMazeGrid() {
    return mazeGrid;
  }

  void setMazeGrid(int[][] mazeGrid) {
    this.mazeGrid = mazeGrid;
  }

  Coordinate getPlayerCoordinate() {
    return playerCoordinate;
  }

  void setPlayerCoordinate(Coordinate playerCoordinate) {
    this.playerCoordinate = playerCoordinate;
  }

  boolean isEmpty() {
    return mazeGrid == null;
  }

  public int getRemainTime() {
    return this.remainTime;
  }

  public void setRemainTime(int remainTime) {
    this.remainTime = remainTime;
  }
}
