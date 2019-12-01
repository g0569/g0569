package com.example.g0569.mazegame.model;

import com.example.g0569.utils.Coordinate;


import java.io.Serializable;

public class SaveMaze implements Serializable {
    private int[][] mazeGrid;
    private Coordinate playerCoordinate;
    private MazeStopWatch stopWatch;

    void setMazeGrid(int[][] mazeGrid) {
    this.mazeGrid = mazeGrid;
    }

    void setPlayerCoordinate(Coordinate playerCoordinate) {
        this.playerCoordinate = playerCoordinate;
    }

    void setStopWatch(MazeStopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    int[][] getMazeGrid() {
        return mazeGrid;
    }

    Coordinate getPlayerCoordinate() {
        return playerCoordinate;
    }

    MazeStopWatch getStopWatch() {
        return stopWatch;
    }
}
