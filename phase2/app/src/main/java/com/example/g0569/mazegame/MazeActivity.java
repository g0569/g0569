package com.example.g0569.mazegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.g0569.R;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;

public class MazeActivity extends AppCompatActivity {

    private MazeView mazeView;
    private MazeContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mazeView == null) {
            mazeView = new MazeView(this);
        }
        setContentView(mazeView);
        Bundle bundle = getIntent().getExtras();
        Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
        presenter = new MazePresenter(mazeView, inventory);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }
}
