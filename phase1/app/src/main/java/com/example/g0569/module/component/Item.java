package com.example.g0569.module.component;

import com.example.g0569.module.utils.Coordinate;

abstract class Item {
    private Coordinate coordinate;

    public abstract void draw();

    /**
     * Update
     */
    public abstract void action();
}
