package com.example.g0569.chessgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.mazegame.MazeActivity;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public class ChessActivity extends AppCompatActivity {

    private ChessView chessView;
    private ChessContract.Presenter presenter;
    private boolean isMenuVisible = false;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (chessView == null){
//            chessView = new ChessView(this);
//        }

        bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        NPC selectedNPC = (NPC) bundle.getSerializable(Constants.BUNDLE_SELECTEDNPC_KEY);
        setContentView(R.layout.activity_chessgame);
        chessView = findViewById(R.id.chessview);
        presenter = new ChessPresenter(chessView, inventory, selectedNPC);

        final LinearLayout menuLayout = findViewById(R.id.menu_layout);
        menuLayout.setVisibility(View.GONE);
        Button menuBtn = findViewById(R.id.meny_btn);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuVisible) {
                    isMenuVisible = false;
                    menuLayout.setVisibility(View.VISIBLE);
                } else {
                    isMenuVisible = true;
                    menuLayout.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.start();
    }

    public void toMazeGame() {
    Intent intent = new Intent(this, MazeActivity.class);
    intent.putExtra(Constants.CHESS_GAME_OVER, Constants.CHESS_GAME_OVER);
    intent.putExtras(bundle);
    startActivity(intent);
    finish();
    // TODO
  }
}
