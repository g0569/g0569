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
  private Coordinate startpoint;
  private Inventory inventory;
  private Item[][] myMazeItem = new Item[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
  private int[][] mazeGrid;
  private MazePlayer mazePlayer;
  private int unbuiltNPC;
  private MazeStopWatch stopWatch;
  private SaveMaze save;

  public MazeGame(MazeContract.Presenter presenter, Inventory inventory) {
    super();
    this.presenter = presenter;
    this.inventory = inventory;
    this.unbuiltNPC = inventory.getNonCollectedItem().size();
    this.save = new SaveMaze();
  }

  public MazeContract.Presenter getPresenter() {
    return presenter;
  }

  public MazeContract.View getView() {
    return view;
  }

  public MazePlayer getMazePlayer() {
    return mazePlayer;
  }

  public void onStart() {
    startpoint = Coordinate.create(0, 0);
    mazePlayer = new MazePlayer(this);
    mazeGrid =
        MazeGenerator.generate(inventory.getNonCollectedItem().size());
    stopWatch = new MazeStopWatch(Constants.MAZETIMER, false);
    //    Button = new BaseButton(this)
    getStopWatch().start();
  }

  @Override
  public void pause() {
    stopWatch.pause();
    presenter.getMazeView().stopView();
    save.setMazeGrid(mazeGrid);
    save.setPlayerCoordinate(mazePlayer.getCoordinate());
    save.setStopWatch(stopWatch);
  }

  @Override
  public void load() {
//    stopWatch.resume();
    presenter.getMazeView().resumeView();
    mazeGrid = save.getMazeGrid();
    mazePlayer.setCoordinate(save.getPlayerCoordinate());
    stopWatch = save.getStopWatch();
    stopWatch.resume();
  }

  Coordinate getStartPoint() {
    return this.startpoint;
  }

  public MazeStopWatch getStopWatch() {
    return stopWatch;
  }

  Item[][] getMyMazeItem() {
    return myMazeItem;
  }

  public void update() {
    mazePlayer.update();
    getStopWatch().update();
    if (getStopWatch().getRemainTime() == -1) {
      timeReach();
    }
  }

  // TODO show inventory page
  private void timeReach() {

    getStopWatch().stop();
    presenter.getMazeView().stopView();
  }

  //  public void showStatistic() {
  //    // TODO
  //    List<String> statistic = new ArrayList<String>();
  //    statistic.add("Number of NPCs You catch: 1");
  //    getGameManager().showStatistic(statistic);
  //  }

  public int[][] getMazeGrid() {
    return mazeGrid;
  }

  public Inventory getInventory() {
    return inventory;
  }

  Coordinate getPlayerDimensions() {
    return presenter.getPlayerDimensions();
  }

  public void movePlayer(Coordinate coordinate) {
    getMazePlayer().setDirection(coordinate);
  }

  /**
   * @param x
   * @param y
   * @return
   */
  public NPC addItemToMazeItem(int x, int y) {
    unbuiltNPC -= 1;
    if (unbuiltNPC == -1) unbuiltNPC = inventory.getNonCollectedItem().size() - 1;
    this.getMyMazeItem()[y][x] = this.getInventory().getNonCollectedItem().get(unbuiltNPC);
    return this.getInventory().getNonCollectedItem().get(unbuiltNPC);
  }

  void deleteItem(int x, int y, NPC npc) {
    this.getMyMazeItem()[y][x] = null;
    this.inventory.deleteNoneCollectedItem(npc);
  }
}
