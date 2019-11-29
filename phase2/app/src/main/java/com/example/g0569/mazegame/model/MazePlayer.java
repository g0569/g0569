package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.Player;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.NPC;

/** The type Maze player. */
// TODO START POINT
public class MazePlayer extends Player {

  private Coordinate direction;
  private Coordinate appearenceSize;
  private Coordinate coordinate;
  private MazeGame game;
//  private ArrayList<NPC> collectedNPC;

  /**
   * Instantiates a new Maze player.
   *
   * @param game the game
   */
  MazePlayer(MazeGame game) {
    super(game);
    this.game = game;
    float x = ((MazeGame) this.getGame()).getStartPoint().getX();
    float y = ((MazeGame) this.getGame()).getStartPoint().getY();
    this.coordinate = new Coordinate(x, y);
    appearenceSize = game.getPlayerDimensions();
  direction = Coordinate.create(0,0);
  }

  /**
   * Gets coordinate.
   *
   * @return the coordinate
   */
  public Coordinate getCoordinate() {
    return coordinate;
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
    /**
     * fix the inconsistent in player size and coordinate.
     */
    if (direction.getX() > 0f) {
      border[0] = 1;
    }
    if (direction.getY() > 0f) {
      border[1] = 1;
    }

    float targetX = this.coordinate.getX() + direction.getX();
    float targetY = this.coordinate.getY() + direction.getY();
    float playerWidth = appearenceSize.getX() / Constants.GRID_WIDTH;
    float playerHeight = appearenceSize.getY() / Constants.GRID_HEIGHT;

    int[][]  mazeGrid = game.getMazeGrid();
    try {
      if (!(mazeGrid[(int) (targetY + playerHeight * border[1])][
              (int) (targetX + playerWidth * border[0])]
          == 0 )) {
        this.coordinate.setX(targetX);
        this.coordinate.setY(targetY);
      }
    } catch (ArrayIndexOutOfBoundsException ignored) {
    }
    //    this.collectedNPC();
  }

  /**
   * TODO build a mazeitem 2d array.
   * Detect the NPCs around the player.
   */
  private void getNPCAround() {
    int currX = (int) this.coordinate.getX();
    int currY = (int) this.coordinate.getY();
//    for (int i = -2; i <= 2; i++) {
//      try{
//        currItemX = ((MazeGame) this.getGame()).getMyMazeItem()[currY][currX + i];
//        if (currItemX instanceof NPC) {
//          collectedNPC((NPC)currItemX);
//          deleteItem(currX+1, currY, ((MazeGame)this.getGame()).getMazeGrid());
//        }
//        currItemY = ((MazeGame) this.getGame()).getMyMazeItem()[currY + i][currX];
//        if (currItemY instanceof NPC) {
//          collectedNPC((NPC)currItemY);
//          deleteItem(currX, currY+1, ((MazeGame)this.getGame()).getMazeGrid());
//        }
//      }catch (ArrayIndexOutOfBoundsException ignored){
//
//      }
    Item currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY][currX];
    if (currItem instanceof NPC){
      collectedNPC((NPC)currItem);
      deleteItem(currX, currY, ((MazeGame)this.getGame()).getMazeGrid());
    }



  }

  /**
   * Todo 1. add to inventory: access from presenter; get from MazeActivity Bundle and be a presentor constructor parameter 2. delete from the maze 3. move in inventory
   * collect NPC in the inventory
   * @param npc the npc
   * @return boolean
   */
  public void collectedNPC(NPC npc) {

    this.game.getPresenter().addCollectedNPC(npc);
  }

  /**
   * delete NPC in mazeGrid and mazeItem respectively.
   * @param x
   * @param y
   * @param maze
   */
  public void deleteItem(int x, int y, int[][] maze){
    this.game.getPresenter().getMazeView().deleteGridItem(x, y, maze);
    this.game.deleteItem(x, y);
  }
}
