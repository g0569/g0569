package com.example.g0569.savegame.model;

import com.example.g0569.base.SQLiteAccessInterface;
import com.example.g0569.utils.NPC;

import java.util.List;

/** The interface Save game SQLite accessor interface. */
public interface SaveGameSQLiteAccessInterface extends SQLiteAccessInterface {
  /**
   * Gets all the save games for a given user.
   *
   * @param uid the uid of the user
   * @return the save games
   */
  List<SaveGame> getSaveGames(int uid);

  /**
   * Save a new save game.
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
