package com.example.g0569.chessgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Coordinate;

public interface ChessContract {
  interface View extends BaseView<Presenter> {
    void initView();

    void drawChessPiece(Coordinate coordinate, String type);
  }

  interface Presenter extends BasePresenter {
    void drawChessPiece();

    boolean startAutoFight();

    Coordinate gridCoordinateToViewCoordinate(Coordinate coordinate);

    void placePlayerChess(Coordinate coordinate);

    String InventoryCoordinateToChessType(Coordinate coordinate);

    Coordinate viewCoordinateToInventoryCoordinate(Coordinate coordinate);

    Coordinate viewCoordinateToBoardCoordinate(Coordinate coordinate);

    void setSelectedChessPieceData(Coordinate coordinate);

    void setGameOverResult(boolean winGame);

    boolean getPositionHasBeenTaken(Coordinate coordinate);

  }
}
