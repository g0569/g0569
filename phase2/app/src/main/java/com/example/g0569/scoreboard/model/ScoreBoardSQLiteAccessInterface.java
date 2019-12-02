package com.example.g0569.scoreboard.model;

import com.example.g0569.base.SQLiteAccessInterface;

import java.util.List;

/** The interface score board SQLite accessor interface. */
public interface ScoreBoardSQLiteAccessInterface extends SQLiteAccessInterface {
  /**
   * upload the current score to the database
   *
   * @param score the score of the user
   */
void uploadScore(Score score);

  /**
   * get all the scores in the score board.
   *
   * @return all the scores
   */
List<Score> getAllScores();
}
