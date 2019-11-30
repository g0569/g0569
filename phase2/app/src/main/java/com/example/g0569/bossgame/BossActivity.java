package com.example.g0569.bossgame;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;

/** The type Boss activity. */
public class BossActivity extends AppCompatActivity {

  private BossView bossView;
  private BossContract.Presenter presenter;
  private boolean isMenuVisible = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//    if (bossView == null) {
//      bossView = new BossView(this);
//    }
    setContentView(R.layout.activity_bossgame);
    bossView = findViewById(R.id.bossview);
    presenter = new BossPresenter(bossView);

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
  protected void onStart() {
    super.onStart();
    presenter.start();
  }


}
