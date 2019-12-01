package com.example.g0569.chessgame;

import com.example.g0569.chessgame.model.ChessGame;
import com.example.g0569.chessgame.model.ChessGameCoordinateDataMaps;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessPresenter implements ChessContract.Presenter {
  private ChessContract.View chessView;
  private ChessGame chessGame;

  ChessPresenter(ChessContract.View chessView, Inventory inventory, NPC selectedNPC) {
    this.chessView = chessView;
    this.chessView.setPresenter(this);
    this.chessGame = new ChessGame(this, inventory, selectedNPC);
  }

  @Override
  public void start() {
    // TODO
    chessGame.onStart();
  }

  @Override
  public void drawChessPiece() {
    List<NPC> chessPieceToDraw = new ArrayList<>();
    chessPieceToDraw.addAll(chessGame.getPlayerChessPiece());
    chessPieceToDraw.addAll(chessGame.getNPCChessPieceData());
    for (NPC chessPiece : chessPieceToDraw) {
      chessView.drawChessPiece(chessPiece.getCoordinate(), chessPiece.getType());
    }
  }

  @Override
  public boolean startAutoFight() {
    // TODO
    return chessGame.autoFight();
  }

  @Override
  public Coordinate gridCoordinateToViewCoordinate(Coordinate coordinate) {
    // TODO
    Integer key = coordinate.getIntX() * 100 + coordinate.getIntY();
    Coordinate viewCoordinate = new Coordinate(0, 0);
    float width = ((ChessView) chessView).getScreenWidth();
    float height = ((ChessView) chessView).getScreenHeight();
    float inventoryX = ((ChessView) chessView).getInventoryX();
    float inventoryY = ((ChessView) chessView).getInventoryY();
    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();
    // For Board Coordinate to View Coordinate to draw.
    if (coordinate.getIntX() < 10) {
      float firstMultiplier =
          (float)
              Objects.requireNonNull(
                      ChessGameCoordinateDataMaps.DRAW_CHESS_BOARD_LOOKUP_TABLE.get(key))
                  .first;
      float secondMultiplier =
          (float)
              Objects.requireNonNull(
                      ChessGameCoordinateDataMaps.DRAW_CHESS_BOARD_LOOKUP_TABLE.get(key))
                  .second;
      viewCoordinate.setXY(width * firstMultiplier, height * secondMultiplier);
    } else {
      float firstOffset =
          (float)
              Objects.requireNonNull(
                      ChessGameCoordinateDataMaps.DRAW_CHESS_INVENTORY_LOOKUP_TABLE.get(key))
                  .first;
      float secondOffset =
          (float)
              Objects.requireNonNull(
                      ChessGameCoordinateDataMaps.DRAW_CHESS_INVENTORY_LOOKUP_TABLE.get(key))
                  .second;
      viewCoordinate.setXY(
          inventoryX + inventoryWidth * firstOffset, inventoryY + inventoryHeight * secondOffset);
    }

    return viewCoordinate;
  }

  @Override
  public void placePlayerChess(Coordinate coordinate) {
    chessGame.placePlayerChessOnBoard(coordinate);
  }

  @Override
  public String InventoryCoordinateToChessType(Coordinate coordinate) {
    return chessGame.getChessPieceType(coordinate);
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
      InventoryCoordinate.setXY(10, 10);
    } else if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY + inventoryHeight * 0.3333f
        && y < inventoryY + inventoryHeight * 0.6666f) {
      InventoryCoordinate.setXY(20, 10);
    } else if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY + inventoryHeight * 0.6666f
        && y < inventoryY + inventoryHeight) {
      InventoryCoordinate.setXY(30, 10);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY
        && y < inventoryY + inventoryHeight * 0.3333f) {
      InventoryCoordinate.setXY(10, 20);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY + inventoryHeight * 0.3333f
        && y < inventoryY + inventoryHeight * 0.6666f) {
      InventoryCoordinate.setXY(20, 20);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY + inventoryHeight * 0.6666f
        && y < inventoryY + inventoryHeight) {
      InventoryCoordinate.setXY(30, 20);
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
      BoardCoordinate.setXY(1, 1);
    } else if (x > width * 0.27f && x < width * 0.37f && y > height * 0.59f && y < height * 0.72f) {
      BoardCoordinate.setXY(2, 1);
    } else if (x > width * 0.23f && x < width * 0.35f && y > height * 0.72f && y < height) {
      BoardCoordinate.setXY(3, 1);
    } else if (x > width * 0.39f && x < width * 0.5f && y > height * 0.44f && y < height * 0.59f) {
      BoardCoordinate.setXY(1, 2);
    } else if (x > width * 0.37f && x < width * 0.5f && y > height * 0.59f && y < height * 0.72f) {
      BoardCoordinate.setXY(2, 2);
    } else if (x > width * 0.35f && x < width * 0.5f && y > height * 0.72f && y < height) {
      BoardCoordinate.setXY(3, 2);
    }
    return BoardCoordinate;
  }

  @Override
  public void setSelectedChessPieceData(Coordinate coordinate) {
    chessGame.setSelectedChessPieceData(coordinate);
  }

  @Override
  public void setGameOverResult(boolean winGame) {
    chessGame.setGameOverResult(winGame);
  }

  @Override
  public boolean getPositionHasBeenTaken(Coordinate coordinate) {
    return chessGame.getPositionHasBeenTaken(coordinate);
  }
}
