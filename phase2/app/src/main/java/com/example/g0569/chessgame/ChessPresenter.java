package com.example.g0569.chessgame;

import com.example.g0569.chessgame.model.ChessGame;
import com.example.g0569.chessgame.model.ChessGameCoordinateDataMap;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The type Chess presenter. */
public class ChessPresenter implements ChessContract.Presenter {
  private ChessContract.View chessView;
  private ChessGame chessGame;

  /**
   * Instantiates a new Chess presenter.
   *
   * @param chessView the chess view
   * @param inventory the inventory
   * @param selectedIndex the selected index
   */
  ChessPresenter(ChessContract.View chessView, Inventory inventory, int selectedIndex) {
    this.chessView = chessView;
    this.chessView.setPresenter(this);
    this.chessGame = new ChessGame(this, inventory, selectedIndex);
  }

  @Override
  public void start() {
    chessGame.onStart();
  }

  @Override
  public void drawChessPiece() {
    // Get the Chess pieces we need to draw.
    List<NPC> chessPieceToDraw = new ArrayList<>();
    chessPieceToDraw.addAll(chessGame.getPlayerChessPiece());
    chessPieceToDraw.addAll(chessGame.getNPCChessPieceData());
    // Draw all chess piece we need to draw.
    for (NPC chessPiece : chessPieceToDraw) {
      chessView.drawChessPiece(chessPiece.getCoordinate(), chessPiece.getType());
    }
  }

  @Override
  public boolean startAutoFight() {
    return chessGame.autoFight();
  }

  @Override
  public Coordinate gridCoordinateToViewCoordinate(Coordinate coordinate) {
    Integer key = coordinate.getIntX() * 100 + coordinate.getIntY();
    Coordinate viewCoordinate = new Coordinate(0, 0);
    float width = ((ChessView) chessView).getScreenWidth();
    float height = ((ChessView) chessView).getScreenHeight();
    float inventoryX = ((ChessView) chessView).getInventoryX();
    float inventoryY = ((ChessView) chessView).getInventoryY();
    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();
    // Get the firstMultiplier and secondMultiplier from ChessGameCoordinateDataMap
    float firstMultiplier =
        (float)
            Objects.requireNonNull(ChessGameCoordinateDataMap.DRAW_CHESS_GRID_LOOKUP_TABLE.get(key))
                .first;
    float secondMultiplier =
        (float)
            Objects.requireNonNull(ChessGameCoordinateDataMap.DRAW_CHESS_GRID_LOOKUP_TABLE.get(key))
                .second;
    // For Board Coordinate to View Coordinate to draw.
    if (coordinate.getIntX() < 10) {
      viewCoordinate.setXY(width * firstMultiplier, height * secondMultiplier);
    } else {
      viewCoordinate.setXY(
          inventoryX + inventoryWidth * firstMultiplier,
          inventoryY + inventoryHeight * secondMultiplier);
    }
    return viewCoordinate;
  }

  @Override
  public void placePlayerChess(Coordinate coordinate) {
    chessGame.placePlayerChessOnBoard(coordinate);
  }

  @Override
  public Coordinate viewCoordinateToInventoryCoordinate(Coordinate coordinate) {
    float x = coordinate.getX();
    float y = coordinate.getY();
    float inventoryX = ((ChessView) chessView).getInventoryX();
    float inventoryY = ((ChessView) chessView).getInventoryY();
    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();

    Coordinate InventoryCoordinate = new Coordinate(0, 0);
    if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY
        && y < inventoryY + inventoryHeight * 0.3333f) {
      // In inventory row1 col1.
      InventoryCoordinate.setXY(ChessGameCoordinateDataMap.INVENTORY_ROW_1_COL_1);
    } else if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY + inventoryHeight * 0.3333f
        && y < inventoryY + inventoryHeight * 0.6666f) {
      // In inventory row2 col1.
      InventoryCoordinate.setXY(ChessGameCoordinateDataMap.INVENTORY_ROW_2_COL_1);
    } else if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY + inventoryHeight * 0.6666f
        && y < inventoryY + inventoryHeight) {
      // In inventory row3 col1.
      InventoryCoordinate.setXY(ChessGameCoordinateDataMap.INVENTORY_ROW_3_COL_1);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY
        && y < inventoryY + inventoryHeight * 0.3333f) {
      // In inventory row1 col2.
      InventoryCoordinate.setXY(ChessGameCoordinateDataMap.INVENTORY_ROW_1_COL_2);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY + inventoryHeight * 0.3333f
        && y < inventoryY + inventoryHeight * 0.6666f) {
      // In inventory row2 col2.
      InventoryCoordinate.setXY(ChessGameCoordinateDataMap.INVENTORY_ROW_2_COL_2);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY + inventoryHeight * 0.6666f
        && y < inventoryY + inventoryHeight) {
      // In inventory row3 col2.
      InventoryCoordinate.setXY(ChessGameCoordinateDataMap.INVENTORY_ROW_3_COL_2);
    }
    return InventoryCoordinate;
  }

  @Override
  public Coordinate viewCoordinateToBoardCoordinate(Coordinate coordinate) {
    float x = coordinate.getX();
    float y = coordinate.getY();
    float width = ((ChessView) chessView).getScreenWidth();
    float height = ((ChessView) chessView).getScreenHeight();
    Coordinate BoardCoordinate = new Coordinate(0, 0);

    if (x > width * 0.3f && x < width * 0.39f && y > height * 0.44f && y < height * 0.59f) {
      // In board row1 col1.
      BoardCoordinate.setXY(ChessGameCoordinateDataMap.BOARD_ROW_1_COL_1);
    } else if (x > width * 0.27f && x < width * 0.37f && y > height * 0.59f && y < height * 0.72f) {
      // In board row2 col1.
      BoardCoordinate.setXY(ChessGameCoordinateDataMap.BOARD_ROW_2_COL_1);
    } else if (x > width * 0.23f && x < width * 0.35f && y > height * 0.72f && y < height) {
      // In board row3 col1.
      BoardCoordinate.setXY(ChessGameCoordinateDataMap.BOARD_ROW_3_COL_1);
    } else if (x > width * 0.39f && x < width * 0.5f && y > height * 0.44f && y < height * 0.59f) {
      // In board row1 col2.
      BoardCoordinate.setXY(ChessGameCoordinateDataMap.BOARD_ROW_1_COL_2);
    } else if (x > width * 0.37f && x < width * 0.5f && y > height * 0.59f && y < height * 0.72f) {
      // In board row2 col2.
      BoardCoordinate.setXY(ChessGameCoordinateDataMap.BOARD_ROW_2_COL_2);
    } else if (x > width * 0.35f && x < width * 0.5f && y > height * 0.72f && y < height) {
      // In board row3 col2.
      BoardCoordinate.setXY(ChessGameCoordinateDataMap.BOARD_ROW_3_COL_2);
    }
    return BoardCoordinate;
  }

  @Override
  public String setSelectedChessPieceData(Coordinate coordinate) {
    return chessGame.setSelectedChessPieceData(coordinate);
  }

  @Override
  public void showGameOverResult(boolean winGame) {
    chessGame.showGameOverResult(winGame);
  }

  @Override
  public boolean showPositionHasBeenTaken(Coordinate coordinate) {
    return chessGame.showPositionHasBeenTaken(coordinate);
  }

  @Override
  public void resetChessPiece() {
    chessGame.resetChessPiece();
  }
}
