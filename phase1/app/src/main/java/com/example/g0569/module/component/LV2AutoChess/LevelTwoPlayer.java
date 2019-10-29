package com.example.g0569.module.component.LV2AutoChess;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.g0569.module.component.Player;
import com.example.g0569.module.game.Game;

public class LevelTwoPlayer extends Player {

    public LevelTwoPlayer(Game game) {
        super(game);
//        super(new Coordinate(0,0));
        getInventory().add(new StarChessPiece(0, 0, game));
        getInventory().add(new TriangleChessPiece(0, 0, game));
//        getInventory().add(new StarChessPiece(0,0));
        // Put three chess piece into the getInventory() area.
    }

//    private void placeChessPieces(int x, int y){
//        // get coordinate for selected chess piece from touching screen and place the chess piece.
//        // while loop to find the index of the chess piece has been selected.
////        getInventory().get().set_location(x,y);
////        getInventory().get().change_selected();
//        // should throw exceptions when x, y is out of range.
//    }
//
//    private void startGame(){
//        // a button for startGame.
//    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    @Override
    public void action() {

    }
}
