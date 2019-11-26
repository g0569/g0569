package com.example.g0569.chessgame;

import com.example.g0569.chessgame.model.ChessGame;
import com.example.g0569.chessgame.model.ChessPiece;
import com.example.g0569.chessgame.model.StarChessPiece;
import com.example.g0569.chessgame.model.TriangleChessPiece;
import com.example.g0569.utils.Coordinate;

public class ChessPresenter implements ChessContract.Presenter {
  private ChessContract.View chessView;
  private ChessGame chessGame;

  ChessPresenter(ChessContract.View chessView) {
    this.chessView = chessView;
    this.chessView.setPresenter(this);
    this.chessGame = new ChessGame(this);
  }

  @Override
  public void start() {
    // TODO
    chessGame.onStart();
  }

  @Override
  public void drawChessPiece(ChessPiece chessPiece) {
    Coordinate coordinate = boardCoordinateToViewCoordinate(chessPiece.getCoordinate());
    if (chessPiece instanceof StarChessPiece) {
      chessView.drawStar(coordinate);
    } else if (chessPiece instanceof TriangleChessPiece) {
      chessView.drawTriangle(coordinate);
    }
  }

  @Override
  public boolean startAutoFight() {
    // TODO
    return chessGame.autoFight();
  }

  @Override
  public Coordinate boardCoordinateToViewCoordinate(Coordinate coordinate) {
    // TODO
    Coordinate viewCoordinate = new Coordinate(0, 0);
    float x = coordinate.getX();
    float y = coordinate.getY();
    float width = ((ChessView) chessView).getScreenWidth();
    float height = ((ChessView) chessView).getScreenHeight();
    if (x == 1 && y == 1) {
      viewCoordinate.setXY(width * 0.55f, height * 0.4f);
    } else if (x == 1 && y == 2) {
      viewCoordinate.setXY(width * 0.65f, height * 0.4f);
    } else if (x == 2 && y == 1) {
      viewCoordinate.setXY(width * 0.55f, height * 0.65f);
    } else if (x == 2 && y == 2) {
      viewCoordinate.setXY(width * 0.65f, height * 0.65f);
    } else if (x == 3 && y == 1) {
      viewCoordinate.setXY(width * 0.55f, height * 0.9f);
    } else if (x == 3 && y == 2) {
      viewCoordinate.setXY(width * 0.65f, height * 0.9f);
    }
    return viewCoordinate;
  }

  @Override
  public void placePlayerChess(Coordinate coordinate, String type) {
    // TODO
    chessGame.setPlayerChess(coordinate.getX(), coordinate.getY(), type);
  }

  @Override
  public String InventoryCoordinateToChessType(Coordinate coordinate) {
    return chessGame.getChessPieceType(coordinate);
  }
}
