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
  private String difficulty =
      "hard"; // might be "easy", "hard", or "insane" // TODO Need to be implemented.
  private ChessPieceFactory chessPieceFactory;
  private List<NPC> NPCChessPieceData = new ArrayList<>();
  private List<NPC> playerChessPieceData = new ArrayList<>();
  private NPC selectedChessPiece;
  private List<NPC> NPCChessPieceBackUps = new ArrayList<>();
  private List<NPC> playerChessPieceBackUPs = new ArrayList<>();


  /**
   * Initialize a game manager for ChessGame.
   *
   * @param presenter the presenter
   */
  public ChessGame(ChessContract.Presenter presenter, Inventory inventory, NPC selectedNPC) {
    super();
    this.presenter = presenter;
    this.inventory = inventory;
    this.selectedNPC = selectedNPC;
  }

  public void onStart() {
    this.chessPieceFactory = new ChessPieceFactory();
    decodeNPCData();
    placePlayerChess();
    makeBackUp();
    // TODO should place the player's Chess Piece in inventory.
  }

  private void makeBackUp(){
    playerChessPieceBackUPs.addAll(playerChessPieceData);
    NPCChessPieceBackUps.addAll(NPCChessPieceData);
  }

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
    // TODO when decoding the string, for each chess piece, call placeNPCChess(x, y, type).
  }

  private void placeNPCChess(float x, float y, String type) {
    ChessPiece chessPiece = chessPieceFactory.getChessPiece(x, y, type);
    NPC npc = new NPC(type);
    npc.setBehavior(chessPiece);
    NPCChessPieceData.add(npc);
  }

  public void resetChessPiece(){
    playerChessPieceData.clear();
    playerChessPieceData.addAll(playerChessPieceBackUPs);
    NPCChessPieceData.clear();
    NPCChessPieceData.addAll(NPCChessPieceBackUps);
  }

  private void placePlayerChess() {
    // This method place the player chess piece to the inventory.
    playerChessPieceData.addAll(inventory.getAvailableItem());

    // TODO
    List<Coordinate> inventoryCoordinateList = new ArrayList<>();
    inventoryCoordinateList.add(new Coordinate(10, 10));
    inventoryCoordinateList.add(new Coordinate(10, 20));
    inventoryCoordinateList.add(new Coordinate(20, 10));
    inventoryCoordinateList.add(new Coordinate(20, 20));
    inventoryCoordinateList.add(new Coordinate(30, 10));
    inventoryCoordinateList.add(new Coordinate(30, 20));

    int index = 0;
    while (index < playerChessPieceData.size()) {
      ChessPiece chessPiece =
          chessPieceFactory.getChessPiece(
              inventoryCoordinateList.get(index).getX(),
              inventoryCoordinateList.get(index).getY(),
              playerChessPieceData.get(index).getType());
      inventory.getAvailableItem().get(index).setBehavior(chessPiece);
      index++;
    }
  }

  public void placePlayerChessOnBoard(Coordinate coordinate) {
    // This method place the Player Chess Piece on the Board.
    selectedChessPiece.setCoordinate(coordinate);
  }

  public String getChessPieceType(Coordinate coordinate) {
    String result = "";
    for (NPC chessPiece : playerChessPieceData) {
      if (chessPiece.getCoordinate().equals(coordinate)) {
        result = chessPiece.getType();
      }
    }
    return result;
  }

  private void setSelectedChessPiece(NPC selectedChessPiece) {
    this.selectedChessPiece = selectedChessPiece;
  }

  public void setSelectedChessPieceData(Coordinate coordinate) {
    for (NPC chessPiece : playerChessPieceData) {
      if (chessPiece.getCoordinate().equals(coordinate)) {
        setSelectedChessPiece(chessPiece);
      }
    }
  }

  public List<NPC> getNPCChessPieceData() {
    return NPCChessPieceData;
  }

  public List<NPC> getPlayerChessPiece() {
    return playerChessPieceData;
  }

  private int powerCalculator(String side, int row) {
    // TODO This method need to be improved!
    int rowPower = 0;
    List<NPC> requiredInventory = new ArrayList<>();
    if (side.equals("player")) {
      requiredInventory.addAll(getPlayerChessPiece());
    } else if (side.equals("NPC")) {
      requiredInventory.addAll(NPCChessPieceData);
    }
    for (NPC currentChess : requiredInventory) {
      if (currentChess.getCoordinate().getX() == row) {
        rowPower += currentChess.getDamage();
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

  public void setGameOverResult(boolean winGame) {
    if (winGame && !inventory.getAvailableItem().contains(selectedNPC)) {
      inventory.addAvailableItem(selectedChessPiece);
    }
  }

  public boolean getPositionHasBeenTaken(Coordinate coordinate){
    boolean findInSamePosition = false;
    for ( NPC npc: playerChessPieceData ) {
      if (npc.getCoordinate().equals(coordinate))
        findInSamePosition = true;
    }
    return findInSamePosition;
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
