package com.example.g0569.chessgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.chessgame.ChessContract;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.List;

/** A game manager for ChessGame. */
public class ChessGame extends BaseGame {

  private ChessContract.Presenter presenter;
  private Inventory inventory;
  private NPC selectedNPC;
  private ChessPieceFactory chessPieceFactory;
  private List<NPC> NPCChessPieceData = new ArrayList<>();
  private List<NPC> playerChessPieceData = new ArrayList<>();
  private NPC selectedChessPiece;

  /**
   * Initialize a game manager for ChessGame.
   *
   * @param presenter the presenter
   * @param inventory the inventory
   * @param selectedIndex the selected index
   */
  public ChessGame(ChessContract.Presenter presenter, Inventory inventory, int selectedIndex) {
    super();
    this.presenter = presenter;
    this.inventory = inventory;
    List<NPC> allNPCs = new ArrayList<>();
    allNPCs.addAll(inventory.getAvailableItem());
    allNPCs.addAll(inventory.getCollectedItem());
    this.selectedNPC = allNPCs.get(selectedIndex);
  }

  /** On start. */
  public void onStart() {
    this.chessPieceFactory = new ChessPieceFactory();
    decodeNPCData();
    placePlayerChess();
  }

  /**
   * Decode the NPC data from SQLite database which has been read and stored in NPC.
   */
  private void decodeNPCData() {
    String chessString = selectedNPC.getChessLayout(); // get data from NPC from level one.
    String[] chessDataList = chessString.split("\\."); // suppose we are getting string
    // like"type1,1,1.type3,1,2.circle,1,3.type2,2,1"
    int count = 0;
    int loopLimit = chessDataList.length;
    while (count < loopLimit) {
      String[] currentChess = chessDataList[count].split(","); // for example:["type2","1","1"]
      String type = currentChess[0];
      float x = Float.parseFloat(currentChess[1]);
      float y = Float.parseFloat(currentChess[2]);
      placeNPCChess(x, y, type);
      count++;
    }
  }

  /**
   * Place the NPC chess piece to the board.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param type the chess piece type.
   */
  private void placeNPCChess(float x, float y, String type) {
    ChessPiece chessPiece = chessPieceFactory.getChessPiece(x, y, type);
    NPC npc = new NPC(type);
    npc.setBehavior(chessPiece);
    NPCChessPieceData.add(npc);
  }

  /** Reset chess piece. */
  public void resetChessPiece() {
    for (NPC npc : playerChessPieceData) {
      ((ChessPiece) npc.getBehavior()).resetCoordinate();
    }
  }

  /** Place the player chess piece to the inventory. */
  private void placePlayerChess() {
    playerChessPieceData.addAll(inventory.getAvailableItem());

    List<Coordinate> inventoryCoordinateList = new ArrayList<>();
    inventoryCoordinateList.add(new Coordinate(10, 10));
    inventoryCoordinateList.add(new Coordinate(10, 20));
    inventoryCoordinateList.add(new Coordinate(20, 10));
    inventoryCoordinateList.add(new Coordinate(20, 20));
    inventoryCoordinateList.add(new Coordinate(30, 10));
    inventoryCoordinateList.add(new Coordinate(30, 20));

    int index = 0;
    while (index < playerChessPieceData.size() && index < 6) {
      ChessPiece chessPiece =
          chessPieceFactory.getChessPiece(
              inventoryCoordinateList.get(index).getX(),
              inventoryCoordinateList.get(index).getY(),
              playerChessPieceData.get(index).getType());
      inventory.getAvailableItem().get(index).setBehavior(chessPiece);
      index++;
    }
  }

  /**
   * Place player chess on board.
   *
   * @param coordinate the coordinate
   */
  public void placePlayerChessOnBoard(Coordinate coordinate) {
    // This method place the Player Chess Piece on the Board.
    selectedChessPiece.setCoordinate(coordinate);
  }

  private void setSelectedChessPiece(NPC selectedChessPiece) {
    this.selectedChessPiece = selectedChessPiece;
  }

  /**
   * Sets selected chess piece data.
   *
   * @param coordinate the coordinate
   * @return the selected chess piece data
   */
  public String setSelectedChessPieceData(Coordinate coordinate) {
    for (NPC chessPiece : playerChessPieceData) {
      if (chessPiece.getCoordinate().equals(coordinate)) {
        setSelectedChessPiece(chessPiece);
      }
    }
    return selectedNPC.getName();
  }

  /**
   * Gets npc chess piece data.
   *
   * @return the npc chess piece data
   */
  public List<NPC> getNPCChessPieceData() {
    return NPCChessPieceData;
  }

  /**
   * Gets player chess piece.
   *
   * @return the player chess piece
   */
  public List<NPC> getPlayerChessPiece() {
    return playerChessPieceData;
  }

  /**
   * Filter the Chess piece still in the player's inventory.
   * Only put the Chess Piece on the board to fight list.
   */
  private List<NPC> addChessPieceToFightList(List<NPC> NPCList) {
    List<NPC> fightList = new ArrayList<>();
    for (NPC npc : NPCList) {
      if (npc.getCoordinate().getIntX() < 10 && npc.getCoordinate().getIntY() < 10) {
        fightList.add(npc);
      }
    }
    return fightList;
  }

  // TODO Add some comments here.
  private int characterAttack(List<NPC> friendlyInventory, List<NPC> opponentInventory) {
    int characterScore = 0;
    for (NPC currentChess : friendlyInventory) {
      Integer[][] targetList = ((ChessPiece) currentChess.getBehavior()).createTargetList();
      int count = 0;
      boolean enemyFound = false;
      while (!enemyFound && count < targetList.length) {
        for (NPC enemyChess : opponentInventory) {
          if (!enemyFound
              && ((ChessPiece) enemyChess.getBehavior()).matchCoordinate(targetList[count])) {
            enemyFound = true;
            int enemyDmg = enemyChess.getDamage();
            int ourDmg = currentChess.getDamage();
            if ((currentChess.getBehavior()) instanceof TriangleChessPiece) {
              ourDmg = 2 * currentChess.getDamage();
            }
            if (ourDmg >= enemyDmg) {
              characterScore += 1;
            }
          }
        }
        count++;
      }
      if (!enemyFound) {
        characterScore += 1;
      }
    }
    return characterScore;
  }

  /**
   * Let the chess fight and return the result.
   *
   * @return whether player win the game.
   */
  public boolean autoFight() {
    int playerScore = 0;
    List<NPC> playerFightList = addChessPieceToFightList(playerChessPieceData);
    playerScore += characterAttack(playerFightList, NPCChessPieceData);
    playerScore -= characterAttack(NPCChessPieceData, playerFightList);
    return (playerScore > 0);
  }

  /**
   * Sets game over result.
   *
   * @param winGame the win game
   */
  public void setGameOverResult(boolean winGame) {
    if (winGame && !inventory.getAvailableItem().contains(selectedNPC)) {
      inventory.addAvailableItem(selectedNPC);
    }
  }

  /**
   * Gets position has been taken.
   *
   * @param coordinate the coordinate
   * @return the position has been taken
   */
  public boolean getPositionHasBeenTaken(Coordinate coordinate) {
    boolean findInSamePosition = false;
    for (NPC npc : playerChessPieceData) {
      if (npc.getCoordinate().equals(coordinate)) findInSamePosition = true;
    }
    return findInSamePosition;
  }

  @Override
  public void pause() {}

  /**
   * Gets presenter.
   *
   * @return the presenter
   */
  public ChessContract.Presenter getPresenter() {
    return presenter;
  }

  /**
   * Sets presenter.
   *
   * @param presenter the presenter
   */
  public void setPresenter(ChessContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
