package com.example.g0569.scoreboard;

import com.example.g0569.auth.model.User;
import com.example.g0569.scoreboard.model.Score;
import com.example.g0569.scoreboard.model.ScoreBoardSQLiteAccessInterface;
import com.example.g0569.utils.Inventory;

import java.util.List;

/**
 * The type Score board presenter.
 */
public class ScoreBoardPresenter implements ScoreBoardContract.Presenter {

  private ScoreBoardContract.View view;
  private Score currentScore;
  private ScoreBoardSQLiteAccessInterface scoreBoardSQLiteAccessor;

  /**
   * Instantiates a new Score board presenter.
   *
   * @param bossScore the boss score
   * @param inventory the inventory
   * @param user the user
   */
public ScoreBoardPresenter(int bossScore, Inventory inventory, User user) {
    int currentScore =
        (50 - bossScore) * 100
            + inventory.getAvailableItem().size() * 50
            + inventory.getCollectedItem().size() * 10;
    this.currentScore = new Score(user.getUid(), currentScore, user.getUsername());
  }

  @Override
  public void start() {}

  @Override
  public Score getCurrentScore() {
    return currentScore;
  }

  @Override
  public List<Score> getScoreList() {
    return scoreBoardSQLiteAccessor.getAllScores();
  }

  @Override
  public void setView(ScoreBoardContract.View view) {
    this.view = view;
  }

  /**
   * Sets score board sq lite accessor.
   *
   * @param scoreBoardSQLiteAccessor the score board sq lite accessor
   */
public void setScoreBoardSQLiteAccessor(
      ScoreBoardSQLiteAccessInterface scoreBoardSQLiteAccessor) {
    this.scoreBoardSQLiteAccessor = scoreBoardSQLiteAccessor;
    scoreBoardSQLiteAccessor.uploadScore(currentScore);
  }
}
