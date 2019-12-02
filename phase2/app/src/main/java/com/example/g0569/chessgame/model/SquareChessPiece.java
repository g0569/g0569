package com.example.g0569.chessgame.model;

/**
 * The type Square chess piece.
 */
public class SquareChessPiece extends ChessPiece {
    /**
     * Instantiates a new Square chess piece.
     *
     * @param x The x coordinate for chess piece.
     * @param y The y coordinate for chess piece.
     */
SquareChessPiece(float x, float y) {
        super(x, y);
    }
    @Override
    public Integer[][] createTargetList() {
        Integer[][] target = new Integer[3][2];
        float column = this.getCoordinate().getX();
        if(column == (1 | 2)){// this means this chess piece is on player's side.
            //Square piece can only attack enemy chess piece in the "backline"
            target[0][0] = 4;
            target[1][0] = 4;
            target[2][0] = 4;
        }
        else {// this means this chess piece is on NPC's side.
            target[0][0] = 1;
            target[1][0] = 1;
            target[2][0] = 1;
        }
        //Square piece can attack enemy chess piece in any row, start searching from the first row
        target[0][1] = 1;
        target[1][1] = 2;
        target[2][1] = 3;
        return target;
    }
}
