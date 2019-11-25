package com.example.g0569.mazegame;

import com.example.g0569.mazegame.model.MazeGame;
import com.example.g0569.utils.Coordinate;

public class MazePresenter implements MazeContract.Presenter {
  private MazeContract.View mazeView;
  private MazeGame mazeGame;
  public MazePresenter(MazeContract.View mazeView) {
    this.mazeView = mazeView;
    this.mazeView.setPresenter(this);
    this.mazeGame = new MazeGame(this);
  }

  @Override
  public void start() {
    mazeView.drawMaze(mazeGame.getMazeGrid());
    mazeGame.onStart();
  }

  public void toChessGame(String args) {}

  @Override
  public void movePlayer(Coordinate coordinate) {
    mazeGame.movePlayer(coordinate);
  }

  @Override
  public void stopMove() {
    mazeGame.movePlayer(Coordinate.create(0,0));
  }

  @Override
  public int[][] getMazeGrid() {
    return mazeGame.getMazeGrid();
  }

  @Override
  public void update() {
    mazeGame.update();
  }

  @Override
  public Coordinate getPlayerCoor() {
    Coordinate playerCoor = Coordinate.create(0,0);;
    try{
      playerCoor = mazeGame.getMazePlayer().getCoordinate();
    }
    catch (NullPointerException e) {
    }
    finally{
      return playerCoor;
    }
  }

  @Override
  public Coordinate getPlayerDimensions() {
    return mazeView.getPlayerDimensions();
  }
}
