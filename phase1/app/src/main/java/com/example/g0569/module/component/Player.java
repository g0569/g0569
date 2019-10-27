package com.example.g0569.module.component;

import com.example.g0569.module.utils.Coordinate;

import java.util.List;

public abstract class Player extends Item{

    protected Coordinate coordinate;
    private List<NonPlayerItem> inventory;

    public Player() {
    }

    public abstract void action();

}
