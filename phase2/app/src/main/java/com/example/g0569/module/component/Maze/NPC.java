package com.example.g0569.module.component.Maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.game.MazeGame;
import com.example.g0569.module.utils.Coordinate;

public class NPC extends Item {
  NPC(Game game, float x, float y) {
    super(game);
    this.coordinate = new Coordinate(x, y);
  }

  /**
   * Draw the NPC, text for now
   *
   * @param canvas of the button that is being drawn on
   * @param paint the style of the button
   */
  @Override
  public void draw(Canvas canvas, Paint paint) {
    paint.setColor(Color.GRAY);
    paint.setTextSize(70);
    canvas.drawText(
        "NPC",
        coordinate.getX() * ((MazeGame) getGame()).getGridWidth(),
        coordinate.getY() * ((MazeGame) getGame()).getGridHeight(),
        paint);
  }

  @Override
  public void action() {}

  /** Pop up the text showing the NPC is detected. Text for now. */
  public void pop() {
    //    Looper.prepare();
    //    getGame().getGameManager().getMainActivity().shootToast("test");
    //    Toast.makeText(getGame().getGameManager().getMainActivity(), "test",
    // Toast.LENGTH_SHORT).show();
    //    Looper.loop();
  }
}
