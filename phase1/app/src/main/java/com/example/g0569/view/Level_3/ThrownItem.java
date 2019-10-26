package com.example.g0569.view.Level_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.graphics.Rect;

import com.example.g0569.R;
import com.example.g0569.model.Boss;
import com.example.g0569.view.BaseView;

public abstract class ThrownItem extends BitmapItem {

  public int damage;
  public boolean thrown;
  private double shrink;

  public ThrownItem(float screen_width, float screen_height, Resources resource) {
    size = (int) screen_width / 12;
    x = (int) (screen_width / 2 - size / 2);
    y = (int) (screen_height - screen_width * 3 / 32 - size / 2);
    shrink = 0.99;
    dx = (int) (size * (1 - shrink));
    dy = (int) (-screen_height / 100 + size * (1-shrink));
    thrown = false;
    dest_rect = new Rect(x, y, x + size, y + size);
  }

  public int getDamage() {
    return damage;
  }

  public void move(Canvas canvas, Paint paint) {
    if (thrown) {
      size = (int) (size * shrink);
      basicMove();
    }
  }

  public boolean inTheScreen(float screen_height) {
    return 0 <= (y + size) && (y + size) <= screen_height;
  }

  public void thrown() {
    thrown = true;
  }
}
