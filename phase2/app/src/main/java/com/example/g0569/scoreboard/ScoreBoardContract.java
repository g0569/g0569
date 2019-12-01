package com.example.g0569.scoreboard;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

/** The ScoreBoard contract interface. */
public interface ScoreBoardContract {
  /** The interface View. */
  interface View extends BaseView<Presenter> {}

  /** The interface Presenter. */
  interface Presenter extends BasePresenter {}
}
