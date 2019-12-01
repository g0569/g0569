package com.example.g0569.scoreboard.model;

import com.example.g0569.base.SQLiteAccessInterface;
import com.example.g0569.savegame.model.SaveGame;
import com.example.g0569.utils.NPC;

import java.util.List;

/** The interface score board SQLite accessor interface. */
public interface ScoreBoardSQLiteAccessInterface extends SQLiteAccessInterface {
  /**
   * upload the current score to the database
   *
   * @param uid the uid of the user
   * @param score the score of the user
   */
  void uploadScore(int uid, int score);

  /**
   * get all the scores in the score board.
   *
   * @param saveGame the save game
   * @return the save game itself
   */
  SaveGame saveNewGame(SaveGame saveGame);

  /**
   * Gets available NPCs.
   *
   * @return the available NPCs
   */
  List<NPC> getAvailableNPCs();

  /**
   * Update the save game in the database
   *
   * @param saveGame the save game
   */
  void updateSaveGame(SaveGame saveGame);
}
