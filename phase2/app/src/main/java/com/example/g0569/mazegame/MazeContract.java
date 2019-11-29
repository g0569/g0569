package com.example.g0569.mazegame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public interface MazeContract {
  interface View extends BaseView<Presenter> {
    void initView();
    void drawMaze(int[][] mazeGrid);
    void drawPlayer(Coordinate coor);
    void drawNPC(Coordinate coor, NPC npc);

    Coordinate getPlayerDimensions();

    void deleteGridItem(int x, int y, int[][] mazeGrid);
  }

  interface Presenter extends BasePresenter {
    void movePlayer(Coordinate coordinate);

    void stopMove();

    int[][] getMazeGrid();

    void update();

    Coordinate getPlayerCoor();

    Coordinate getPlayerDimensions();

    void addCollectedNPC(NPC npc);

    String getNPCName(NPC npc);

    String getNPCType(NPC npc);

    MazeContract.View getMazeView();

    /**
    add item to maze item.
     */
    NPC addItemToMazeItem(int x, int y);

    Inventory getInventory();
  }
}
