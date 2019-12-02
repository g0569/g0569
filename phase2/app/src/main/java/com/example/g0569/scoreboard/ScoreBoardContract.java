package com.example.g0569.scoreboard;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.scoreboard.model.Score;

import java.util.List;

/** The ScoreBoard contract interface. */
public interface ScoreBoardContract {
  /** The interface View. */
interface View extends BaseView<Presenter> {}

  /** The interface Presenter. */
interface Presenter extends BasePresenter {
    /**
     * Gets current score.
     *
     * @return the current score
     */
Score getCurrentScore();

    /**
     * Gets score list.
     *
     * @return the score list
     */
List<Score> getScoreList();

    /**
     * Sets view.
     *
     * @param view the view
     */
void setView(View view);
  }
}
