package com.example.g0569.mazegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;

public class MazeActivity extends AppCompatActivity {

    private MazeView mazeView;
    private MazeContract.Presenter presenter;
    private boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (mazeView == null) {
//            mazeView = new MazeView(this);
//        }
        setContentView(R.layout.activity_mazegame);
        mazeView = findViewById(R.id.mazeview);
        final LinearLayout menuLayout = findViewById(R.id.menu_layout);
        menuLayout.setVisibility(View.GONE);
        Button menuBtn = findViewById(R.id.meny_btn);
        Bundle bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        presenter = new MazePresenter(mazeView, inventory);

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
    protected void onStart() {
        super.onStart();
        presenter.start();
    }
}
