package com.example.g0569.chessgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

public interface ChessContract {
  interface View extends BaseView<Presenter> {}

  interface Presenter extends BasePresenter {}
}
