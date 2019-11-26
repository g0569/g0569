package com.example.g0569.chessgame.model;

// import com.example.g0569.base.model.Player;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.base.model.Item;
import com.example.g0569.chessgame.ChessContract;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

/** A game manager for ChessGame. */
public class ChessGame extends BaseGame {

  private ChessContract.Presenter presenter;
  // TODO still need to figure out a way to implement two different NPC's game.
  private int round = 0;
  private int winNumbers;
  private LevelTwoPlayer l2player;
  private int clickNumbers = 1;
  private ChessPieceFactory chessPieceFactory;
  private ChessSQLiteAccessInterface BoardData;
  private List<List<ChessPiece>> NPCData = new ArrayList<>();
  // save where the NPC place the chess piece for different round.

  /**
   * Initialize a game manager for ChessGame.
   *
   * @param presenter
   */
  public ChessGame(ChessContract.Presenter presenter) {
    super();
    this.presenter = presenter;
    this.chessPieceFactory = new ChessPieceFactory();
    l2player = new LevelTwoPlayer(this);
    List<ChessPiece> NPC1ChessPiece = new ArrayList<>();
    NPC1ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.4f, this));
    NPC1ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.65f, this));
    //    NPC1ChessPiece.add(new StarChessPiece(1, 2));
    List<ChessPiece> NPC2ChessPiece = new ArrayList<>();
    NPC2ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.4f, this));
    NPC2ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreenWidth() * 0.6f, gameManager.getScreenHeight() * 0.65f, this));

    NPCData.add(NPC1ChessPiece);
    NPCData.add(NPC2ChessPiece);
  }

  public void setBoardData(ChessSQLiteAccessInterface boardData) {
    BoardData = boardData;
  }

  public void decodeNPCData() {
    String s = BoardData.getChessBoardData();
    // TODO Decoder for string. Add objects to NPCData.
    // TODO when decode the string, for each chess piece call placeNPCChess().
  }

  private void placeNPCChess(String x, String y, String type,){
    // TODO
    ChessPiece chessPiece = chessPieceFactory.getChessPiece();
  }
  // save where the NPC place the chess piece for different round.

  /**
   * Show the statistic of the game.
   *
   * @param win whether or not the player win the game.
   */
  public void showStatistic(boolean win) {
    // TODO
    List<String> statistic = new ArrayList<>();
    if (win) {
      statistic.add("Number of Cards You Get: 2");
    } else {
      statistic.add("Number of Cards You Get: 0");
    }
    getGameManager().showStatistic(statistic);
  }

  /**
   * The getter for level two player.
   *
   * @return levelTwoPlayer
   */
  public LevelTwoPlayer getL2player() {
    return l2player;
  }

  /**
   * An counter to count how many rows the player win.
   *
   * @param PlayerChess The players' chess.
   * @param round The round.
   * @return number of rows the player wins.
   */
  private int fightCounter(Item PlayerChess, int round) {
    int win = 0;
    for (ChessPiece NPCChess : NPCData.get(round)) {
      if (NPCChess.getCoordinate().getY() == PlayerChess.getCoordinate().getY()
          && NPCChess.getPower() <= ((ChessPiece) PlayerChess).getPower()) {
        win++; // Compared power it has.
      }
    }
    return win;
  }

  /**
   * Let the chess fight and return the result.
   *
   * @return whether player win the game.
   */
  public boolean autoFight() {
    for (Item chess : l2player.getInventory()) {
      winNumbers += fightCounter(chess, round);
    }
    return (winNumbers >= 2);
  }

  /**
   * Place the chess piece in the place has been touched. And store the chess in the players
   * inventory.
   *
   * @param chosenPlace The place has been chosen.
   * @return the coordinate.
   */
  public Coordinate placeChess(int chosenPlace) {
    // In data, first is inventoryX. second is the width of inventory image.
    // Get the location where player place the chess piece and change the location of original chess
    // piece.
    if (clickNumbers == 1) {
      l2player
          .getInventory()
          .get(chosenPlace)
          .getCoordinate()
          .setXY(
              getGameManager().getScreenWidth() * 0.45f, getGameManager().getScreenHeight() * 0.4f);
      clickNumbers++;
      return new Coordinate(
          getGameManager().getScreenWidth() * 0.45f, getGameManager().getScreenHeight() * 0.4f);
      //    } else if (clickNumbers == 2) {
    } else { // For now we use else here, since there is a limit for number of chess.
      // TODO
      l2player
          .getInventory()
          .get(chosenPlace)
          .getCoordinate()
          .setXY(
              getGameManager().getScreenWidth() * 0.45f,
              getGameManager().getScreenHeight() * 0.65f);
      return new Coordinate(
          getGameManager().getScreenWidth() * 0.45f, getGameManager().getScreenHeight() * 0.65f);
    }
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
