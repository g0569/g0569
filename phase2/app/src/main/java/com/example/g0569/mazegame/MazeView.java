package com.example.g0569.mazegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.g0569.R;
import com.example.g0569.base.GameView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.NPC;

/** The Maze view. */
public class MazeView extends GameView implements MazeContract.View, SensorEventListener {

  private Bitmap background;
  private MazeContract.Presenter presenter;

  private Bitmap wall;
  private Bitmap moveButtons;
  private Bitmap player;
  private Bitmap npc1;
  private Bitmap npc2;
  private Bitmap npc3;
  private Bitmap npc4;
  private Bitmap npc5;
  private Bitmap npc6;

  private float gridWidth;
  private float gridHeight;

  private boolean ableMove;

  private SensorManager sensorManager;
  private boolean enableSensor;

  /**
   * Instantiates a new Maze view.
   *
   * @param context the context
   */
  public MazeView(Context context) {
    super(context);
    paint.setTextSize(40);
    thread = new Thread(this);
    ableMove = true;
  }

  public MazeView(Context context, AttributeSet attrs) {
    super(context, attrs);
    paint.setTextSize(40);
    thread = new Thread(this);
    ableMove = true;
  }

  public void setEnableSensor(boolean enableSensor) {
    this.enableSensor = enableSensor;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    gridHeight = getHeight() / Constants.GRID_HEIGHT;
    gridWidth = getWidth() / Constants.GRID_WIDTH;
    sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
    enableSensor =
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI);
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
      canvas.drawColor(Color.BLACK);
      //      canvas.drawColor(
      //          Color.rgb(
      //              (int) (Math.random() * 255),
      //              (int) (Math.random() * 255),
      //              (int) (Math.random() * 255)));
      canvas.save();
      //      canvas.scale(scalex, scaley, 0, 0);
      //      canvas.drawBitmap(background, 0, 0, paint);
      //      canvas.restore();
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
    npc1 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l1);
    npc1 =
        Bitmap.createScaledBitmap(
            npc1, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    npc2 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l2);
    npc2 =
        Bitmap.createScaledBitmap(
            npc2, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    npc3 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l3);
    npc3 =
        Bitmap.createScaledBitmap(
            npc3, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    npc4 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l4);
    npc4 =
        Bitmap.createScaledBitmap(
            npc4, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    npc5 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l5);
    npc5 =
        Bitmap.createScaledBitmap(
            npc5, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
    npc6 = BitmapFactory.decodeResource(getResources(), R.drawable.npc_l6);
    npc6 =
        Bitmap.createScaledBitmap(
            npc6, getWidth() / Constants.GRID_WIDTH, getHeight() / Constants.GRID_HEIGHT, false);
  }

  @Override
  public void initView() {
    drawMaze(presenter.getMazeGrid());
    drawButton();
    drawClock();
    drawPlayer(presenter.getPlayerCoor());
  }

  private void drawButton() {
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    paint.setColor(Color.WHITE);
    canvas.drawBitmap(moveButtons, screenWidth - 4 * unitX, screenHeight - 4 * unitY, paint);
  }

  /** put unitX unitY to be instance variable?? */
  private void drawClock() {
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    paint.setColor(Color.WHITE);
    canvas.drawText(
        Integer.toString(presenter.getRemainTime()), screenWidth - 4 * unitX, 4 * unitY, paint);
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
          if (mazeGrid[j][i] == 1) {
          } else if (mazeGrid[j][i] == 0) {
            drawWall(gridNum2Coor(i, j));
          } else if (mazeGrid[j][i] == 2) {
            paint.setColor(Color.RED);
            canvas.drawText(
                Coordinate.create(i, j).toString(),
                gridNum2Coor(i, j).getX() - 10f,
                gridNum2Coor(i, j).getY() - 10f,
                paint);
            drawNPC(gridNum2Coor(i, j), presenter.addItemToMazeItem(i, j));
          }
        }
      }
    }
  }

  @Override
  public void deleteGridItem(int x, int y, int[][] mazeGrid) {
    mazeGrid[y][x] = 1;
  }

  private void drawWall(Coordinate coor) {
    paint.setColor(Color.WHITE);
    canvas.drawBitmap(wall, coor.getX(), coor.getY(), paint);
  }

  @Override
  public void drawPlayer(Coordinate coor) {
    paint.setColor(Color.WHITE);
    Coordinate screenCoor = gridNum2Coor(coor.getIntX(), coor.getIntY());
    canvas.drawBitmap(player, screenCoor.getX(), screenCoor.getY(), paint);
  }

  /**
   * TODO what if it is not limited to 6???
   *
   * @param coor
   * @param npc
   */
  @Override
  public void drawNPC(Coordinate coor, NPC npc) {
    paint.setColor(Color.GRAY);
    paint.setTextSize(70);
    //        String type = presenter.getNPCType(npc);
    if (presenter.getNPCType(npc).equals("type1")) {
      canvas.drawBitmap(npc1, coor.getX(), coor.getY(), paint);
    } else if (presenter.getNPCType(npc).equals("type2")) {
      canvas.drawBitmap(npc2, coor.getX(), coor.getY(), paint);
    } else if (presenter.getNPCType(npc).equals("type3")) {
      canvas.drawBitmap(npc3, coor.getX(), coor.getY(), paint);
    } else if (presenter.getNPCType(npc).equals("type4")) {
      canvas.drawBitmap(npc4, coor.getX(), coor.getY(), paint);
    } else if (presenter.getNPCType(npc).equals("type5")) {
      canvas.drawBitmap(npc5, coor.getX(), coor.getY(), paint);
    } else if (presenter.getNPCType(npc).equals("type6")) {
      canvas.drawBitmap(npc6, coor.getX(), coor.getY(), paint);
    }
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

  @Override
  public void stopView() {
    ableMove = false;
  }

  @Override
  public void resumeView() {
    ableMove = false;
  }

  /*
  detecting the moving direction (sth for button to do.)
   */
  private void moveDetect(float x, float y) {
    int unitX = (int) (screenWidth * 0.13 / 3);
    int unitY = (int) (screenHeight * 0.13 / 3);
    System.out.println(ableMove);
    if (ableMove) {
      Coordinate coor = new Coordinate(0f, 0f);
      if (x >= screenWidth - 2 * unitX
          && x <= screenWidth - unitX
          && y >= screenHeight - 3 * unitY
          && y <= screenHeight) {
        //        && y <= screenHeight - 2 * unitY) {
        //                presenter.movePlayer(Coordinate.create(0.5f, 0f));
        coor.setXY(0.5f, 0f);
        //    } else if (x >= screenWidth - 4 * unitX
      } else if (x >= screenWidth - 6 * unitX
          && x <= screenWidth - 3 * unitX
          && y >= screenHeight - 3 * unitY
          && y <= screenHeight - 2 * unitY) {
        //                presenter.movePlayer(Coordinate.create(-0.5f, 0f));
        coor.setXY(-0.5f, 0f);
      } else if (x >= screenWidth - 3 * unitX
          && x <= screenWidth - 2 * unitX
          //            && y >= screenHeight - 4 * unitY
          && y >= screenHeight - 6 * unitY
          && y <= screenHeight - 3 * unitY) {
        //                presenter.movePlayer(Coordinate.create(0f, -0.5f));
        coor.setXY(0f, -0.5f);
      } else if (x >= screenWidth - 3 * unitX
          && x <= screenWidth - 2 * unitX
          && y >= screenHeight - 2 * unitY
          //            && y <= screenHeight - unitY) {
          && y <= screenHeight) {
        //                presenter.movePlayer(Coordinate.create(0f, 0.5f));
        coor.setXY(0f, 0.5f);
      } else {
      }
      System.out.println(coor.getX());
      System.out.println(coor.getY());
      presenter.movePlayer(coor);
    } else {

    }
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.sensor == null) {
      return;
    }
    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
      if (enableSensor) {
        float accX = event.values[0];
        float accY = event.values[1];
        float accZ = event.values[2];
        Coordinate coordinate = Coordinate.create(0, 0);
        if (accY > 1f && accZ < 9.7f) coordinate.offsetXY(0.5f, 0);
        if (accY < -1f && accZ < 9.7f) coordinate.offsetXY(-0.5f, 0);
        if (accX < -1f && accZ < 9.7f) coordinate.offsetXY(0, -0.5f);
        if (accX > 1f && accZ < 9.7f) coordinate.offsetXY(0, 0.5f);
        presenter.movePlayer(coordinate);
      }
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
