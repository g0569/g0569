package com.example.g0569.mazegame.model;

import com.example.g0569.mazegame.MazeContract;
import com.example.g0569.utils.Coordinate;


import java.util.ArrayList;

public class SaveMaze {
    private int[][] mazeGrid;
    private Coordinate playerCoordinate;
    private MazeStopWatch stopWatch;

    public void setMazeGrid(int[][] mazeGrid) {
    this.mazeGrid = mazeGrid;
    }

    public void setPlayerCoordinate(Coordinate playerCoordinate) {
        this.playerCoordinate = playerCoordinate;
    }

    public void setStopWatch(MazeStopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    public int[][] getMazeGrid() {
        return mazeGrid;
    }

    public Coordinate getPlayerCoordinate() {
        return playerCoordinate;
    }

    public MazeStopWatch getStopWatch() {
        return stopWatch;
    }
}
