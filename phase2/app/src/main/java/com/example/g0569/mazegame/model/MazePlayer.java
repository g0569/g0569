package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.Player;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.NPC;

/** The type Maze player. */

public class MazePlayer extends Player {

  private Coordinate direction;
  private Coordinate appearanceSize;
  private Coordinate coordinate;
  private MazeGame game;
  //  private ArrayList<NPC> collectedNPC;

  /**
   * Instantiates a new Maze player.
   *
   * @param game the game
   */
  MazePlayer(MazeGame game) {
    super();
    this.game = game;
    float x = this.getGame().getStartPoint().getX();
    float y = this.getGame().getStartPoint().getY();
    this.coordinate = Coordinate.create(x, y);
    appearanceSize = game.getPlayerDimensions();
    direction = Coordinate.create(0, 0);
  }

  public MazeGame getGame() {
    return game;
  }

  /**
   * Gets coordinate.
   *
   * @return the coordinate
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public void update() {
    this.move();
    this.getNPCAround();
  }

  /**
   * Gets direction.
   *
   * @return the direction
   */
  public Coordinate getDirection() {
    return direction;
  }

  /**
   * Sets direction.
   *
   * @param direction the direction
   */
  void setDirection(Coordinate direction) {
    this.direction = direction;
  }

  /** Move the player around (left and right) Detect the wall and NPCs. */
  private void move() {
    int[] border = new int[2];
    /** fix the inconsistent in player size and coordinate. */
    if (direction.getX() > 0f) {
      border[0] = 1;
    }
    if (direction.getY() > 0f) {
      border[1] = 1;
    }

    float targetX = this.coordinate.getX() + direction.getX();
    float targetY = this.coordinate.getY() + direction.getY();
    float playerWidth = appearanceSize.getX() / Constants.GRID_WIDTH;
    float playerHeight = appearanceSize.getY() / Constants.GRID_HEIGHT;

    int[][] mazeGrid = game.getMazeGrid();
    boolean inRangeY = ((int) (targetY + playerHeight * border[1])) <= (Constants.GRID_HEIGHT - 1) && ((int) (targetY + playerHeight * border[1])) >= 0;
    boolean inRangeX = ((int) (targetX + playerWidth * border[0])) <= (Constants.GRID_WIDTH - 1) && ((int) (targetX + playerWidth * border[0])) >= 0 ;
    if (inRangeX && inRangeY) {
      if (!(mazeGrid[(int) (targetY + playerHeight * border[1])][
              (int) (targetX + playerWidth * border[0])]
          == 0)) {
        this.coordinate.setX(targetX);
        this.coordinate.setY(targetY);
      }
      }
    //    this.collectedNPC();
  }

  /** Detect the NPCs around the player. */
  private void getNPCAround() {
    int currX = (int) this.coordinate.getX();
    int currY = (int) this.coordinate.getY();
    Item currItem = this.getGame().getMyMazeItem()[currY][currX];
    if (currItem instanceof NPC) {
      collectedNPC((NPC) currItem);
      deleteItem(currX, currY, this.getGame().getMazeGrid(), (NPC) currItem);
    }
  }

  /**
   * Todo 1. add to inventory: access from presenter; get from MazeActivity Bundle and be a
   * presentor constructor parameter 2. delete from the maze 3. move in inventory collect NPC in the
   * inventory
   *
   * @param npc the npc
   * @return boolean
   */
  public void collectedNPC(NPC npc) {

    this.game.getPresenter().addCollectedNPC(npc);
  }

  /**
   * delete NPC in mazeGrid and mazeItem respectively.
   *
   * @param x
   * @param y
   * @param maze
   */
  public void deleteItem(int x, int y, int[][] maze, NPC npc) {
    this.game.getPresenter().getMazeView().deleteGridItem(x, y, maze);
    this.game.deleteItem(x, y, npc);
  }
}
