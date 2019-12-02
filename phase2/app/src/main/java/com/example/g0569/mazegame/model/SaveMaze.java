package com.example.g0569.mazegame.model;

import com.example.g0569.utils.Coordinate;

import java.io.Serializable;

/** The type Save maze. */
public class SaveMaze implements Serializable {
  private int[][] mazeGrid;
  private Coordinate playerCoordinate;
  private int remainTime;

  /**
   * Get maze grid int [ ] [ ].
   *
   * @return the int [ ] [ ]
   */
  int[][] getMazeGrid() {
    return mazeGrid;
  }

  /**
   * Sets maze grid.
   *
   * @param mazeGrid the maze grid
   */
  void setMazeGrid(int[][] mazeGrid) {
    this.mazeGrid = mazeGrid;
  }

  /**
   * Gets player coordinate.
   *
   * @return the player coordinate
   */
  Coordinate getPlayerCoordinate() {
    return playerCoordinate;
  }

  /**
   * Sets player coordinate.
   *
   * @param playerCoordinate the player coordinate
   */
  void setPlayerCoordinate(Coordinate playerCoordinate) {
    this.playerCoordinate = playerCoordinate;
  }

  /**
   * Check if the mazeGrid is empty boolean.
   *
   * @return the boolean that whether is
   */
  boolean isEmpty() {
    return mazeGrid == null;
  }

  /**
   * Gets remain time.
   *
   * @return the remain time
   */
  int getRemainTime() {
    return this.remainTime;
  }

  /**
   * Sets remain time.
   *
   * @param remainTime the remain time
   */
  void setRemainTime(int remainTime) {
    this.remainTime = remainTime;
  }
}
