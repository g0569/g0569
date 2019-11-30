package com.example.g0569.auth.model;

import com.example.g0569.base.SQLiteAccessInterface;

/** The interface for the User SQLite accessor. */
public interface UserSQLiteAccessInterface extends SQLiteAccessInterface {
  /**
   * Gets user name with given uid.
   *
   * @param uid the uid of the user
   * @return the user name of the user
   */
  String getUserName(int uid);
}
