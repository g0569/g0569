package com.example.g0569.main;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

/** The Main contract interface. */
public interface MainContract {
  /** The interface View. */
  interface View extends BaseView<Presenter> {}

  /** The interface Presenter. */
  interface Presenter extends BasePresenter {}
}
