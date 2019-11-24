package com.example.g0569.mazegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.Player;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;

public class MazePlayer extends Player {

  private float[] direction = new float[2];
  private Bitmap appearance;

  public MazePlayer(BaseGame game) {
    super(game);
    float x = ((MazeGame) this.getGame()).getStartpoint().getX();
    float y = ((MazeGame) this.getGame()).getStartpoint().getY();
    this.coordinate = new Coordinate(x, y);

    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearance = BitmapFactory.decodeResource(resources, R.drawable.pacman_2);
    appearance =
        Bitmap.createScaledBitmap(
            appearance,
            (int) ((MazeGame) this.getGame()).getGridWidth(),
            (int) ((MazeGame) this.getGame()).getGridHeight(),
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
        this.coordinate.getX() * ((MazeGame) this.getGame()).getGridWidth(),
        this.coordinate.getY() * ((MazeGame) this.getGame()).getGridHeight(),
        paint);
  }

  @Override
  public void action() {
    this.move();
  }

  /** Move the player around (left and right) Detect the wall and NPCs. */
  private void move() {
    int[] border = new int[2];
    if (direction[0] > 0f){
      border[0] = 1;
    }
    if (direction[1] > 0f){
      border[1] = 1;
    }
    float targetX = this.coordinate.getX() + direction[0];
    float targetY = this.coordinate.getY() + direction[1];
    float playerWidth = appearance.getWidth()/((MazeGame) this.getGame()).getGridWidth();
    float playerHeight = appearance.getHeight()/((MazeGame) this.getGame()).getGridHeight();
    try {
      if (!(((MazeGame) this.getGame()).getMyMazeItem()[(int) (targetY + playerHeight*border[1])][(int) (targetX + playerWidth*border[0])]
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
   * Get the player's direction
   *
   * @return the array of direction
   */
  public float[] getDirection() {
    return direction;
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
