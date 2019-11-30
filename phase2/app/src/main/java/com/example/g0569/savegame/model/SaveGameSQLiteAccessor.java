package com.example.g0569.savegame.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.utils.NPC;
import com.example.g0569.utils.SQLiteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** The Save game SQLite accessor. */
public class SaveGameSQLiteAccessor implements SaveGameSQLiteAccessInterface {

  private SQLiteHelper sqliteHelper;

  @SuppressLint("SimpleDateFormat")
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

  @Override
  public List<SaveGame> getSaveGames(int uid) {
    SQLiteDatabase db = sqliteHelper.getReadableDatabase();
    List<SaveGame> saveGames = new ArrayList<>();
    saveGames.add(new SaveGame(new Date(), -1, 0, uid, true));

    Cursor cursor =
        db.query(
            "users_saves",
            new String[] {"save_id", "inventory_data", "created_time", "progress"},
            "uid=?",
            new String[] {String.valueOf(uid)},
            null,
            null,
            null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      int saveId = cursor.getInt(0);
      String inventoryData = cursor.getString(1);
      String createdTimeString = cursor.getString(2);
      Date createdTime;
      try {
        createdTime = dateFormat.parse(createdTimeString);
      } catch (ParseException e) {
        createdTime = new Date();
      }
      int progress = cursor.getInt(3);
      try {
        saveGames.add(new SaveGame(createdTime, saveId, progress, inventoryData, uid));
      } catch (Exception e) {
        e.printStackTrace();
      }
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return saveGames;
  }

  @Override
  public SaveGame saveNewGame(SaveGame saveGame) {
    SQLiteDatabase db = sqliteHelper.getWritableDatabase();
    try {
      ContentValues contentValues = new ContentValues();
      contentValues.put("uid", saveGame.getUid());
      contentValues.put("progress", 0);
      contentValues.put("inventory_data", saveGame.getStringInventory());
      contentValues.put("created_time", dateFormat.format(saveGame.getCreatedTime()));
      db.insert("users_saves", null, contentValues);

    } catch (Exception ignored) {
    } finally {
      db.close();
    }
    return saveGame;
  }

  @Override
  public List<NPC> getAvailableNPCs() {
    SQLiteDatabase db = sqliteHelper.getReadableDatabase();
    List<NPC> allNPC = new ArrayList<>();
    Cursor cursor =
        db.query(
            "npc_data",
            new String[] {
              "npc_id", "npc_name", "damage", "power", "chess_layout", "difficulty", "type"
            },
            null,
            null,
            null,
            null,
            null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      int npcId = cursor.getInt(0);
      String npcName = cursor.getString(1);
      int damage = cursor.getInt(2);
      String power = cursor.getString(3);
      String chessLayout = cursor.getString(4);
      String difficulty = cursor.getString(5);
      String type = cursor.getString(6);

      allNPC.add(new NPC(npcId, npcName, damage, power, difficulty, type, chessLayout));
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return allNPC;
  }

  @Override
  public void setSQLiteHelper(SQLiteHelper sqLiteHelper) {
    this.sqliteHelper = sqLiteHelper;
  }
}
