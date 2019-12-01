package com.example.g0569.scoreboard;

import com.example.g0569.auth.model.User;
import com.example.g0569.scoreboard.model.Score;
import com.example.g0569.scoreboard.model.ScoreBoardSQLiteAccessInterface;
import com.example.g0569.utils.Inventory;

import java.util.List;

public class ScoreBoardPresenter implements ScoreBoardContract.Presenter {

  private ScoreBoardContract.View view;
  private Score currentScore;
  private ScoreBoardSQLiteAccessInterface scoreBoardSQLiteAccessor;

  public ScoreBoardPresenter(
      ScoreBoardContract.View view, int bossScore, Inventory inventory, User user) {
    this.view = view;
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

    public void setScoreBoardSQLiteAccessor(ScoreBoardSQLiteAccessInterface scoreBoardSQLiteAccessor) {
        this.scoreBoardSQLiteAccessor = scoreBoardSQLiteAccessor;
    }
}
