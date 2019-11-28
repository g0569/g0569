package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.base.model.Player;
import com.example.g0569.chessgame.ChessContract;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

/** A game manager for ChessGame. */
public class ChessGame extends BaseGame {

  private ChessContract.Presenter presenter;
  // TODO still need to figure out a way to implement two different NPC's game.
  private int winNumbers;
  private LevelTwoPlayer l2player;
  private int clickNumbers = 1;
  private String difficulty;// might be "easy", "hard", or "insane"
  private ChessPieceFactory chessPieceFactory;
  private ChessSQLiteAccessInterface boardData;
  private List<ChessPiece> NPCData = new ArrayList<>();
  // save where the NPC place the chess piece for different round.

  /**
   * Initialize a game manager for ChessGame.
   *
   * @param presenter the presenter
   */
  public ChessGame(ChessContract.Presenter presenter) {
    super();
    this.presenter = presenter;
    //    List<ChessPiece> NPC1ChessPiece = new ArrayList<>();
    //    NPC1ChessPiece.add(
    //        new StarChessPiece(
    //            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.4f, this));
    //    NPC1ChessPiece.add(
    //        new TriangleChessPiece(
    //            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.65f,
    // this));
    //    //    NPC1ChessPiece.add(new StarChessPiece(1, 2));
    //    List<ChessPiece> NPC2ChessPiece = new ArrayList<>();
    //    NPC2ChessPiece.add(
    //        new TriangleChessPiece(
    //            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.4f, this));
    //    NPC2ChessPiece.add(
    //        new StarChessPiece(
    //            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.65f,
    // this));
    //
    //    NPCData.add(NPC1ChessPiece);
    //    NPCData.add(NPC2ChessPiece);
  }

  public void onStart() {
    l2player = new LevelTwoPlayer(this);
    this.chessPieceFactory = new ChessPieceFactory();
  }

  public void setBoardData(ChessSQLiteAccessInterface boardData) {
    this.boardData = boardData;
  }

  public void decodeNPCData() {
    String chessString = boardData.getChessBoardData(difficulty);//get data before the start of each round.
    String[] chessDataList = chessString.split(".");//suppose we are getting string like"star,1,1.circle,1,2.circle,1,3.nochess,2,1"
    int count = 0;
    int loopLimit = chessDataList.length;
    while(count < loopLimit){
        String[] curr_chess = chessDataList[count].split(",");// for example:["star","1","1"]
        String type = curr_chess[0];
        float x = Float.parseFloat(curr_chess[1]);
        float y = Float.parseFloat(curr_chess[2]);
        placeNPCChess(x,y,type);
        count ++;
    }
    // TODO when decoding the string, for each chess piece, call placeNPCChess(x, y, type).
  }

  private void placeNPCChess(float x, float y, String type) {
    ChessPiece chessPiece = chessPieceFactory.getChessPiece(x, y, this, type);
    NPCData.add(chessPiece);
    presenter.drawChessPiece(chessPiece);
  }

  public void setPlayerChess(float x, float y, String type) {
    ChessPiece chessPiece = chessPieceFactory.getChessPiece(x, y, this, type);
    l2player.getInventory().add(chessPiece);
  }

  public String getChessPieceType(Coordinate coordinate) {
    String result = "";
    for (ChessPiece chesspiece : NPCData) {
      if (chesspiece.getCoordinate().equals(coordinate)) {
        result = typeGetter(chesspiece);
      }
    }
    return result;
  }

  private String typeGetter(ChessPiece chessPiece) {
    if (chessPiece instanceof StarChessPiece) {
      return "star";
      //    }else if(chessPiece instanceof TriangleChessPiece){
      //      return "triangle";
    } else return "triangle";
  }

  //  /**
  //   * Show the statistic of the game.
  //   *
  //   * @param win whether or not the player win the game.
  //   */
  //  public void showStatistic(boolean win) {
  //    // TODO
  //    List<String> statistic = new ArrayList<>();
  //    if (win) {
  //      statistic.add("Number of Cards You Get: 2");
  //    } else {
  //      statistic.add("Number of Cards You Get: 0");
  //    }
  //    getGameManager().showStatistic(statistic);
  //  }
  //

  //  /**
  //   * An counter to count how many rows the player win.
  //   *
  //   * @param PlayerChess The players' chess.
  //   * @param round The round.
  //   * @return number of rows the player wins.
  //   */
  //  private int fightCounter(Item PlayerChess, int round) {
  //    int win = 0;
  //    for (ChessPiece NPCChess : NPCData.get(round)) {
  //      if (NPCChess.getCoordinate().getY() == PlayerChess.getCoordinate().getY()
  //          && NPCChess.getPower() <= ((ChessPiece) PlayerChess).getPower()) {
  //        win++; // Compared power it has.
  //      }
  //    }
  //    return win;
  //  }
  //
  private int powerCalculator(String side, int row){
    // TODO This method need to be improved!
    int rowPower = 0;
    List<ChessPiece> requiredInventory = new ArrayList<>();
    if(side.equals("player")){
      for (Item chessPiece: l2player.getInventory() ) {
        requiredInventory.add((ChessPiece) chessPiece);
      }
    }
    else if(side.equals("NPC")){requiredInventory = NPCData;}
    for (ChessPiece curr_chess : requiredInventory) {
      if (curr_chess.getCoordinate().getX() == row){
        rowPower += curr_chess.getPower();
      }
    }
    return rowPower;
  }
  private boolean singleRowFight(int row){
    boolean playerWin = false;
    int playerPower = powerCalculator("player", row);
    int NPCPower = powerCalculator("NPC", row);
    if(difficulty.equals("easy")){
      playerWin = (playerPower >= NPCPower);// player under easy mode can win a row with power equal to NPC.
    }
    else if(difficulty.equals("hard")){
      playerWin = (playerPower > NPCPower);// now player can only win a row with more power.
    }
    else if(difficulty.equals("insane")){
      playerWin = (playerPower > NPCPower&
              playerPower > powerCalculator("NPC", 1));// insane NPC at row 1 gains ability to fight other chess pieces on other rows.
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
      if (singleRowFight(row)){winNumbers += 1;}
      row ++;
    }
    return (winNumbers >= 2);
    // TODO Need to be implemented.

    //      for (Item chess : l2player.getInventory()) {
    //        winNumbers += fightCounter(chess, round);
    //      }
    //      return (winNumbers >= 2);
  }
  //
  //  /**
  //   * Place the chess piece in the place has been touched. And store the chess in the players
  //   * inventory.
  //   *
  //   * @param chosenPlace The place has been chosen.
  //   * @return the coordinate.
  //   */
  //  public Coordinate placeChess(int chosenPlace) {
  //    // In data, first is inventoryX. second is the width of inventory image.
  //    // Get the location where player place the chess piece and change the location of original
  // chess
  //    // piece.
  //    if (clickNumbers == 1) {
  //      l2player
  //          .getInventory()
  //          .get(chosenPlace)
  //          .getCoordinate()
  //          .setXY(
  //              getGameManager().getScreenWidth() * 0.45f, getGameManager().getScreenHeight() *
  // 0.4f);
  //      clickNumbers++;
  //      return new Coordinate(
  //          getGameManager().getScreenWidth() * 0.45f, getGameManager().getScreenHeight() * 0.4f);
  //      //    } else if (clickNumbers == 2) {
  //    } else { // For now we use else here, since there is a limit for number of chess.
  //      l2player
  //          .getInventory()
  //          .get(chosenPlace)
  //          .getCoordinate()
  //          .setXY(
  //              getGameManager().getScreenWidth() * 0.45f,
  //              getGameManager().getScreenHeight() * 0.65f);
  //      return new Coordinate(
  //          getGameManager().getScreenWidth() * 0.45f, getGameManager().getScreenHeight() *
  // 0.65f);
  //    }
  //  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
