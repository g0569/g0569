package com.example.g0569.chessgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Coordinate;

public interface ChessContract {
  interface View extends BaseView<Presenter> {
    void initView();
  }

  interface Presenter extends BasePresenter {
    Coordinate getChessPieceCoordinate();
  }
}
