package com.example.g0569.auth.model;

public class User {

  UserSQLiteAccessInterface sqLiteAccesser;
  private int uid;
  private String email;
  private String username;

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
