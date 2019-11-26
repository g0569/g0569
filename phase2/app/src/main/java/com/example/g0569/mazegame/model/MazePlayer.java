package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.Player;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;

public class MazePlayer extends Player {

  private Coordinate direction;
  private Coordinate appearenceSize;
  private Coordinate coordinate;
//  private MazeGame game;
  private ArrayList<NPC> collectedNPC;

  /** @param game */
  MazePlayer(MazeGame game) {
    super(game);
//    this.game = game;
    float x = ((MazeGame) this.getGame()).getStartpoint().getX();
    float y = ((MazeGame) this.getGame()).getStartpoint().getY();
    this.coordinate = new Coordinate(x, y);
    appearenceSize = game.getPlayerDimensions();
  direction = Coordinate.create(0,0);
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void update() {
    this.move();
    this.getNPCAround();
  }

  public Coordinate getDirection() {
    return direction;
  }

  void setDirection(Coordinate direction) {
    this.direction = direction;
  }

  /** Move the player around (left and right) Detect the wall and NPCs. */
  private void move() {
    int[] border = new int[2];
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
    try {
      if (!(((MazeGame) this.getGame())
              .getMyMazeItem()[(int) (targetY + playerHeight * border[1])][
              (int) (targetX + playerWidth * border[0])]
          instanceof Wall)) {
        this.coordinate.setX(targetX);
        this.coordinate.setY(targetY);
      }
    } catch (ArrayIndexOutOfBoundsException ignored) {
    }
    //    this.interact();
  }

  /**
   * Detect the NPCs around the player.
   */
  private void getNPCAround() {
    int currX = (int) this.coordinate.getX();
    int currY = (int) this.coordinate.getY();
    Item currItemX;
    Item currItemY;
    for (int i = -2; i <= 2; i++) {
      currItemX = ((MazeGame) this.getGame()).getMyMazeItem()[currY][currX + i];
      if (currItemX instanceof NPC) {
        this.collectedNPC.add((NPC)currItemX);
        interact((NPC)currItemX);
      }
      currItemY = ((MazeGame) this.getGame()).getMyMazeItem()[currY + i][currX];
      if (currItemY instanceof NPC) {
        this.collectedNPC.add((NPC)currItemY);
        interact((NPC)currItemY);
      }
    }
  }

  /**
   * 1. add to inventory
   * 2. delete from the maze
   * @param npc
   * @return
   */
  public boolean interact(NPC npc) {
    return true;
  }

}
