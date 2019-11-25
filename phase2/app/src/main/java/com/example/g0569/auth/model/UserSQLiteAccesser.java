package com.example.g0569.auth.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.utils.SQLiteHelper;

public class UserSQLiteAccesser implements UserSQLiteAccessInterface {

  private SQLiteHelper sqliteHelper;

  @Override
  public String getUserName(int uid) {
    SQLiteDatabase db = sqliteHelper.getReadableDatabase();
    Cursor cursor =
        db.query(
            "users",
            new String[] {"username"},
            "uid=?",
            new String[] {String.valueOf(uid)},
            null,
            null,
            null);
    cursor.moveToNext();
    String username = cursor.getString(cursor.getColumnIndex("username"));
    db.close();
    return username;
  }

  @Override
  public void setSQLiteHelper(SQLiteHelper sqLiteHelper) {
    this.sqliteHelper = sqLiteHelper;
  }
}
