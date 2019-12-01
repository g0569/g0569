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
//  private List<NPC> NPCChessPieceBackUps;
//  private List<NPC> playerChessPieceBackUPs;


  /**
   * Initialize a game manager for ChessGame.
   *
   * @param presenter the presenter
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

  public void onStart() {
    this.chessPieceFactory = new ChessPieceFactory();
    decodeNPCData();
    placePlayerChess();
//    makeBackUp();
    // TODO should place the player's Chess Piece in inventory.
  }

//  private void makeBackUp(){
//    playerChessPieceBackUPs = new ArrayList<>(playerChessPieceData);
//    NPCChessPieceBackUps = new ArrayList<>(NPCChessPieceData);
//  }

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
    for (NPC npc : playerChessPieceData ) {
      ((ChessPiece)npc.getBehavior()).resetCoordinate();
    }
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

  private int characterAttack(String character){
    int characterScore = 0;
    List<NPC> friendlyInventory = new ArrayList<>();
    List<NPC> opponentInventory = new ArrayList<>();
    if (character.equals("player")) {
      friendlyInventory.addAll(getPlayerChessPiece());
      opponentInventory.addAll(NPCChessPieceData);
    }
    else if (character.equals("NPC")) {
      friendlyInventory.addAll(NPCChessPieceData);
      opponentInventory.addAll(getPlayerChessPiece());
    }
    for (NPC currentChess : friendlyInventory) {
      Integer[][] targetList = ((ChessPiece)currentChess.getBehavior()).createTargetList();
      int count = 0;
      boolean enemyFound = false;
      while(!enemyFound && count < targetList.length){
        for(NPC enemyChess: opponentInventory){
          if(!enemyFound && ((ChessPiece)enemyChess.getBehavior()).matchCoordinate(targetList[count])){
            enemyFound = true;
            int enemyDmg = enemyChess.getDamage();
            int ourDmg = currentChess.getDamage();
            if((currentChess.getBehavior()) instanceof TriangleChessPiece){
              ourDmg = 2*currentChess.getDamage();
            }
            if(ourDmg >= enemyDmg){characterScore += 1;}
          }
        }
        count ++;
      }
      if(!enemyFound){characterScore += 1;}
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
    playerScore += characterAttack("Player");
    playerScore -= characterAttack("NPC");
    return (playerScore >= 0);
    // TODO Need to be implemented.
  }

  public void setGameOverResult(boolean winGame) {
    if (winGame && !inventory.getAvailableItem().contains(selectedNPC)) {
      inventory.addAvailableItem(selectedNPC);
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
