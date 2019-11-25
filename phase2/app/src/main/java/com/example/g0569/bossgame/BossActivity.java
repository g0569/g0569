package com.example.g0569.bossgame;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/** The type Boss activity. */
public class BossActivity extends AppCompatActivity {

  private BossView bossView;
  private BossContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    if (bossView == null) {
      bossView = new BossView(this);
    }
    presenter = new BossPresenter(bossView);
    setContentView(bossView);
  }
}
