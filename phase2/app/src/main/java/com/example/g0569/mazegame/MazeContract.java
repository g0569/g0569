package com.example.g0569.mazegame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

public interface MazeContract {
  interface View extends BaseView<Presenter> {
  }

  interface Presenter extends BasePresenter {}
}
