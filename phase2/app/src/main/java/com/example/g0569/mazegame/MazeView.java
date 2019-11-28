package com.example.g0569.mazegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.g0569.R;
import com.example.g0569.base.GameView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;

/** The Maze view. */
public class MazeView extends GameView implements MazeContract.View {

  private Bitmap background;
  private MazeContract.Presenter presenter;

  private Bitmap wall;
  private Bitmap moveButtons;
  private Bitmap player;
  private Bitmap npc;

  private float gridWidth;
  private float gridHeight;

  /**
   * Instantiates a new Maze view.
   *
   * @param context the context
   */
  public MazeView(Context context) {
    super(context);

    paint.setTextSize(40);
    thread = new Thread(this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    gridHeight = getHeight() / Constants.GRID_HEIGHT;
    gridWidth = getWidth() / Constants.GRID_WIDTH;
    initBitmaps();
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
      canvas.drawColor(Color.rgb(246, 216, 115));
      canvas.save();
      canvas.scale(scalex, scaley, 0, 0);
      //      canvas.drawBitmap(background, 0, 0, paint);
      canvas.restore();
      initView();
    } catch (Exception err) {
      System.out.println(err);
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
      presenter.update();
      long endTime = System.currentTimeMillis();
      try {
        if (endTime - startTime < 100) Thread.sleep((100 - (endTime - startTime)));
      } catch (InterruptedException err) {
        err.printStackTrace();
      }
    }
  }

  //  public int[] inRange()

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1) {
      float x = event.getX();
      float y = event.getY();
      moveDetect(x, y);
      return true;
    } else if (event.getAction() == MotionEvent.ACTION_UP && event.getPointerCount() == 1) {
      presenter.stopMove();
    }
    return false;
  }

  @Override
  public void setPresenter(MazeContract.Presenter presenter) {
    this.presenter = presenter;
  }

  public void initBitmaps() {
//    background = BitmapFactory.decodeResource(getResources(), R.drawable.maze_background);
//    scalex = screenWidth / background.getWidth();
//    scaley = screenHeight / background.getHeight();

    wall = BitmapFactory.decodeResource(getResources(), R.drawable.mazegame_component_tile);
    wall =
        Bitmap.createScaledBitmap(
            wall, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    player = BitmapFactory.decodeResource(getResources(), R.drawable.mazegame_component_pacman_2);
    player =
        Bitmap.createScaledBitmap(
            player, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    moveButtons = BitmapFactory.decodeResource(getResources(), R.drawable.mazegame_movebutton);
    moveButtons =
        Bitmap.createScaledBitmap(
            moveButtons, (int) (getWidth() * 0.13f), (int) (getHeight() * 0.13f), false);
  }

  @Override
  public void initView() {
    drawMaze(presenter.getMazeGrid());
    drawButton();
    drawPlayer(presenter.getPlayerCoor());
  }

  private void drawButton() {
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    paint.setColor(Color.WHITE);
    canvas.drawBitmap(moveButtons, screenWidth - 4 * unitX, screenHeight - 4 * unitY, paint);
  }

  public float getGridWidth() {
    return gridWidth;
  }

  public float getGridHeight() {
    return gridHeight;
  }

  private Coordinate gridNum2Coor(int x, int y) {
    return Coordinate.create(x * getGridWidth(), y * getGridHeight());
  }

  @Override
  public void drawMaze(int[][] mazeGrid) {
    if (mazeGrid == null) {
    } else {
      for (int i = 0; i < Constants.GRID_WIDTH; i++) {
        for (int j = 0; j < Constants.GRID_HEIGHT; j++) {
          if (mazeGrid[j][i] == 1) {;
          } else if (mazeGrid[j][i] == 0) {
            drawWall(gridNum2Coor(i, j));
          } else if (mazeGrid[j][i] == 2) {
            drawNPC(gridNum2Coor(i, j));
          }
        }
      }
    }
  }



  private void drawWall(Coordinate coor) {
    paint.setColor(Color.WHITE);
    canvas.drawBitmap(wall, coor.getX(), coor.getY(), paint);
  }

  @Override
  public void drawPlayer(Coordinate coor) {
    paint.setColor(Color.WHITE);
    Coordinate screenCoor = gridNum2Coor(coor.getIntX(), (int) coor.getIntY());
    canvas.drawBitmap(player, screenCoor.getX(), screenCoor.getY(), paint);
  }


  public void drawNPC(Coordinate coor) {
    paint.setColor(Color.GRAY);
    paint.setTextSize(70);
    canvas.drawText("NPC", coor.getX(), coor.getY(), paint);
  }

  @Override
  public Coordinate getPlayerDimensions() {
    Coordinate coordinate = Coordinate.create(0, 0);
    try {
      coordinate = Coordinate.create(player.getWidth(), player.getHeight());
    } catch (NullPointerException e) {
    } finally {
      return coordinate;
    }
  }

  /*
  detecting the moving direction (sth for button to do.)
   */
  private void moveDetect(float x, float y) {
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    if (x >= screenWidth - 2 * unitX
        && x <= screenWidth - unitX
        && y >= screenHeight - 3 * unitY
        && y <= screenHeight - 2 * unitY) {
      presenter.movePlayer(Coordinate.create(0.5f, 0f));
    } else if (x >= screenWidth - 4 * unitX
        && x <= screenWidth - 3 * unitX
        && y >= screenHeight - 3 * unitY
        && y <= screenHeight - 2 * unitY) {
      presenter.movePlayer(Coordinate.create(-0.5f, 0f));
    } else if (x >= screenWidth - 3 * unitX
        && x <= screenWidth - 2 * unitX
        && y >= screenHeight - 4 * unitY
        && y <= screenHeight - 3 * unitY) {
      presenter.movePlayer(Coordinate.create(0f, -0.5f));
    } else if (x >= screenWidth - 3 * unitX
        && x <= screenWidth - 2 * unitX
        && y >= screenHeight - 2 * unitY
        && y <= screenHeight - unitY) {
      presenter.movePlayer(Coordinate.create(0f, 0.5f));
    } else {
    }
  }
}
