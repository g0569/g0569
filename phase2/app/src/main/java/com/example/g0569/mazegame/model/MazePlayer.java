package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.Player;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;

public class MazePlayer extends Player {

  Coordinate direction;
  private Coordinate appearenceSize;
  private Coordinate coordinate;

  public MazePlayer(MazeGame game) {
    super(game);
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
  }

  public Coordinate getDirection() {
    return direction;
  }

  public void setDirection(Coordinate direction) {
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
        System.out.println(targetY);
      }
    } catch (ArrayIndexOutOfBoundsException ignored) {
    }
    //    this.interact();
  }

  /**
   * Detect the NPCs around the player.
   *
   * @return an arraylist of NPC around
   */
  private ArrayList<NPC> getNPCAround() {
    int currX = (int) this.coordinate.getX();
    int currY = (int) this.coordinate.getY();
    Item currItem;
    ArrayList<NPC> NPCAround = new ArrayList<>();
    for (int i = -2; i <= 2; i++) {
      currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY][currX + i];
      if (currItem instanceof NPC) {
        NPCAround.add((NPC) currItem);
      }
      currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY + i][currX];
      if (currItem instanceof NPC) {
        NPCAround.add((NPC) currItem);
      }
    }
    return NPCAround;
  }

  /**
   * Interact with the NPC around. cannot continue moving after detecting right now. Will interact
   * with NPC (but not now...
   */
  public boolean interact() {
    //    for (NPC i : this.getNPCAround()) {
    //      i.pop();
    // it leads an error, cannot find symbol pop()
    //      Looper.prepare();
    //      Toast.makeText(getGame().getGameManager().getMainActivity(), "test",
    // Toast.LENGTH_SHORT)
    //          .show();
    //      Looper.loop();
    //    }
    return this.getNPCAround().size() != 0;
  }

  @Override
  public void action() {}
}
