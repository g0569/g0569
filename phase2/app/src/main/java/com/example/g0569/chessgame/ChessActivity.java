package com.example.g0569.chessgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public class ChessActivity extends AppCompatActivity {

    private ChessView chessView;
    private ChessContract.Presenter presenter;
    private boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (chessView == null){
//            chessView = new ChessView(this);
//        }

        Bundle bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        NPC selectedNPC = (NPC) bundle.getSerializable(Constants.BUNDLE_SELECTEDNPC_KEY);
        setContentView(R.layout.activity_chessgame);
        chessView = findViewById(R.id.chessview);
        NPC npc = new NPC(6, "test_npc_6", 20, "fire", "hard", "type5", "type1,1,3.type2,2,3.type3,2,4");
        presenter = new ChessPresenter(chessView, inventory, npc);

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
}
