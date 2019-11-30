package com.example.g0569.main;

/** The type Main presenter. */
public class MainPresenter implements MainContract.Presenter {

  private final MainContract.View mainView;

  /**
   * Instantiates a new Main presenter.
   *
   * @param mainView the main view
   */
  MainPresenter(MainContract.View mainView) {
    this.mainView = mainView;
    mainView.setPresenter(this);
  }

  @Override
  public void start() {}
}
