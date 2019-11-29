package com.example.g0569.savegame.model;

import com.example.g0569.base.SQLiteAccessInterface;
import com.example.g0569.utils.NPC;

import java.util.List;

public interface SaveGameSQLiteAccessInterface extends SQLiteAccessInterface {
  List<SaveGame> getSaveGames(int uid);

  SaveGame saveNewGame(SaveGame saveGame);

  List<NPC> getAvaliableNPCs();
}
