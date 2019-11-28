package com.example.g0569.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** The helper to operate SQLite. */
public class SQLiteHelper extends SQLiteOpenHelper {

  private static int version = 1;

  /**
   * Instantiates a new SQLite helper.
   *
   * @param context the context
   * @param name the name of the table
   */
  public SQLiteHelper(Context context, String name) {
    super(context, name, null, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String sql =
        "CREATE TABLE if not exists users ("
            + "  uid INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "  username TEXT NOT NULL,"
            + "  password TEXT NOT NULL,"
            + "  email TEXT NOT NULL,"
            + "  created_time DATE NOT NULL"
            + ");";
    db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
