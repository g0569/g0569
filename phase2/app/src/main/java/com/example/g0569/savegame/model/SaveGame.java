package com.example.g0569.savegame.model;

import com.example.g0569.mazegame.model.SaveMaze;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.Utils;

import java.io.Serializable;
import java.util.Date;

/** A single Save game stored in the database. */
public class SaveGame implements Serializable {
  private Date createdTime;
  private int saveId;
  private int progress;
  private int uid;
  private Inventory inventory;
  private boolean isNewGame;
  private SaveMaze saveMaze;
  /**
   * create a new Save game from the database.
   *
   * @param createdTime the created time of this save
   * @param saveId the save id in the database
   * @param progress the progress of current save game
   * @param inventoryData the serialized inventory data
   * @param uid the uid of the user who has this save game
   * @param saveMazeData the save maze data
   * @throws Exception the exception
   */
  SaveGame(
      Date createdTime,
      int saveId,
      int progress,
      String inventoryData,
      int uid,
      String saveMazeData)
      throws Exception {
    this.createdTime = createdTime;
    this.saveId = saveId;
    this.progress = progress;
    this.uid = uid;
    this.isNewGame = false;
    this.inventory = (Inventory) Utils.deserializeToObject(inventoryData);
    this.saveMaze = (SaveMaze) Utils.deserializeToObject(saveMazeData);
  }

  /**
   * Instantiates a new Save game with the flag indicating if this is a new save game.
   *
   * @param createdTime the created time of this save
   * @param saveId the save id in the database
   * @param progress the progress of current save game
   * @param uid the uid of the user who has this save game
   * @param isNewGame indicates if this is a new save game
   */
  SaveGame(Date createdTime, int saveId, int progress, int uid, boolean isNewGame) {
    this.createdTime = createdTime;
    this.saveId = saveId;
    this.progress = progress;
    this.uid = uid;
    this.isNewGame = isNewGame;
    this.saveMaze = new SaveMaze();
  }

  /**
   * Gets save maze.
   *
   * @return the save maze
   */
  public SaveMaze getSaveMaze() {
    return saveMaze;
  }

  /**
   * Sets save maze.
   *
   * @param saveMaze the save maze
   */
  public void setSaveMaze(SaveMaze saveMaze) {
    this.saveMaze = saveMaze;
  }

  /**
   * Gets the created time of this save game.
   *
   * @return the created time
   */
  public Date getCreatedTime() {
    return createdTime;
  }

  /**
   * Sets the created time of this save game.
   *
   * @param createdTime the created time
   */
  void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * Gets save id.
   *
   * @return the save id
   */
  int getSaveId() {
    return saveId;
  }

  /**
   * Sets save id.
   *
   * @param saveId the save id
   */
  void setSaveId(int saveId) {
    this.saveId = saveId;
  }

  /**
   * Gets inventory.
   *
   * @return the inventory
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Sets inventory.
   *
   * @param inventory the inventory
   */
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Gets string inventory.
   *
   * @return the string inventory
   */
  String getStringInventory() {
    String serializedInventory = null;
    try {
      serializedInventory = Utils.serializeToString(this.inventory);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return serializedInventory;
    }
  }

  /**
   * Get string representative of maze save.
   *
   * @return the string
   */
  String getStringMazeSave() {
    String serializedMazeData = null;
    try {
      serializedMazeData = Utils.serializeToString(this.saveMaze);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return serializedMazeData;
    }
  }

  /**
   * Gets progress.
   *
   * @return the progress
   */
  public int getProgress() {
    return progress;
  }

  /**
   * Sets progress.
   *
   * @param progress the progress
   */
  void setProgress(int progress) {
    this.progress = progress;
  }

  /**
   * Gets uid.
   *
   * @return the uid
   */
  int getUid() {
    return uid;
  }

  /**
   * Is new game boolean.
   *
   * @return the boolean
   */
  public boolean isNewGame() {
    return isNewGame;
  }

  /**
   * Sets new game.
   *
   * @param newGame the new game
   */
  void setNewGame(boolean newGame) {
    isNewGame = newGame;
  }
}
