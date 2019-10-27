package com.example.g0569.module.component.LV2AutoChess;

import com.example.g0569.module.component.Player;
import com.example.g0569.module.utils.Coordinate;

public class LevelTwoPlayer extends Player {

    public LevelTwoPlayer(Coordinate coordinate ) {
        super(new Coordinate(0,0));
        inventory.add(new StarChessPiece(0,0));
        inventory.add(new TriangleChessPiece(0,0));
        inventory.add(new StarChessPiece(0,0));
        // Put three chess piece into the inventory area.

        // Should be modified to a fixed location.
        // let me pass the coordinate and List of items to the super class!
    }

    private void placeChessPieces(int x, int y){
        // get coordinate for selected chess piece from touching screen and place the chess piece.
        // while loop to find the index of the chess piece has been selected.
        inventory.get().set_location(x,y);
        inventory.get().change_selected();
        // should throw exceptions when x, y is out of range.
    }

    private void startGame(){
        // a button for startGame.
    }



    @Override
    public void draw() {
    }

    @Override
    public void action() {

    }
}
