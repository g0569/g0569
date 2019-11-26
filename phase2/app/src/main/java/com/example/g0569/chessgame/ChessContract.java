package com.example.g0569.chessgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.chessgame.model.ChessPiece;
import com.example.g0569.utils.Coordinate;

public interface ChessContract {
  interface View extends BaseView<Presenter> {
    void initView();
    void drawStar(Coordinate coordinate);
    void drawTriangle(Coordinate coordinate);
    Coordinate viewCoordinateToBoardCoordinate(float x, float y);
  }

  interface Presenter extends BasePresenter {
    void drawChessPiece(ChessPiece chessPiece);
    boolean startAutoFight();
    Coordinate boardCoordinateToViewCoordinate(Coordinate coordinate);
    void placePlayerChess(Coordinate coordinate, String type);
  }
}
