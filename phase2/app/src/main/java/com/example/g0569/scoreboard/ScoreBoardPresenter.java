package com.example.g0569.scoreboard;

public class ScoreBoardPresenter implements ScoreBoardContract.Presenter {

    private ScoreBoardContract.View view;

    public ScoreBoardPresenter(ScoreBoardContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
