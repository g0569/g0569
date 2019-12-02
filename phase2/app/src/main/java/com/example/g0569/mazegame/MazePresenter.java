package com.example.g0569.mazegame;

import com.example.g0569.mazegame.model.MazeGame;
import com.example.g0569.mazegame.model.SaveMaze;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public class MazePresenter implements MazeContract.Presenter {
  private MazeContract.View mazeView;
  private MazeGame mazeGame;
  private Inventory inventory;

  MazePresenter(MazeContract.View mazeView, Inventory inventory, SaveMaze saveMaze) {
    this.mazeView = mazeView;
    this.mazeView.setPresenter(this);
    this.mazeGame = new MazeGame(this, inventory, saveMaze);
    this.inventory = inventory;
  }

  @Override
  public void start() {
    //    mazeView.drawMaze(mazeGame.getMazeGrid());
    mazeGame.onStart();
  }

  @Override
  public MazeContract.View getMazeView() {
    return mazeView;
  }

  @Override
  public void movePlayer(Coordinate coordinate) {
    mazeGame.movePlayer(coordinate);
  }

  @Override
  public void stopMove() {
    mazeGame.movePlayer(Coordinate.create(0, 0));
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
  public NPC addItemToMazeItem(int x, int y) {
    return mazeGame.addItemToMazeItem(x, y);
  }

  @Override
  public Coordinate getPlayerCoor() {
    Coordinate playerCoor = Coordinate.create(0, 0);
    try {
      playerCoor = mazeGame.getMazePlayer().getCoordinate();
    } catch (NullPointerException ignored) {
    } finally {
      return playerCoor;
    }
  }

  @Override
  public void addCollectedNPC(NPC npc) {
    inventory.addCollectedItem(npc);
  }

  @Override
  public Coordinate getPlayerDimensions() {
    return mazeView.getPlayerDimensions();
  }

  @Override
  public Inventory getInventory() {
    return inventory;
  }

  @Override
  public String getNPCType(NPC npc) {
    return npc.getType();
  }

  @Override
  public int getRemainTime() {
    return mazeGame.getStopWatch().getRemainTime();
  }

  @Override
  public void pauseStopWatch() {
    mazeGame.getStopWatch().pause();
  }

  @Override
  public void resumeStopWatch() {
    mazeGame.getStopWatch().resume();
  }

  @Override
  public void pause() {
    mazeGame.pause();
  }

  @Override
  public SaveMaze save() {
    return mazeGame.save();
  }

  @Override
  public void load(SaveMaze saveMaze) {
    mazeGame.load(saveMaze);
  }
}
