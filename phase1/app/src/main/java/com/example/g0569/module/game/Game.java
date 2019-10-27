package com.example.g0569.module.game;

public abstract class Game {

    private GameManager gameManager;

    public Game(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public abstract void pause();

    public abstract void load();

}
