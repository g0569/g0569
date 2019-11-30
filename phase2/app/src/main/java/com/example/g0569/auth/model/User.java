package com.example.g0569.auth.model;

import java.io.Serializable;

/** The user of the game. */
public class User implements Serializable {

  private int uid;
  private String username;

  /**
   * Instantiates a new User.
   *
   * @param uid the uid of the user
   * @param username the username of the user
   */
  public User(int uid, String username) {
    this.uid = uid;
    this.username = username;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets uid.
   *
   * @return the uid
   */
  public int getUid() {
    return uid;
  }
}
