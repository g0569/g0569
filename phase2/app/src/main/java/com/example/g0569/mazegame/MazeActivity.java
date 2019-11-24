package com.example.g0569.mazegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.g0569.R;

public class MazeActivity extends AppCompatActivity {

    private MazeView mazeView;
    private MazeContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mazeView == null) {
            mazeView = new MazeView(this);
        }
        presenter = new MazePresenter();
        mazeView.setPresenter(presenter);
        setContentView(mazeView);
    }
}
