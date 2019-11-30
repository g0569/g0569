package com.example.g0569.chessgame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public class ChessActivity extends AppCompatActivity {

    private ChessView chessView;
    private ChessContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (chessView == null){
            chessView = new ChessView(this);
        }

        Bundle bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        NPC selectedNPC = (NPC) bundle.getSerializable(Constants.BUNDLE_SELECTEDNPC_KEY);
        presenter = new ChessPresenter(chessView, inventory, selectedNPC);
        setContentView(chessView);
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.start();
    }
}
