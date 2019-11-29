package com.example.g0569.auth.model;

import java.io.Serializable;

public class User implements Serializable {

  private int uid;
  private String email;
  private String username;

  public User(int uid, String username) {
    this.username = username;
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

  public int getUid() {
    return uid;
  }

  public String getEmail() {
    return email;
  }
}
