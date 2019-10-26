package com.example.g0569.module.game;

import com.example.g0569.MainActivity;
import com.example.g0569.module.User;

public class GameManager {

    private MainActivity mainActivity;
    private Game currentGame;
    private User user;


    public GameManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void changeGame(int nextGame) {
//        call this.save()
        // TODO
    }

    public boolean login(String email, String password) {
        // TODO
        return true;
    }

    public void save() {
//        call currentGame.save() and store to SQLite using user.save(arg)
    }

}
