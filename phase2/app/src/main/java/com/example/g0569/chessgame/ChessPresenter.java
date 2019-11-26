package com.example.g0569.chessgame;

import com.example.g0569.chessgame.model.ChessGame;
import com.example.g0569.utils.Coordinate;

public class ChessPresenter implements ChessContract.Presenter {
  private ChessContract.View chessView;
  private ChessGame chessGame;

  public ChessPresenter(ChessContract.View chessView) {
    this.chessView = chessView;
    this.chessView.setPresenter(this);
    this.chessGame = new ChessGame(this);
  }

  @Override
  public void start() {}

  @Override
  public Coordinate getChessPieceCoordinate() {
    return null;
  }
}
