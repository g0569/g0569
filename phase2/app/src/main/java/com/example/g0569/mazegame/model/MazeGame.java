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

  public MazeGame(MazeContract.Presenter presenter, Inventory inventory, SaveMaze saveMaze) {
    super();
    this.presenter = presenter;
    this.inventory = inventory;
    this.unbuiltNPC = inventory.getNonCollectedItem().size();
    this.save = saveMaze;
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
    stopWatch = new MazeStopWatch(Constants.MAZETIMER);
    if (save.isEmpty()) {
      mazeGrid = MazeGenerator.generate(inventory.getNonCollectedItem().size());
      //    Button = new BaseButton(this)
      getStopWatch().start();
      this.save();
    } else {
      this.load();
    }
  }

  @Override
  public void pause() {
    stopWatch.pause();
    presenter.getMazeView().stopView();
    save.setMazeGrid(mazeGrid);
    save.setPlayerCoordinate(mazePlayer.getCoordinate());
    save.setRemainTime(stopWatch.getRemainTime());
  }

  public void load(SaveMaze saveMaze) {
    //    stopWatch.resume();
    this.save = saveMaze;
    presenter.getMazeView().resumeView();
    mazeGrid = saveMaze.getMazeGrid();
    mazePlayer.setCoordinate(saveMaze.getPlayerCoordinate());
    stopWatch.setRemainTime(saveMaze.getRemainTime());
    stopWatch.resume();
  }

  public void load() {
    //    stopWatch.resume();
    presenter.getMazeView().resumeView();
    mazeGrid = save.getMazeGrid();
    mazePlayer.setCoordinate(save.getPlayerCoordinate());
    stopWatch.setRemainTime(save.getRemainTime());
    stopWatch.resume();
  }

  public SaveMaze save() {
    save.setPlayerCoordinate(mazePlayer.getCoordinate());
    save.setRemainTime(stopWatch.getRemainTime());
    save.setMazeGrid(mazeGrid);
    return save;
  }

  Coordinate getStartPoint() {
    return this.startpoint;
  }

  public void setMyMazeItem() {
    //    MazeHelper.loadMaze(myMazeItem, this);
  }

  public MazeStopWatch getStopWatch() {
    return stopWatch;
  }

  public Item[][] getMyMazeItem() {
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
  public void timeReach() {

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

  public Coordinate getPlayerDimensions() {
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
    //      if (inventory.getNonCollectedItem().size() == 1)
    // System.out.println(this.getInventory().getNonCollectedItem().get(unbuiltNPC).getType());
    //    System.out.println(unbuiltNPC);
    this.getMyMazeItem()[y][x] = this.getInventory().getNonCollectedItem().get(unbuiltNPC);
    //    System.out.println(this.getInventory().getNonCollectedItem().get(unbuiltNPC - 1));
    return this.getInventory().getNonCollectedItem().get(unbuiltNPC);
  }

  /** TODO delete from 2d array mazeItem; check the default value of Item */
  public void deleteItem(int x, int y, NPC npc) {
    this.getMyMazeItem()[y][x] = null;
    this.inventory.deleteNoneCollectedItem(npc);
  }
}
