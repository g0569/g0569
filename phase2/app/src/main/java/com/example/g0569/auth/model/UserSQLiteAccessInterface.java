package com.example.g0569.auth.model;

import com.example.g0569.base.SQLiteAccessInterface;

public interface UserSQLiteAccessInterface extends SQLiteAccessInterface {
  String getUserName(int uid);
}
