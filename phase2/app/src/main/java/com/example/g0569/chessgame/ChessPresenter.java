package com.example.g0569.chessgame;

import com.example.g0569.chessgame.model.ChessGame;
import com.example.g0569.chessgame.model.ChessGameCoordinateDataMaps;
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
    Coordinate viewCoordinate = Coordinate.create(0, 0);
    float width = ((ChessView) chessView).getScreenWidth();
    float height = ((ChessView) chessView).getScreenHeight();
    float inventoryX = ((ChessView) chessView).getInventoryX();
    float inventoryY = ((ChessView) chessView).getInventoryY();
    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();
    // Get the firstMultiplier and secondMultiplier from ChessGameCoordinateDataMaps
    float firstMultiplier =
        (float)
            Objects.requireNonNull(
                    ChessGameCoordinateDataMaps.DRAW_CHESS_GRID_LOOKUP_TABLE.get(key))
                .first;
    float secondMultiplier =
        (float)
            Objects.requireNonNull(
                    ChessGameCoordinateDataMaps.DRAW_CHESS_GRID_LOOKUP_TABLE.get(key))
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
    int index = chessView.findTouchedGridCoordinate(coordinate);
    return ChessGameCoordinateDataMaps.FIND_CHESS_GRID_LOOKUP_TABLE.get(index);
  }

  @Override
  public Coordinate viewCoordinateToBoardCoordinate(Coordinate coordinate) {
    int index = chessView.findTouchedGridCoordinate(coordinate);
    return ChessGameCoordinateDataMaps.FIND_CHESS_GRID_LOOKUP_TABLE.get(index);
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
