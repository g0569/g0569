package com.example.g0569.module.game;

//import com.example.g0569.module.component.Player;
import com.example.g0569.module.component.*;

import java.util.List;

public class AutoChessGame extends Game {
    private List<ChessPiece> NPC1_ChessPiece;
    private List<ChessPiece> NPC2_ChessPiece;
    private int round;
    private List data; // save the data.

    public AutoChessGame(){
        round = 1;
        NPC1_ChessPiece.add(new StarChessPiece(1,0));
        NPC1_ChessPiece.add(new TriangleChessPiece(1,1));
        NPC1_ChessPiece.add(new StarChessPiece(1,2));
    }
    public boolean autoFight(Player player){
        // return the win or lose result in boolean expression.
        if(round == 1){
            // set up an array to take the result of battle of each row. (boolean)
            new List<Object> battle_result;
            // start fighting: let every player's chess piece take action
            for(ChessPiece chess: player.inventory){
                int row = chess.corrdinate.getX();
                battle_result.add(chess.action(NPC1_ChessPiece.get(row)));// coordinator must start from 0
            }
            NPC1_ChessPiece.clear();
        }
        if(round == 2){
            // place the NPC's chesspieces on chessboard.
            NPC2_ChessPiece.add(new StarChessPiece(1,0));
            NPC2_ChessPiece.add(new TriangleChessPiece(1,1));
            NPC2_ChessPiece.add(new TriangleChessPiece(1,2));
            // set up an array to take the result of battle of each row. (boolean)
            new List<Object> battle_result;
            // start fighting: let every player's chess piece take action
            for(ChessPiece chess: player.inventory){
                int row = chess.corrdinate.getX();
                battle_result.add(chess.action(NPC1_ChessPiece.get(row)));// coordinator must start from 0
                }
        }
        new int win;
        for(boolean result: battle_result){
            if(result){
                win ++;
            }
        }
        round ++; // check if round is over limit in the manager.
        return(win >= 2);
    }

    public void showOutput(){
        // what does this method do?
    }



    @Override
    public void pause() {

    }

    @Override
    public void load() {

    }
}
