package com.example.g0569.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.g0569.chessgame.model.ChessSQLiteAccesser;

public class ChessActivity extends AppCompatActivity {

    private ChessView chessView;
    private ChessContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (chessView == null){
            chessView = new ChessView(this);
        }

        presenter = new ChessPresenter(chessView, new ChessSQLiteAccesser());
        setContentView(chessView);
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.start();
    }
}
