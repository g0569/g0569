package com.example.g0569.mazegame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.mazegame.model.SaveMaze;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

/** The interface Maze contract. */
public interface MazeContract {
  /** The interface View. */
  interface View extends BaseView<Presenter> {
    /** Init view. */
    void initView();

    /**
     * Draw maze.
     *
     * @param mazeGrid the maze grid
     */
    void drawMaze(int[][] mazeGrid);

    /**
     * Draw player.
     *
     * @param coor the coor
     */
    void drawPlayer(Coordinate coor);

    /**
     * Draw npc.
     *
     * @param coor the coor
     * @param npc the npc
     */
    void drawNPC(Coordinate coor, NPC npc);

    /**
     * Gets player dimensions.
     *
     * @return the player dimensions
     */
    Coordinate getPlayerDimensions();

    /**
     * Delete grid item.
     *
     * @param x the x
     * @param y the y
     * @param mazeGrid the maze grid
     */
    void deleteGridItem(int x, int y, int[][] mazeGrid);

    /** Stop view when the game stops or pauses. */
    void stopView();

    /** Resume view after pausing the game. */
    void resumeView();
  }

  /** The interface Presenter. */
  interface Presenter extends BasePresenter {
    /**
     * Move player to the assigned coordinate.
     *
     * @param coordinate the coordinate for the player to move
     */
    void movePlayer(Coordinate coordinate);

    /** Stop move. */
    void stopMove();

    /**
     * Get maze grid int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    int[][] getMazeGrid();

    /** Update. */
    void update();

    /**
     * Gets player coor.
     *
     * @return the player coor
     */
    Coordinate getPlayerCoor();

    /**
     * Gets player dimensions.
     *
     * @return the player dimensions
     */
    Coordinate getPlayerDimensions();

    /**
     * Add collected npc.
     *
     * @param npc the npc
     */
    void addCollectedNPC(NPC npc);

    /**
     * Gets npc type.
     *
     * @param npc the npc
     * @return the npc type
     */
    String getNPCType(NPC npc);

    /**
     * Gets maze view.
     *
     * @return the maze view
     */
    MazeContract.View getMazeView();

    /**
     * add item to maze item. @param x the x
     *
     * @param y the y
     * @return the npc
     */
    NPC addItemToMazeItem(int x, int y);

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    Inventory getInventory();

    /**
     * Gets remain time of the stop watch.
     *
     * @return the remain time
     */
    int getRemainTime();

    /** Pause stop watch. */
    void pauseStopWatch();

    /** Resume stop watch. */
    void resumeStopWatch();

    /** Pause. */
    void pause();

    /**
     * Save save maze.
     *
     * @return the save maze
     */
    SaveMaze save();

    /**
     * Load.
     *
     * @param saveMaze the save maze
     */
    void load(SaveMaze saveMaze);

  }
}
