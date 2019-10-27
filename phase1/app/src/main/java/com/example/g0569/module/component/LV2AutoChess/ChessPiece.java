package com.example.g0569.module.component.LV2AutoChess;

import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.utils.Coordinate;


public class ChessPiece extends NonPlayerItem {
    //    should we implement classes for chess pieces?
//    These classes to be the type of this instance variable?
//    private Coordinate coordinate;
    private String appearance;
    private int power; // The amount for the chess piece can attack in one round.
    private int health; // The amount of the hp for the chess piece.

    public ChessPiece(int x, int y, int power, int health) {
        super(new Coordinate(x,y));
        this.power = power;
        this.health = health;
    }



//    private void set_location(float x, float y){
//        this.coordinate.setXY(x,y);
//    } This method should be implemented in the LevelTwoPlayer.

    @Override
    public void draw() {

    }

    @Override
    public void action() {

    }
}
