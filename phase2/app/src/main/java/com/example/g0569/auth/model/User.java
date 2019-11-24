package com.example.g0569.auth.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.module.game.GameManager;

public class User {

  private int uid;
  private String email;
  private String username;
  UserSQLiteAccessInterface sqLiteAccesser;

  public User(int uid, UserSQLiteAccessInterface sqLiteAccesser) {
    this.sqLiteAccesser = sqLiteAccesser;
    this.username = sqLiteAccesser.getUserName(uid);
  }

  protected void loadSave() {
    // TODO
  }

  protected void save() {
    // TODO
  }

  public String getUsername() {
    return username;
  }
}
