package com.example.g0569.module.game;

// import com.example.g0569.module.component.Player;

import com.example.g0569.module.component.LV2AutoChess.ChessPiece;
import com.example.g0569.module.component.LV2AutoChess.LevelTwoPlayer;
import com.example.g0569.module.component.LV2AutoChess.StarChessPiece;
import com.example.g0569.module.component.LV2AutoChess.TriangleChessPiece;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class AutoChessGame extends Game {

  // TODO still need to figure out a way to implement two different NPC's game.
  private int round = 0;
  private int number_win;
  private LevelTwoPlayer l2player;
  private int number_clicked = 1;

  private List<List<ChessPiece>> NPC_data = new ArrayList<>();
  // save where the NPC place the chess piece for different round.

  AutoChessGame(GameManager gameManager) {
    super(gameManager);
    l2player = new LevelTwoPlayer(this);
    List<ChessPiece> NPC1_ChessPiece = new ArrayList<>();
    NPC1_ChessPiece.add(
        new StarChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.4f, this));
    NPC1_ChessPiece.add(
        new TriangleChessPiece(
            gameManager.getScreen_width() * 0.6f, gameManager.getScreen_height() * 0.65f, this));
    //    NPC1_ChessPiece.add(new StarChessPiece(1, 2));
    List<ChessPiece> NPC2_ChessPiece = new ArrayList<>();
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
        win++; // Compared power it has.
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

  public Coordinate place_Chess(float x, int chosen_place) {
    // In data, first is inventory_x. second is the width of inventory image.
    // Get the location where player place the chess piece and change the location of original chess
    // piece.
    if (number_clicked == 1) {
      l2player
          .getInventory()
          .get(chosen_place)
          .getCoordinate()
          .setXY(
              getGameManager().getScreen_width() * 0.45f,
              getGameManager().getScreen_height() * 0.4f);
      number_clicked++;
      return new Coordinate(
          getGameManager().getScreen_width() * 0.45f, getGameManager().getScreen_height() * 0.4f);
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
      return new Coordinate(
          getGameManager().getScreen_width() * 0.45f, getGameManager().getScreen_height() * 0.65f);
    }
  }

  @Override
  public void pause() {}

  @Override
  public void load() {}
}
