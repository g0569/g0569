package com.example.g0569.chessgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.g0569.R;
import com.example.g0569.base.BaseActivity;
import com.example.g0569.mazegame.MazeActivity;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;

/** The type Chess activity. */
public class ChessActivity extends BaseActivity {

    private ChessView chessView;
    private ChessContract.Presenter presenter;
    private ConstraintLayout dialogue;
    private LinearLayout menuLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (chessView == null){
//            chessView = new ChessView(this);
//        }

        bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        int selectedIndex = bundle.getInt(Constants.BUNDLE_SELECTEDNPC_KEY);
        setContentView(R.layout.activity_chessgame);
        chessView = findViewById(R.id.chessview);
        presenter = new ChessPresenter(chessView, inventory, selectedIndex);

        dialogue = findViewById(R.id.dialogue);

        menuLayout = findViewById(R.id.menu_layout);
        menuLayout.setVisibility(View.GONE);
        Button menuBtn = findViewById(R.id.meny_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(menuLayout);
            }
        });
        findViewById(R.id.menu_load_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoadPage();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.start();
    }

  /** To maze game. */
  public void toMazeGame() {
    Intent intent = new Intent(this, MazeActivity.class);
    intent.putExtra(Constants.CHESS_GAME_OVER, Constants.CHESS_GAME_OVER);
    intent.putExtras(bundle);
    startActivity(intent);
    finish();
    // TODO
  }

  /**
   * Show ending dialogue.
   *
   * @param title the title
   * @param text the text
   * @param buttonHint the button hint
   */
  public void showEndingDialogue(String title, String text, String buttonHint) {
        ((TextView) dialogue.findViewById(R.id.dialogue_title)).setText(title);
        ((TextView) dialogue.findViewById(R.id.dialogue_text)).setText(text);
        Button dialogueButton = dialogue.findViewById(R.id.dialogue_btn_1);
        dialogueButton.setText(buttonHint);
        dialogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMazeGame();
            }
        });
        Button dialogueButton2 = dialogue.findViewById(R.id.dialogue_btn_2);
        dialogueButton2.setText("Restart the Chess Game");
        dialogueButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMenuVisible = true;
                menuLayout.setVisibility(View.GONE);
                presenter.resetChessPiece();
            }
        });
        dialogue.setVisibility(View.VISIBLE);

    }
}
