package com.example.g0569.chessgame.model;

import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;

public class CircleChessPiece extends ChessPiece {
    /**
     * @param x      The x coordinate for chess piece.
     * @param y      The y coordinate for chess piece.
     */
    CircleChessPiece(float x, float y) {
        super(x, y);
    }

    @Override
    public Integer[][] createTargetList() {
        Integer[][] target = new Integer[1][2];
        float column = this.getCoordinate().getX();
        if(column == (1 | 2)){target[0][0] = 3;}
        else {target[0][0] = 2;}
        target[0][1] = this.getCoordinate().getIntY();
        return target;
    }
}
