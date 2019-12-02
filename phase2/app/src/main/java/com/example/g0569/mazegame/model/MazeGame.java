package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.mazegame.MazeContract;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public class MazeGame extends BaseGame {

  private MazeContract.Presenter presenter;
  private MazeContract.View view;
  private Coordinate startPoint;
  private Inventory inventory;
  private Item[][] myMazeItem = new Item[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
  private int[][] mazeGrid;
  private MazePlayer mazePlayer;
  private int unbuiltNPC;
  private int collectedNPC = 0;
  private MazeStopWatch stopWatch;
  private SaveMaze save;

  public MazeGame(MazeContract.Presenter presenter, Inventory inventory, SaveMaze saveMaze) {
    super();
    this.presenter = presenter;
    this.inventory = inventory;
    this.unbuiltNPC = inventory.getNonCollectedItem().size();
    this.save = saveMaze;
  }

  /**
   * Return the presenter of the game.
   *
   * @return the presenter of the mazeGame
   */
  public MazeContract.Presenter getPresenter() {
    return presenter;
  }

  /**
   * Return the game view
   *
   * @return the view of the mazeGame
   */
  public MazeContract.View getView() {
    return view;
  }

  /**
   * Getter of the player in the game.
   *
   * @return the player of mazeGame
   */
  public MazePlayer getMazePlayer() {
    return mazePlayer;
  }

  /**
   * Instantiates everything needed for mazeGame to run smoothly Makes a startPoint, mazePlayer,
   * mazeGrid, stopWatch
   */
  public void onStart() {
    startPoint = Coordinate.create(0, 0);
    mazePlayer = new MazePlayer(this);
    stopWatch = new MazeStopWatch(Constants.MAZETIMER);
    if (save.isEmpty()) {
      mazeGrid = MazeGenerator.generate(inventory.getNonCollectedItem().size());
      getStopWatch().start();
      this.save();
    } else {
      this.load();
    }
  }

  /**
   * Pause everything in the mazeGame (including stopWatch, mazePlayer coordinate and mazeGrid and
   * set them to save
   */
  @Override
  public void pause() {
    stopWatch.pause();
    presenter.getMazeView().stopView();
    save.setMazeGrid(mazeGrid);
    save.setPlayerCoordinate(mazePlayer.getCoordinate());
    save.setRemainTime(stopWatch.getRemainTime());
  }

  /**
   * Reload all the data stored in the saveMaze
   * @param saveMaze store the state of components when last paused
   */
  public void load(SaveMaze saveMaze) {
    //    stopWatch.resume();
    this.save = saveMaze;
    presenter.getMazeView().resumeView();
    mazeGrid = saveMaze.getMazeGrid();
    mazePlayer.setCoordinate(saveMaze.getPlayerCoordinate());
    stopWatch.setRemainTime(saveMaze.getRemainTime());
    stopWatch.resume();
  }

  /**
   * Resume the state of components
   */
  public void load() {
//    stopWatch.resume();
    presenter.getMazeView().resumeView();
    mazeGrid = save.getMazeGrid();
    mazePlayer.setCoordinate(save.getPlayerCoordinate());
    stopWatch.setRemainTime(save.getRemainTime());
    stopWatch.resume();
  }

  /**
   * Save the status of all the components
   * @return the data load
   */
  public SaveMaze save() {
    save.setPlayerCoordinate(mazePlayer.getCoordinate());
    save.setRemainTime(stopWatch.getRemainTime());
    save.setMazeGrid(mazeGrid);
    return save;
  }

  /**
   * Getter of the startPoint
   *
   * @return the startPoint of the maze
   */
  Coordinate getStartPoint() {
    return this.startPoint;
  }

  /**
   * Getter of the stopWatch
   *
   * @return the stopWatch of the mazeGame
   */
  public MazeStopWatch getStopWatch() {
    return stopWatch;
  }

  /**
   * Getter of the mazeItem array
   *
   * @return the mazeItem of the mazeGame
   */
  Item[][] getMyMazeItem() {
    return myMazeItem;
  }

  /**
   * Update constantly with the mazePlayer and stopWatch and check if the time is up to stop the
   * game.
   */
  public void update() {
    mazePlayer.update();
    getStopWatch().update();
    if (getStopWatch().getRemainTime() == -1) {
      timeReach();
    }
  }

  // TODO show inventory page

  /** Instruction to stop the stopWatch and stop mazeView when time is reached. */
  private void timeReach() {

    getStopWatch().stop();
    presenter.getMazeView().stopView();
  }

  /**
   * Getter of the mazeGrid
   *
   * @return mazeGrid of the mazeGame
   */
  public int[][] getMazeGrid() {
    return mazeGrid;
  }

  /**
   * Getter of the inventory
   *
   * @return the inventory of the game
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Return the dimensions of the mazePlayer
   *
   * @return the dimensions of the mazePlayer to measure its width and height
   */
  Coordinate getPlayerDimensions() {
    return presenter.getPlayerDimensions();
  }

  /**
   * instruction to get the player moved
   *
   * @param coordinate the direction that the player is going to move to.
   */
  public void movePlayer(Coordinate coordinate) {
    getMazePlayer().setDirection(coordinate);
  }

  /**
   * add the NPC instance to the indicated index in the mazeItem.
   *
   * @param x the second index in the mazeItem array, indicating x coordinate of the added item
   * @param y the first index in the mazeItem array, indicating y coordinate of the added item
   * @return the NPC instance that is added to the mazeItem
   */
  public NPC addItemToMazeItem(int x, int y) {
    unbuiltNPC -= 1;
    if (unbuiltNPC == -1) unbuiltNPC = inventory.getNonCollectedItem().size() - 1;
    this.getMyMazeItem()[y][x] = this.getInventory().getNonCollectedItem().get(unbuiltNPC);
    return this.getInventory().getNonCollectedItem().get(unbuiltNPC);
  }

  /**
   * Delete the NPC on its index in the mazeItem array
   *
   * @param x the x coordinate of the NPC
   * @param y the y coordinate of the NPC
   * @param npc the NPC that is targeted to remove from the mazeItem
   */
  void deleteItem(int x, int y, NPC npc) {
    this.getMyMazeItem()[y][x] = null;
    this.inventory.deleteNoneCollectedItem(npc);
//    if (inventory.getNonCollectedItem().size() == 0){
//      presenter.showInventory();
//    }
  }
}
