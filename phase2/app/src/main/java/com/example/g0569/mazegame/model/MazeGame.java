package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.mazegame.MazeContract;
import com.example.g0569.mazegame.MazeView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

/** The type Maze game. */
public class MazeGame extends BaseGame {

  private MazeContract.Presenter presenter;
  private MazeView view;
  private Coordinate startpoint;
  private Inventory inventory;
  private Item[][] myMazeItem = new Item[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
  private int[][] mazeGrid;
  private MazePlayer mazePlayer;
  private int unbuiltNPC;
  private MazeStopWatch stopWatch;

  /**
   * Gets presenter.
   *
   * @return the presenter
   */
  public MazeContract.Presenter getPresenter() {
    return presenter;
  }

  /**
   * Gets view.
   *
   * @return the view
   */
  public MazeContract.View getView() {
    return view;
  }

  /**
   * Instantiates a new Maze game.
   *
   * @param presenter the presenter
   * @param inventory the inventory
   */
  public MazeGame(MazeContract.Presenter presenter, Inventory inventory) {
    super();
    this.presenter = presenter;
    this.inventory = inventory;
    this.unbuiltNPC = inventory.getNonCollectedItem().size();
  }

  /**
   * Gets maze player.
   *
   * @return the maze player
   */
  public MazePlayer getMazePlayer() {
    return mazePlayer;
  }

  /** On start. */
  public void onStart() {
//    startpoint = Coordinate.create(0, 0);
    mazePlayer = new MazePlayer(this);
    mazeGrid =
        MazeGenerator.generate(
            Constants.GRID_HEIGHT, Constants.GRID_WIDTH, inventory.getNonCollectedItem().size());
    stopWatch = new MazeStopWatch(60);
    //    Button = new BaseButton(this)
    getStopWatch().start();
  }

  @Override
  public void pause() {
      stopWatch.pause();
      presenter.getMazeView().stopView();
  }

  @Override
  public void load() {
      stopWatch.resume();
      presenter.getMazeView().resumeView();
  }

  //  private Coordinate getStartPoint() {
  //    return this.startpoint;
  //  }

  //  public void setMyMazeItem() {
  //    MazeHelper.loadMaze(myMazeItem, this);
  //  }

  /**
   * Gets stop watch.
   *
   * @return the stop watch
   */
  public MazeStopWatch getStopWatch() {
    return stopWatch;
  }

  /**
   * Get my maze item item [ ] [ ].
   *
   * @return the item [ ] [ ]
   */
  Item[][] getMyMazeItem() {
    return myMazeItem;
  }

  /** Update. */
  public void update() {
    mazePlayer.update();
    getStopWatch().update();
    if (getStopWatch().getRemainTime() == -1){
      timeReach();
    }
  }

  //TODO show inventory page
  private void timeReach(){

      getStopWatch().stop();
      presenter.getMazeView().stopView();

  }

  //  public void showStatistic() {
  //    // TODO
  //    List<String> statistic = new ArrayList<String>();
  //    statistic.add("Number of NPCs You catch: 1");
  //    getGameManager().showStatistic(statistic);
  //  }

  /**
   * Get maze grid int [ ] [ ].
   *
   * @return the int [ ] [ ]
   */
  public int[][] getMazeGrid() {
    return mazeGrid;
  }

  /**
   * Gets inventory.
   *
   * @return the inventory
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Gets player dimensions.
   *
   * @return the player dimensions
   */
  Coordinate getPlayerDimensions() {
    return presenter.getPlayerDimensions();
  }

  /**
   * Move player.
   *
   * @param coordinate the coordinate
   */
  public void movePlayer(Coordinate coordinate) {
    getMazePlayer().setDirection(coordinate);
  }

  /**
   * Add item to maze item npc.
   *
   * @param x the x
   * @param y the y
   * @return npc
   */
  public NPC addItemToMazeItem(int x, int y) {
      unbuiltNPC -= 1;
      if (unbuiltNPC == -1) unbuiltNPC = inventory.getNonCollectedItem().size() - 1;
//      if (inventory.getNonCollectedItem().size() == 1) System.out.println(this.getInventory().getNonCollectedItem().get(unbuiltNPC).getType());
//    System.out.println(unbuiltNPC);
    this.getMyMazeItem()[y][x] = this.getInventory().getNonCollectedItem().get(unbuiltNPC);
//    System.out.println(this.getInventory().getNonCollectedItem().get(unbuiltNPC - 1));
    return this.getInventory().getNonCollectedItem().get(unbuiltNPC);
  }

  /**
   * TODO delete from 2d array mazeItem; check the default value of Item @param x the x
   *
   * @param y the y
   * @param npc the npc
   */
  void deleteItem(int x, int y, NPC npc) {
    this.getMyMazeItem()[y][x] = null;
    this.inventory.deleteNoneCollectedItem(npc);
  }
}
