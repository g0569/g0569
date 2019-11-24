package com.example.g0569.main;

public class MainPresenter implements MainContract.Presenter {


    private final MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;

        mainView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
