package com.example.g0569.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.R;
import com.example.g0569.module.component.LV2AutoChess.LevelTwoPlayer;
import com.example.g0569.module.game.AutoChessGame;

public class ChessView extends BaseView {
  private Bitmap background;
  private Bitmap inventory;
  private Bitmap button;
  //  private ChessPiece chess;
  private Bitmap l2npc;
  private float button_x;
  private float button_y;
  private float button_y2;
  private float inventory_x;
  private float inventory_y;
  private Bitmap triangle;
  private float triangle_x;
  private float triangle_y;
  private Bitmap star;
  private float star_x;
  private float star_y;

  private LevelTwoPlayer l2player;
  private AutoChessGame autoChessGame;

  public ChessView(Context context) {
    super(context);
    paint.setTextSize(40);
    thread = new Thread(this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    background = BitmapFactory.decodeResource(getResources(), R.drawable.autochessboard);
    button = BitmapFactory.decodeResource(getResources(), R.drawable.start);
    button = Bitmap.createScaledBitmap(button, 150, 150, false);
    scalex = screen_width / background.getWidth();
    scaley = screen_height / background.getHeight();

    button_x = screen_width * 0.9f;
    button_y = screen_height * 0.7f;
    //        button_y2 = button_y + button.getHeight() + 40;
    inventory = BitmapFactory.decodeResource(getResources(), R.drawable.iteminventory);
    inventory = Bitmap.createScaledBitmap(inventory, 200, 300, false);
    inventory_x = screen_width / 30;
    inventory_y = screen_height * 0.7f;

    triangle = BitmapFactory.decodeResource(getResources(),R.drawable.triangle);
    triangle = Bitmap.createScaledBitmap(triangle, 80,80,false);
    triangle_x = inventory_x;
    triangle_y = inventory_y;

    star = BitmapFactory.decodeResource(getResources(),R.drawable.star);
    star = Bitmap.createScaledBitmap(star, 80,80,false);
    star_x = inventory_x + inventory.getWidth() * 0.5f;
    star_y = inventory_y;


    autoChessGame = (AutoChessGame) mainActivity.getGameManager().getCurrentGame();

    if (thread.isAlive()) {
      thread.start();
    } else {
      thread = new Thread(this);
      thread.start();
    }
  }



  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    super.surfaceChanged(holder, format, width, height);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    super.surfaceDestroyed(holder);
  }

  @Override
  public void draw() {
    try {
      canvas = sfh.lockCanvas();
      canvas.drawColor(Color.BLACK);
      canvas.save();
      canvas.scale(scalex, scaley, 0, 0);
      canvas.drawBitmap(background, 0, 0, paint);
      canvas.restore();
      canvas.drawBitmap(button, button_x, button_y, paint);
      canvas.drawBitmap(inventory, inventory_x, inventory_y, paint);
      canvas.drawBitmap(star,star_x,star_y,paint);
      canvas.drawBitmap(triangle,triangle_x,triangle_y,paint);
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      if (canvas != null) {
        sfh.unlockCanvasAndPost(canvas);
      }
    }
  }

  @Override
  public void run() {
    while (threadFlag) {
      long startTime = System.currentTimeMillis();
      draw();
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 400) Thread.sleep(400 - (endTime - startTime));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1) {
      float x = event.getX();
      float y = event.getY();
      System.out.println(String.valueOf(x) + " " + String.valueOf(y));
      if (x > button_x
          && x < button_x + button.getWidth()
          && y > button_y
          && y < button_y + button.getHeight()) {
        Toast.makeText(mainActivity, "Button was pressed", Toast.LENGTH_SHORT).show();
        // Call method to start the game.
        autoChessGame.autoFight(l2player);
      } else if (x > inventory_x
          && x < inventory_x + inventory.getWidth() * 0.5f
          && y > inventory_y
          && y < inventory_y + inventory.getHeight() * 0.3333f) {
        autoChessGame.place_Chess(0);
      } else if (x > inventory_x + inventory.getWidth() * 0.5f
          && x < inventory_x + inventory.getWidth()
          && y > inventory_y
          && y < inventory_y + inventory.getHeight() * 0.3333f) {
        autoChessGame.place_Chess(1);
      }
      return true;
    }
    return false;
  }
}
