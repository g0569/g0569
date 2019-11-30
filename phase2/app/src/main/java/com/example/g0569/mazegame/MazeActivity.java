package com.example.g0569.mazegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.chessgame.ChessActivity;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

public class MazeActivity extends AppCompatActivity {

    private MazeView mazeView;
    private MazeContract.Presenter presenter;
    private boolean isMenuVisible = true;
    private boolean isInventoryVisible = true;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (mazeView == null) {
//            mazeView = new MazeView(this);
//        }
        setContentView(R.layout.activity_mazegame);
        mazeView = findViewById(R.id.mazeview);
        bundle = getIntent().getExtras();
        final Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);

        InventoryFragment inventoryView =
                (InventoryFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);
        if (inventoryView == null) {
            inventoryView = new InventoryFragment(inventory);
        }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ContentFrame, (Fragment) inventoryView)
                    .commit();

        presenter = new MazePresenter(mazeView, inventory);

        final LinearLayout menuLayout = findViewById(R.id.menu_layout);
        menuLayout.setVisibility(View.GONE);
        final FrameLayout inventoryLayout = findViewById(R.id.ContentFrame);

        inventoryLayout.setVisibility(View.GONE);
        Button menuBtn = findViewById(R.id.meny_btn);
        Button inventoryBtn = findViewById(R.id.mazegame_inventory_btn);
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
        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInventoryVisible){
                    isInventoryVisible = false;
                    inventoryLayout.setVisibility(View.VISIBLE);
                } else {
                    isInventoryVisible = true;
                    inventoryLayout.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    public void toChessGame(NPC selectedNPC) {
        bundle.putSerializable(Constants.BUNDLE_SELECTEDNPC_KEY, selectedNPC);
        Intent intent = new Intent(this, ChessActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
