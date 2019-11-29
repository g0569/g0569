package com.example.g0569.savegame.model;

import com.example.g0569.utils.Inventory;

import java.util.Date;

public class SaveGame {
  private Date createdTime;
  private int saveId;
  private int progress;
  private int uid;
  private Inventory inventory;

  public SaveGame(Date createdTime, int saveId, int progress, String inventoryData, int uid) {
    this.createdTime = createdTime;
    this.saveId = saveId;
    this.progress = progress;
    this.uid = uid;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public int getSaveId() {
    return saveId;
  }

  public void setSaveId(int saveId) {
    this.saveId = saveId;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public String getStringInventory() {
    return null;
  }

  public int getProgress() {
    return progress;
  }

  public void setProgress(int progress) {
    this.progress = progress;
  }

  public int getUid() {
    return uid;
  }
}
