package com.example.g0569.scoreboard;

import com.example.g0569.utils.Inventory;

public class ScoreBoardPresenter implements ScoreBoardContract.Presenter {

    private ScoreBoardContract.View view;
    private int currentScore;

    public ScoreBoardPresenter(ScoreBoardContract.View view, int bossScore, Inventory inventory) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
