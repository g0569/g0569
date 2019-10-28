package com.example.g0569.module.game;

// import com.example.g0569.module.component.Player;

import com.example.g0569.module.component.*;
import com.example.g0569.module.component.LV2AutoChess.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutoChessGame extends Game {
  //    private HashMap<Integer, List<ChessPiece>> battle_setting = new HashMap<Integer,
  // List<ChessPiece>>();
  private List<ChessPiece> NPC1_ChessPiece = new ArrayList<>();
  private List<ChessPiece> NPC2_ChessPiece = new ArrayList<>();
  private Integer round;
  private int number_win;
  //  private List data; // save where the player place the chess piece.
  private LevelTwoPlayer l2player;
  private int number_clicked = 1;

  public AutoChessGame(GameManager gameManager) {
    super(gameManager);
    round = 1;
    l2player = new LevelTwoPlayer(this);
    NPC1_ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.4f, this));
    NPC1_ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.65f, this));
    //    NPC1_ChessPiece.add(new StarChessPiece(1, 2));
    NPC2_ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.4f, this));
    NPC2_ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.65f, this));
    //    NPC2_ChessPiece.add(new TriangleChessPiece(1, 2));
    //        battle_setting.put(1, NPC1_ChessPiece);
    //        battle_setting.put(2,NPC2_ChessPiece);
  }
  //
  //    public HashMap<Integer, List<ChessPiece>> getBattle_setting() {
  //        return battle_setting;
  //    }

  public boolean autoFight(Player player) {
    // return the win or lose result in boolean expression.
    //    List<Boolean> battle_result = new ArrayList<>();
    //        for (Integer key : battle_setting.keySet()) {
    //            List<ChessPiece> lst = battle_setting.get(key);
    //            for (NonPlayerItem chess: player.inventory){
    //                float row = chess.getCoordinate().getX();
    //                if ((int)row == 0){
    //                    boolean r1 = lst.get(0).getPower() <= ((ChessPiece) chess).getPower();
    //                    battle_result.add(r1);
    //                }
    //                else if ((int) row == 1){
    //                    boolean r2 = lst.get(0).getPower() <= ((ChessPiece) chess).getPower();
    //                    battle_result.add(r2);
    //                }
    //                else {
    //                    boolean r3 = lst.get(0).getPower() <= ((ChessPiece) chess).getPower();
    //                    battle_result.add(r3);
    //                }
    //            }
    //        }
    if (round == 1) {
      // set up an array to take the result of battle of each row. (boolean)
      // start fighting: let every player's chess piece take action
      //            for(NonPlayerItem chess: player.inventory){
      //                int row = chess.corrdinate.getX();
      //                battle_result.add(chess.action(NPC1_ChessPiece.get(row)));// coordinator
      // must start from 0
      //            }

      for (NonPlayerItem chess : player.getInventory()) {
        float row = chess.getCoordinate().getX();
        if ((int) row == 0) {
          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
            number_win++;
          }
          //          battle_result.add(r1);
        } else if ((int) row == 1) {
          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
            number_win++;
          }
          //        } else {
          //          boolean r3 = NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece)
          // chess).getPower();
          //          battle_result.add(r3);
          //        }
        }
        NPC1_ChessPiece.clear();
      }
    }
    if (round == 2) {
      // place the NPC's chesspieces on chessboard.
      //            NPC2_ChessPiece.add(new StarChessPiece(1,0));
      //            NPC2_ChessPiece.add(new TriangleChessPiece(1,1));
      //            NPC2_ChessPiece.add(new TriangleChessPiece(1,2));
      // set up an array to take the result of battle of each row. (boolean)
      //            List battle_result;
      //            battle_result = new ArrayList<>() ;
      // start fighting: let every player's chess piece take action
      for (NonPlayerItem chess : player.getInventory()) {
        float row = chess.getCoordinate().getX();
        if ((int) row == 0) {
          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
            number_win++;
          }
        } else if ((int) row == 1) {
          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
            number_win++;
          }
          //        } else {
          //          boolean r3 = NPC2_ChessPiece.get(0).getPower() <= ((ChessPiece)
          // chess).getPower();
          //          battle_result.add(r3);
          //        }
        }
        NPC2_ChessPiece.clear();

        //            for(NonPlayerItem chess: player.inventory){
        //                int row = chess.corrdinate.getX();
        //                battle_result.add(chess.action(NPC1_ChessPiece.get(row)));// coordinator
        // must start from 0
        //                }
        //            NPC2_ChessPiece.clear();
      }
      //    int win = 0;
      //    for (boolean result : battle_result) {
      //      if (result) {
      //        win++;
      //      }
      //    }
      //    round++; // check if round is over limit in the manager.
      //    battle_result.clear();
    }
    return (number_win >= 2);
  }

  public void showOutput() {
    // what does this method do?
  }

  public void place_Chess(int choosen_place) {
    // choosen_place is the place of the choosen chess in the player's inventory.
    // e.g. User choose the first (position 0) item in the inventory.

    // Get the location where player place the chess piece and change the location of original chess
    // piece.
    //    TODO
    if (number_clicked == 1) {
      l2player
          .getInventory()
          .get(choosen_place)
          .getCoordinate()
          .setXY(
              getGameManager().getScreen_width() * 0.45f,
              getGameManager().getScreen_height() * 0.4f);
    } else if (number_clicked == 2) {
      l2player
          .getInventory()
          .get(choosen_place)
          .getCoordinate()
          .setXY(
              getGameManager().getScreen_width() * 0.45f,
              getGameManager().getScreen_height() * 0.65f);
    }
    //    l2player.getInventory().get(0).getCoordinate().setXY();
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
