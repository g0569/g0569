package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.chessgame.ChessContract;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

/** A game manager for ChessGame. */
public class ChessGame extends BaseGame {

  private ChessContract.Presenter presenter;

  private LevelTwoPlayer l2player;
  private String difficulty; // might be "easy", "hard", or "insane"
  private ChessPieceFactory chessPieceFactory;
  private ChessSQLiteAccessInterface boardData;
  private List<ChessPiece> NPCChessPiece = new ArrayList<>();
  // save where the NPC place the chess piece for different round.

  /**
   * Initialize a game manager for ChessGame.
   *
   * @param presenter the presenter
   */
  public ChessGame(ChessContract.Presenter presenter) {
    super();
    this.presenter = presenter;
  }

  public void onStart() {
    l2player = new LevelTwoPlayer();
    this.chessPieceFactory = new ChessPieceFactory();
    decodeNPCData();
  }

  public void setBoardData(ChessSQLiteAccessInterface boardData) {
    this.boardData = boardData;
  }

  private void decodeNPCData() {
    String chessString =
        boardData.getChessBoardData(
            difficulty); // get data before the chessgame_component_start of each round.
    String[] chessDataList =
        chessString.split(
            "."); // suppose we are getting string
                  // like"chessgame_component_star,1,1.circle,1,2.circle,1,3.nochess,2,1"
    int count = 0;
    int loopLimit = chessDataList.length;
    while (count < loopLimit) {
      String[] curr_chess =
          chessDataList[count].split(","); // for example:["chessgame_component_star","1","1"]
      String type = curr_chess[0];
      float x = Float.parseFloat(curr_chess[1]);
      float y = Float.parseFloat(curr_chess[2]);
      placeNPCChess(x, y, type);
      count++;
    }
    // TODO when decoding the string, for each chess piece, call placeNPCChess(x, y, type).
  }

  private void placeNPCChess(float x, float y, String type) {
    ChessPiece chessPiece = chessPieceFactory.getChessPiece(x, y, this, type);
    NPCChessPiece.add(chessPiece);
    //    presenter.drawChessPiece(chessPiece);
  }

  public void placePlayerChess(float x, float y, String type) {
    ChessPiece chessPiece = chessPieceFactory.getChessPiece(x, y, this, type);
    l2player.getInventory().add(chessPiece);
  }

  public String getChessPieceType(Coordinate coordinate) {
    String result = "";
    for (ChessPiece chesspiece : NPCChessPiece) {
      if (chesspiece.getCoordinate().equals(coordinate)) {
        result = typeGetter(chesspiece);
      }
    }
    return result;
  }

  public String typeGetter(ChessPiece chessPiece) {
    if (chessPiece instanceof StarChessPiece) {
      return "chessgame_component_star";
      //    }else if(chessPiece instanceof TriangleChessPiece){
      //      return "chessgame_component_triangle";
    } else return "chessgame_component_triangle";
  }

  public List<ChessPiece> getNPCChessPiece() {
    return NPCChessPiece;
  }

  public List<ChessPiece> getPlayerChessPiece() {
    List<ChessPiece> playerChessPieceList = new ArrayList<>();
    for (Item chessPiece : l2player.getInventory()) {
      playerChessPieceList.add((ChessPiece) chessPiece);
    }
    return playerChessPieceList;
  }

  private int powerCalculator(String side, int row) {
    // TODO This method need to be improved!
    int rowPower = 0;
    List<ChessPiece> requiredInventory = new ArrayList<>();
    if (side.equals("player")) {
      requiredInventory.addAll(getPlayerChessPiece());
    } else if (side.equals("NPC")) {
      requiredInventory.addAll(NPCChessPiece);
    }
    for (ChessPiece currentChess : requiredInventory) {
      if (currentChess.getCoordinate().getX() == row) {
        rowPower += currentChess.getPower();
      }
    }
    return rowPower;
  }

  private boolean singleRowFight(int row) {
    boolean playerWin = false;
    int playerPower = powerCalculator("player", row);
    int NPCPower = powerCalculator("NPC", row);
    if (difficulty.equals("easy")) {
      playerWin =
          (playerPower
              >= NPCPower); // player under easy mode can win a row with power equal to NPC.
    } else if (difficulty.equals("hard")) {
      playerWin = (playerPower > NPCPower); // now player can only win a row with more power.
    } else if (difficulty.equals("insane")) {
      playerWin =
          (playerPower > NPCPower
              & playerPower
                  > powerCalculator(
                      "NPC",
                      1)); // insane NPC at row 1 gains ability to fight other chess pieces on other
                           // rows.
    }
    return playerWin;
  }

  /**
   * Let the chess fight and return the result.
   *
   * @return whether player win the game.
   */
  public boolean autoFight() {
    int winNumbers = 0;
    int row = 1;
    while (row <= 3) {
      if (singleRowFight(row)) {
        winNumbers += 1;
      }
      row++;
    }
    return (winNumbers >= 2);
    // TODO Need to be implemented.
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
