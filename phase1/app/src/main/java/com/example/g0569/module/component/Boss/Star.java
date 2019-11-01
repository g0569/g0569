package com.example.g0569.module.component.Boss;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.module.game.Game;

public class Star extends ThrownItems {

  public Star(Game game, float screen_width, float screen_height, Resources resource) {
    super(game, screen_width, screen_height, resource);
    appearance = BitmapFactory.decodeResource(resource, R.drawable.star);
    damage = 20;
    src_rect = new Rect(0, 0, appearance.getWidth(), appearance.getHeight());
    explodingAppearance = BitmapFactory.decodeResource(resource, R.drawable.explosion);
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    super.draw(canvas, paint);
  }
}
