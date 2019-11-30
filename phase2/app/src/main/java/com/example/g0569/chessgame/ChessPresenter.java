package com.example.g0569.chessgame;

import com.example.g0569.chessgame.model.ChessGame;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.List;

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

//  private void initHashMap(){
//    float width = ((ChessView) chessView).getScreenWidth();
//    float height = ((ChessView) chessView).getScreenHeight();
//    float inventoryX = ((ChessView) chessView).getInventoryX();
//    float inventoryY = ((ChessView) chessView).getInventoryY();
//    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
//    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(1,1), new Coordinate(width * 0.32f, height * 0.46f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(1,2), new Coordinate(width * 0.42f, height * 0.46f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(1,3), new Coordinate(width * 0.52f, height * 0.46f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(1,4), new Coordinate(width * 0.62f, height * 0.46f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(2,1), new Coordinate(width * 0.3f, height * 0.61f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(2,2), new Coordinate(width * 0.41f, height * 0.61f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(2,3), new Coordinate(width * 0.525f, height * 0.61f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(2,4), new Coordinate(width * 0.64f, height * 0.61f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(3,1), new Coordinate(width * 0.27f, height * 0.8f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(3,2), new Coordinate(width * 0.395f, height * 0.8f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(3,3), new Coordinate(width * 0.53f, height * 0.8f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(3,4), new Coordinate(width * 0.67f, height * 0.8f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(10,10), new Coordinate(inventoryX, inventoryY));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(10,20), new Coordinate(inventoryX + inventoryWidth * 0.5f, inventoryY));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(20,10), new Coordinate(inventoryX, inventoryY + inventoryHeight * 0.33f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(20,20), new Coordinate(inventoryX + inventoryWidth * 0.5f, inventoryY + inventoryHeight * 0.33f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(30,10), new Coordinate(inventoryX, inventoryY + inventoryHeight * 0.66f));
//    gridCoordinateToViewCoordinateMap.put(new Coordinate(30,20), new Coordinate(inventoryX + inventoryWidth * 0.5f, inventoryY + inventoryHeight * 0.66f));
//  }

  @Override
  public boolean startAutoFight() {
    // TODO
    return chessGame.autoFight();
  }

  @Override
  public Coordinate gridCoordinateToViewCoordinate(Coordinate coordinate) {
    // TODO
    Coordinate viewCoordinate = new Coordinate(0, 0);
    float x = coordinate.getX();
    float y = coordinate.getY();
    float width = ((ChessView) chessView).getScreenWidth();
    float height = ((ChessView) chessView).getScreenHeight();
    float inventoryX = ((ChessView) chessView).getInventoryX();
    float inventoryY = ((ChessView) chessView).getInventoryY();
    float inventoryWidth = ((ChessView) chessView).getInventoryWidth();
    float inventoryHeight = ((ChessView) chessView).getInventoryHeight();
    // For Board Coordinate to View Coordinate to draw.
    if (x == 1 && y == 1) {
      viewCoordinate.setXY(width * 0.32f, height * 0.46f);
    } else if (x == 1 && y == 2) {
      viewCoordinate.setXY(width * 0.42f, height * 0.46f);
    } else if (x == 1 && y == 3) {
      viewCoordinate.setXY(width * 0.52f, height * 0.46f);
    } else if (x == 1 && y == 4) {
      viewCoordinate.setXY(width * 0.62f, height * 0.46f);
    } else if (x == 2 && y == 1) {
      viewCoordinate.setXY(width * 0.3f, height * 0.61f);
    } else if (x == 2 && y == 2) {
      viewCoordinate.setXY(width * 0.41f, height * 0.61f);
    } else if (x == 2 && y == 3) {
      viewCoordinate.setXY(width * 0.525f, height * 0.61f);
    } else if (x == 2 && y == 4) {
      viewCoordinate.setXY(width * 0.64f, height * 0.61f);
    } else if (x == 3 && y == 1) {
      viewCoordinate.setXY(width * 0.27f, height * 0.8f);
    } else if (x == 3 && y == 2) {
      viewCoordinate.setXY(width * 0.395f, height * 0.8f);
    } else if (x == 3 && y == 3) {
      viewCoordinate.setXY(width * 0.53f, height * 0.8f);
    } else if (x == 3 && y == 4) {
      viewCoordinate.setXY(width * 0.67f, height * 0.8f);
    }
    // For inventory Coordinate To View Coordinate to draw.
    else if (x == 10 && y == 10) {
      viewCoordinate.setXY(inventoryX, inventoryY);
    } else if (x == 10 && y == 20) {
      viewCoordinate.setXY(inventoryX + inventoryWidth * 0.5f, inventoryY);
    } else if (x == 20 && y == 10) {
      viewCoordinate.setXY(inventoryX, inventoryY + inventoryHeight * 0.33f);
    } else if (x == 20 && y == 20) {
      viewCoordinate.setXY(
          inventoryX + inventoryWidth * 0.5f, inventoryY + inventoryHeight * 0.33f);
    } else if (x == 30 && y == 10) {
      viewCoordinate.setXY(inventoryX, inventoryY + inventoryHeight * 0.66f);
    } else if (x == 30 && y == 20) {
      viewCoordinate.setXY(
          inventoryX + inventoryWidth * 0.5f, inventoryY + inventoryHeight * 0.66f);
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
}
