package com.example.g0569.bossgame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BossActivity extends AppCompatActivity {

    private BossView bossView;
    private BossContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (bossView == null) {
            bossView = new BossView(this);
        }
        presenter = new BossContract.Presenter() {
            @Override
            public void start() {

            }
        };
        bossView.setPresenter(presenter);
        setContentView(bossView);
    }
}
