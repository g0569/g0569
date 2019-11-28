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

  @Override
  public Coordinate viewCoordinateToInventoryCoordinate(Coordinate coordinate) {
    float x = coordinate.getX();
    float y = coordinate.getY();
    float inventoryX = ((ChessView) chessView).getInventoryX();
    float inventoryY = ((ChessView) chessView).getInventoryY();
    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();
    float buttonX = ((ChessView) chessView).getButtonX();
    float buttonY = ((ChessView) chessView).getButtonY();
    float buttonWidth = ((ChessView) chessView).getButtonWidth();
    float buttonHeight = ((ChessView) chessView).getButtonHeight();

    Coordinate InventoryCoordinate = new Coordinate(0, 0);
    if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY
        && y < inventoryY + inventoryHeight * 0.3333f) {
      InventoryCoordinate.setXY(1, 1);
    } else if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY + inventoryHeight * 0.3333f
        && y < inventoryY + inventoryHeight * 0.6666f) {
      InventoryCoordinate.setXY(2, 1);
    } else if (x > inventoryX
        && x < inventoryX + inventoryWidth * 0.5f
        && y > inventoryY + inventoryHeight * 0.6666f
        && y < inventoryY + inventoryHeight) {
      InventoryCoordinate.setXY(3, 1);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY
        && y < inventoryY + inventoryHeight * 0.3333f) {
      InventoryCoordinate.setXY(1, 2);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY + inventoryHeight * 0.3333f
        && y < inventoryY + inventoryHeight * 0.6666f) {
      InventoryCoordinate.setXY(2, 2);
    } else if (x > inventoryX + inventoryWidth * 0.5f
        && x < inventoryX + inventoryWidth
        && y > inventoryY + inventoryHeight * 0.6666f
        && y < inventoryY + inventoryHeight) {
      InventoryCoordinate.setXY(3, 2);
    } else if (x > buttonX
        && x < buttonX + buttonWidth
        && y > buttonY
        && y < buttonY + buttonHeight) {
      InventoryCoordinate.setXY(0, 0);
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
    if (x > width * 0.3f
        && x < width * 0.39f
        && y > height * 0.44f
        && y < height * 0.59f) {
      BoardCoordinate.setXY(1, 1);
    } else if (x > width * 0.27f
        && x < width * 0.37f
        && y > height * 0.59f
        && y < height * 0.72f) {
      BoardCoordinate.setXY(2, 1);
    } else if (x > width * 0.23f
        && x < width * 0.35f
        && y > height * 0.72f
        && y < height) {
      BoardCoordinate.setXY(3, 1);
    } else if (x > width * 0.39f
        && x < width * 0.5f
        && y > height * 0.44f
        && y < height * 0.59f) {
      BoardCoordinate.setXY(1, 2);
    } else if (x > width * 0.37f
        && x < width * 0.5f
        && y > height * 0.59f
        && y < height * 0.72f) {
      BoardCoordinate.setXY(2, 2);
    } else if (x > width * 0.35f
        && x < width * 0.5f
        && y > height * 0.72f
        && y < height) {
      BoardCoordinate.setXY(3, 2);
    }
    return BoardCoordinate;
  }
}
