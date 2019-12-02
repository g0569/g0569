package com.example.g0569.scoreboard.model;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** The type Score. */
public class Score {
  private Date createdTime;
  private int scoreId;
  private int uid;
  private String username;
  private int score;
  private int rank;
  /**
   * Instantiates a new Score.
   *
   * @param uid the uid
   * @param score the score
   * @param username the username
   */
  public Score(int uid, int score, String username) {
    this.uid = uid;
    this.score = score;
    this.username = username;
    this.createdTime = new Date();
  }

  /**
   * Instantiates a new Score.
   *
   * @param scoreId the score id
   * @param uid the uid
   * @param score the score
   * @param username the username
   */
  public Score(int scoreId, int uid, int score, String username, int rank) {
    this.scoreId = scoreId;
    this.uid = uid;
    this.score = score;
    this.username = username;
    this.rank = rank;
    this.createdTime = new Date();
  }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
   * Sets username.
   *
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets created time.
   *
   * @return the created time
   */
  public String getCreatedTime() {
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    return dateFormat.format(createdTime);
  }

  /**
   * Sets created time.
   *
   * @param createdTime the created time
   */
  public void setCreatedTime(String createdTime) {
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
      try {
          this.createdTime =  dateFormat.parse(createdTime);
      } catch (ParseException e) {
          e.printStackTrace();
          this.createdTime = new Date();
      }
  }

  /**
   * Gets score id.
   *
   * @return the score id
   */
  public int getScoreId() {
    return scoreId;
  }

  /**
   * Sets score id.
   *
   * @param scoreId the score id
   */
  public void setScoreId(int scoreId) {
    this.scoreId = scoreId;
  }

  /**
   * Gets uid.
   *
   * @return the uid
   */
  public int getUid() {
    return uid;
  }

  /**
   * Sets uid.
   *
   * @param uid the uid
   */
  public void setUid(int uid) {
    this.uid = uid;
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets score.
   *
   * @param score the score
   */
  public void setScore(int score) {
    this.score = score;
  }
}
