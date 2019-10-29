package com.example.g0569.module.game;

// import com.example.g0569.module.component.Player;

import android.graphics.Bitmap;

import com.example.g0569.module.component.*;
import com.example.g0569.module.component.LV2AutoChess.*;
import com.example.g0569.module.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class AutoChessGame extends Game {
  //    private HashMap<Integer, List<ChessPiece>> battle_setting = new HashMap<Integer,
  // List<ChessPiece>>();
  private List<ChessPiece> NPC1_ChessPiece = new ArrayList<>();
  private List<ChessPiece> NPC2_ChessPiece = new ArrayList<>();

  private int round = 0;
  private int number_win;
  private LevelTwoPlayer l2player;
  private int number_clicked = 1;

  private List<List<ChessPiece>> NPC_data = new ArrayList<>();
  // save where the NPC place the chess piece for different round.

  public AutoChessGame(GameManager gameManager) {
    super(gameManager);
    l2player = new LevelTwoPlayer(this);
    NPC1_ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.4f, this));
    NPC1_ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.65f, this));
    //    NPC1_ChessPiece.add(new StarChessPiece(1, 2));
    NPC2_ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.4f, this));
    NPC2_ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.65f, this));

    NPC_data.add(NPC1_ChessPiece);
    NPC_data.add(NPC2_ChessPiece);
  }

  public LevelTwoPlayer getL2player() {
    return l2player;
  }

  public int fight_counter(NonPlayerItem player_chess, int round) {
    int win = 0;
    for (ChessPiece NPC_chess : NPC_data.get(round)) {
      if (NPC_chess.getCoordinate().getY() == player_chess.getCoordinate().getY()
          && NPC_chess.getPower() <= ((ChessPiece) player_chess).getPower()) {
        win++;
      }
    }
    return win;
  }

  public boolean autoFight() {
    for (NonPlayerItem chess : l2player.getInventory()) {
      number_win += fight_counter(chess, round);
    }
    return (number_win >= 2);
  }
  //    if (round == 1) {
  //      for (NonPlayerItem chess : l2player.getInventory()) {
  //        number_win += fight_counter(chess, round);
  //        float row = chess.getCoordinate().getX();
  //        if ((int) row == 0) {
  //          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
  //            number_win++;
  //          }
  //          //          battle_result.add(r1);
  //        } else if ((int) row == 1) {
  //          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
  //            number_win++;
  //          }
  //        }
  //      }
  //    } else if (round == 2) {
  //      // place the NPC's chesspieces on chessboard.
  //      //            NPC2_ChessPiece.add(new StarChessPiece(1,0));
  //      //            NPC2_ChessPiece.add(new TriangleChessPiece(1,1));
  //      //            NPC2_ChessPiece.add(new TriangleChessPiece(1,2));
  //      // set up an array to take the result of battle of each row. (boolean)
  //      //            List battle_result;
  //      //            battle_result = new ArrayList<>() ;
  //      // start fighting: let every player's chess piece take action
  //      for (NonPlayerItem chess : l2player.getInventory()) {
  //        float row = chess.getCoordinate().getX();
  //        if ((int) row == 0) {
  //          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
  //            number_win++;
  //          }
  //        } else if ((int) row == 1) {
  //          if (NPC1_ChessPiece.get(0).getPower() <= ((ChessPiece) chess).getPower()) {
  //            number_win++;
  //          }
  //        }
  //      }
  //    }
  //    round++; // check if round is over limit in the manager.
  //    return (number_win >= 2);
  //  }

  public Coordinate place_Chess(float x, int chosen_place) {
    // In data, first is inventory_x. second is the width of inventory image.
    // Get the location where player place the chess piece and change the location of original chess piece.
    if (number_clicked == 1) {
      l2player
          .getInventory()
          .get(chosen_place)
          .getCoordinate()
          .setXY(
              getGameManager().getScreen_width() * 0.45f,
              getGameManager().getScreen_height() * 0.4f);
      number_clicked++;
      return new Coordinate(getGameManager().getScreen_width() * 0.45f, getGameManager().getScreen_height() * 0.4f);
//    } else if (number_clicked == 2) {
    } else { // For now we use else here, since there is a limit for number of chess.
      // TODO
      l2player
          .getInventory()
          .get(chosen_place)
          .getCoordinate()
          .setXY(
              getGameManager().getScreen_width() * 0.45f,
              getGameManager().getScreen_height() * 0.65f);
      return new Coordinate(getGameManager().getScreen_width() * 0.45f, getGameManager().getScreen_height() * 0.65f);
    }
  }

//  public int place_decider(float x, float inventory_x){
//    if (x == inventory_x){
//      return 0;
//    }
//    else {
////    else if(x == getGameManager().getScreen_height() * 0.65f){
//      return 1;
//    }
////    else { // Right now we only have 3 place for player to place chess.
////      return 2;
////    }
//  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
