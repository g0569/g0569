package com.example.g0569.mazegame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Coordinate;

public interface MazeContract {
  interface View extends BaseView<Presenter> {
    void initView();
    void drawMaze(int[][] mazeGrid);
    void drawPlayer(Coordinate coor);
    void drawNPC(Coordinate coor);

    Coordinate getPlayerDimensions();
  }

  interface Presenter extends BasePresenter {
    void movePlayer(Coordinate coordinate);

    void stopMove();

    int[][] getMazeGrid();

    void update();

    Coordinate getPlayCoor();

    Coordinate getPlayerDimensions();
  }
}
