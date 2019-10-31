package com.example.g0569.module.component.Maze.Components;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

public class Button extends NonPlayerItem {
  private Bitmap appearence;
  private int unitX;
  private int unitY;
  private float screen_width;
  private float screen_height;

  public Button(Game game) {
    super(game);
    this.unitX = (int)(((MazeGame) this.getGame()).getGameManager().getScreen_width()*0.13/3);
    this.unitY = (int)(((MazeGame) this.getGame()).getGameManager().getScreen_height()*0.13/3);
    this.screen_width = ((MazeGame) this.getGame()).getGameManager().getScreen_width();
    this.screen_height = ((MazeGame) this.getGame()).getGameManager().getScreen_height();
    this.coordinate = new Coordinate(16, 16);
    Resources resources = getGame().getGameManager().getMainActivity().getResources();
    this.appearence = BitmapFactory.decodeResource(resources, R.drawable.move_button);
    appearence =
        Bitmap.createScaledBitmap(
            appearence,
            (int) (((MazeGame) this.getGame()).getGameManager().getScreen_width()*0.13),
            (int) (((MazeGame) this.getGame()).getGameManager().getScreen_height()*0.13),
            false);
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.WHITE);
    canvas.drawBitmap(
        appearence,
        screen_width - 4 * unitX,
        screen_height - 4* unitY,
        paint);
  }

  /** Update */
  @Override
  public void action() {}

  public void move(MazePlayer player, float x, float y) {
    if (x >= screen_width - 2*unitX
        && x <= screen_width - unitX
        && y >= screen_height - 3*unitY
        && y <= screen_height - 2*unitY)  {
      player.getDirection()[0] += 1;
    } else if (x >= screen_height - 4*unitX
        && x <= screen_width - 3*unitX
            && y >= screen_height - 3*unitY
            && y <= screen_height - 2*unitY) {
      player.getDirection()[0] -= 1;
    } else if (x >= screen_width - 3*unitX
        && x <= screen_width - 2 * unitX
        && y >= screen_height - 4 * unitY
        && y <= screen_height- 3*unitY)  {
      player.getDirection()[1] -= 1;}
    else if (x >= screen_width - 3 * unitX
            && x <= screen_width - 2 * unitX
        && y >= screen_height - 2 * unitY
        && y <= screen_height - unitY) {
      player.getDirection()[1] += 1;
    } else {
    }
  }
}
