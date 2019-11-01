package com.example.g0569.module.component.Maze.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.widget.Toast;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

public class NPC extends NonPlayerItem {
  public NPC(Game game, float x, float y) {
    super(game);
    this.coordinate = new Coordinate(x, y);
  }

  /**
   *Draw the NPC, text for now
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.CYAN);
    paint.setTextSize(70);
    canvas.drawText(
        "NPC",
        coordinate.getX() * ((MazeGame) getGame()).getGrid_width(),
        coordinate.getY() * ((MazeGame) getGame()).getGrid_height(),
        paint);
  }

  @Override
  public void action() {}

  /**
   * Pop up the text showing the NPC is detected.
   * Text for now.
   */
  public void pop() {
    Looper.prepare();
        Toast.makeText(getGame().getGameManager().getMainActivity(), "test", Toast.LENGTH_SHORT)
            .show();
  Looper.loop();
  }

}
