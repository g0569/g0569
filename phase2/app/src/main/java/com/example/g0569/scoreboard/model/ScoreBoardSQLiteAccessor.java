package com.example.g0569.scoreboard.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardSQLiteAccessor implements ScoreBoardSQLiteAccessInterface {

  private SQLiteHelper sqLiteHelper;

  @Override
  public void uploadScore(Score score) {
    SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
    try {
      ContentValues contentValues = new ContentValues();
      contentValues.put("uid", score.getUid());
      contentValues.put("created_time", score.getCreatedTime());
      contentValues.put("score", score.getScore());
      contentValues.put("username", score.getUsername());
      db.insert("scoreboard", null, contentValues);
      String sql = "select last_insert_rowid() from scoreboard";
      Cursor cursor = db.rawQuery(sql, null);
      int latestIndex = -1;
      if (cursor.moveToFirst()) {
        latestIndex = cursor.getInt(0);
      }
      score.setScoreId(latestIndex);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      db.close();
    }
  }

  @Override
  public List<Score> getAllScores() {
    List<Score> scoreList = new ArrayList<>();
    SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
    Cursor cursor =
        db.query(
            "scoreboard",
            new String[] {"id", "uid", "created_time", "score", "username"},
            null,
            null,
            null,
            null,
            "score DESC");
    cursor.moveToFirst();
    int i = 1;
    while (!cursor.isAfterLast()) {
      int id = cursor.getInt(0);
      int uid = cursor.getInt(1);
      String createdTime = cursor.getString(2);
      int score = cursor.getInt(3);
      String username = cursor.getString(4);
      Score score1 = new Score(id, uid, score, username, i);
      i++;
      scoreList.add(score1);
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return scoreList;
  }

  @Override
  public void setSQLiteHelper(SQLiteHelper sqLiteHelper) {
    this.sqLiteHelper = sqLiteHelper;
  }
}
