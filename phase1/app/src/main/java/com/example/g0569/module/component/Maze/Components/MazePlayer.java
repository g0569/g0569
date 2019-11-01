package com.example.g0569.module.component.Maze.Components;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.MazeItem.Wall;
import com.example.g0569.module.component.Player;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

import java.util.ArrayList;

public class MazePlayer extends Player {

  private float[] direction = new float[2];
  private Bitmap appearance;

  public MazePlayer(Game game) {
    super(game);
    float x = ((MazeGame) this.getGame()).getStartpoint().getX();
    float y = ((MazeGame) this.getGame()).getStartpoint().getY();
    this.coordinate = new Coordinate(x, y);

    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearance = BitmapFactory.decodeResource(resources, R.drawable.pacman);
    appearance =
        Bitmap.createScaledBitmap(
            appearance,
            (int) ((MazeGame) this.getGame()).getGrid_width(),
            (int) ((MazeGame) this.getGame()).getGrid_height(),
            false);

    direction[0] = 0f;
    direction[1] = 0f;
  }
  /**
   * Draws the button, red and big
   *
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.WHITE);

    canvas.drawBitmap(
        appearance,
        this.coordinate.getX() * ((MazeGame) this.getGame()).getGrid_width(),
        this.coordinate.getY() * ((MazeGame) this.getGame()).getGrid_height(),
        paint);
  }

  @Override
  public void action() {
    this.move();
  }

  /** Move the player around (left and right) Detect the wall and NPCs. */
  public void move() {
    float targetX = this.coordinate.getX() + direction[0];
    float targetY = this.coordinate.getY() + direction[1];
    try {
      if (!(((MazeGame) this.getGame()).getMyMazeItem()[(int) targetY][(int) targetX]
          instanceof Wall)) {
        this.coordinate.setX(targetX);
        this.coordinate.setY(targetY);
      }
    } catch (Exception e) {
    }
    this.interact();
  }

  /**
   * Detect the NPCs around the player.
   *
   * @return an arraylist of NPC around
   */
  public ArrayList<NPC> getNPCAround() {
    int currX = (int) this.coordinate.getX();
    int currY = (int) this.coordinate.getY();
    Item currItem;
    ArrayList<NPC> NPCAround = new ArrayList<>();
    if (direction[0] != 1) {
      currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY][currX + 1];
      if (currItem instanceof NPC) {
        NPCAround.add((NPC) currItem);
      }
    }
    if (direction[0] != -1) {
      currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY][currX - 1];
      if (currItem instanceof NPC) {
        NPCAround.add((NPC) currItem);
      }
    }
    if (direction[1] != 1) {
      currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY + 1][currX];
      if (currItem instanceof NPC) {
        NPCAround.add((NPC) currItem);
      }
    }
    if (direction[1] != -1) {
      currItem = ((MazeGame) this.getGame()).getMyMazeItem()[currY - 1][currX];
      if (currItem instanceof NPC) {
        NPCAround.add((NPC) currItem);
      }
    }
    return NPCAround;
  }

  /**
   * Get the player's direction
   *
   * @return the array of direction
   */
  public float[] getDirection() {
    return direction;
  }

  /** Interact with the NPC around. cannot continue moving after detecting right now. */
  public void interact() {
    for (NPC i : this.getNPCAround()) {
      i.pop();
      // it leads an error, cannot find symbol pop()
      //      Looper.prepare();
      //      Toast.makeText(getGame().getGameManager().getMainActivity(), "test",
      // Toast.LENGTH_SHORT)
      //          .show();
      //      Looper.loop();
    }
  }

  /**
   * Set the direction of the player
   *
   * @param x the target x-axis of the player
   * @param y the target y-axis of the player
   */
  public void setDirection(int x, int y) {
    this.direction[0] = x;
    this.direction[1] = y;
  }
}
