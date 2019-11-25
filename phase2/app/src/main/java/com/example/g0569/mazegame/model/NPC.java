package com.example.g0569.mazegame.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

public class NPC extends Item {
  NPC(BaseGame game) {
    super(game);
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
