package com.example.g0569.base;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.Coordinate;

public class BaseButton extends Item {
  private Bitmap appearence;
  private int unitX;
  private int unitY;

//  TODO
  public BaseButton(BaseGame game) {
    super(game);

  }

  @Override
  public void update() {

  }

}
