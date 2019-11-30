package com.example.g0569.chessgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.g0569.R;
import com.example.g0569.base.GameView;
import com.example.g0569.utils.Coordinate;

/** The ChessView for the autoChessGame. */
public class ChessView extends GameView implements ChessContract.View {
  // The indicator whether the player choose the chessPiece(false) or place the chessPiece(true).
  private boolean placeChess;
  private Bitmap background;
  private Bitmap inventory;
  private Bitmap button;
  //  private Bitmap l2npc; // TODO
  private float buttonX;
  private float buttonY;
  private float inventoryX;
  private float inventoryY;
  private Bitmap triangle;
  //  private float triangleX;
  //  private float triangleY;
  //  private Bitmap triangle2;
  //  private float triangle2X;
  //  private float triangle2Y;
  private Bitmap star;
  //  private float starX;
  //  private float starY;
  //  private Bitmap star2;
  //  private float star2X;
  //  private float star2Y;

  private ChessContract.Presenter presenter;

  /**
   * Instantiates a new Chess view.
   *
   * @param context the context
   */
  public ChessView(Context context) {
    super(context);
    paint.setTextSize(40);
    thread = new Thread(this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    super.surfaceCreated(holder);
    initBitmaps();
    if (thread.isAlive()) {
      thread.start();
    } else {
      thread = new Thread(this);
      thread.start();
    }
  }

  protected float getScreenHeight() {
    return screenHeight;
  }

  protected float getScreenWidth() {
    return screenWidth;
  }

  protected float getButtonX() {
    return buttonX;
  }

  protected float getButtonY() {
    return buttonY;
  }

  protected float getButtonWidth() {
    return button.getWidth();
  }

  protected float getButtonHeight() {
    return button.getHeight();
  }

  protected float getInventoryX() {
    return inventoryX;
  }

  protected float getInventoryY() {
    return inventoryY;
  }

  protected float getInventoryWidth() {
    return inventory.getWidth();
  }

  protected float getInventoryHeight() {
    return inventory.getHeight();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    super.surfaceChanged(holder, format, width, height);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    super.surfaceDestroyed(holder);
  }

  private void initBitmaps() {
    background = BitmapFactory.decodeResource(getResources(), R.drawable.chessgame_background);

    scalex = screenWidth / background.getWidth();
    scaley = screenHeight / background.getHeight();

    button = BitmapFactory.decodeResource(getResources(), R.drawable.chessgame_component_start);
    button = Bitmap.createScaledBitmap(button, 150, 150, false);

    inventory =
        BitmapFactory.decodeResource(getResources(), R.drawable.chessgame_component_iteminventory);
    inventory = Bitmap.createScaledBitmap(inventory, 200, 300, false);

    triangle =
        BitmapFactory.decodeResource(getResources(), R.drawable.chessgame_component_triangle);
    triangle = Bitmap.createScaledBitmap(triangle, 80, 80, false);

    star = BitmapFactory.decodeResource(getResources(), R.drawable.chessgame_component_star);
    star = Bitmap.createScaledBitmap(star, 80, 80, false);
  }

  public void drawButton() {
    buttonX = screenWidth * 0.9f;
    buttonY = screenHeight * 0.7f;
    canvas.drawBitmap(button, buttonX, buttonY, paint);
  }

  public void drawInventory() {
    inventoryX = screenWidth * 0.033f;
    inventoryY = screenHeight * 0.7f;
    canvas.drawBitmap(inventory, inventoryX, inventoryY, paint);
  }

  // TODO Add two images for player and NPC. Maybe...

  //    public void drawPlayer(Coordinate coordinate) {
  //      canvas.drawBitmap(l2player, coordinate.getX(), coordinate.getY(), paint);
  //    }
  //
  //    public void drawNPC(Coordinate coordinate) {
  //      canvas.drawBitmap(l2npc, coordinate.getX(), coordinate.getY(), paint);
  //    }

  @Override
  public void initView() {
    drawButton();
    drawInventory();
    presenter.drawChessPiece();
  }

  @Override
  public void drawChessPiece(Coordinate coordinate, String type) {
    Coordinate viewCoordinate = presenter.gridCoordinateToViewCoordinate(coordinate);
    if (type.equals("chessgame_component_star"))
      canvas.drawBitmap(star, viewCoordinate.getX(), viewCoordinate.getY(), paint);
    else if (type.equals("chessgame_component_triangle"))
      canvas.drawBitmap(triangle, viewCoordinate.getX(), viewCoordinate.getY(), paint);
  }

  @Override
  public void draw() {
    try {
      canvas = sfh.lockCanvas();
      canvas.drawColor(Color.BLACK);
      canvas.save();
      canvas.scale(scalex, scaley, 0, 0);
      canvas.drawBitmap(background, 0, 0, paint);
      //      canvas.drawBitmap(star, screenWidth * 3, screenHeight * 3, paint);
      canvas.restore();
      initView();
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
      Coordinate viewCoordinate = new Coordinate(x, y);
      Coordinate inventoryCoordinate =
          presenter.viewCoordinateToInventoryCoordinate(viewCoordinate);
      Coordinate BoardCoordinate = presenter.viewCoordinateToBoardCoordinate(viewCoordinate);
      String type = presenter.InventoryCoordinateToChessType(inventoryCoordinate);
      System.out.println(String.valueOf(x) + " " + String.valueOf(y));
      if (placeChess) {
        // Place Chess Piece now.
        if (x > screenWidth * 0.3f
            && x < screenWidth * 0.5f
            && y > screenHeight * 0.44f
            && y < screenHeight) {
          // Place a chess piece that has been chosen.
          presenter.placePlayerChess(BoardCoordinate);
          placeChess = false;
        }
      } else {
        // Either start the game or choose a chess piece from inventory.
        if (x > buttonX
            && x < buttonX + button.getWidth()
            && y > buttonY
            && y < buttonY + button.getHeight()) {
          Toast.makeText(activity, "Start the game.", Toast.LENGTH_SHORT).show();
          // Call method to chessgame_component_start the game.
          boolean winGame = presenter.startAutoFight();
          if (winGame) {
            Toast.makeText(activity, "You win the game!", Toast.LENGTH_SHORT).show();
            //          autoChessGame.showStatistic(true); // Win and get 2 cards.
          } else {
            //          autoChessGame.showStatistic(false); // Lose and get 0 cards.
            Toast.makeText(activity, "You lose the game!", Toast.LENGTH_SHORT).show();
          }
        } else if (x > inventoryX
            && x < inventoryX + inventory.getWidth()
            && y > inventoryY
            && y < inventoryY + inventory.getHeight()) {
          // Choose a chess piece from inventory.
          Toast.makeText(activity, type + "chess was chosen", Toast.LENGTH_SHORT).show();
          presenter.setSelectedChessPieceData(inventoryCoordinate);
          // TODO To highlight the chess has been chosen.
          placeChess = true;
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public void setPresenter(ChessContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
