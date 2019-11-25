package com.example.g0569.mazegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.g0569.R;
import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.NonPlayerItem;
import com.example.g0569.utils.Coordinate;

public class Wall extends NonPlayerItem {

  Wall(BaseGame game) {
    super(game);
  }
  @Override
  public void action() {
  }
}
