package com.example.g0569.module.component;

import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Item{

    protected Coordinate coordinate;
    private List<NonPlayerItem> inventory = new ArrayList<>();

    public Player(Game game) {
        super(game);
    }

    public abstract void action();

    public List<NonPlayerItem> getInventory() {
        return inventory;
    }
}
