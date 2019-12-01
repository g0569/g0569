package com.example.g0569.scoreboard;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.g0569.R;
import com.example.g0569.base.BaseActivity;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;

public class ScoreBoardActivity extends BaseActivity {

    private ScoreBoardPresenter presenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_container);
    ScoreBoardFragment scoreBoardFragment =
        (ScoreBoardFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);

    Bundle bundle = getIntent().getExtras();
    int bossScore = bundle.getInt(Constants.BUNDLE_BOSSSCORE_KEY);
    Inventory inventory = (Inventory)  bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
    if (scoreBoardFragment == null) {
      scoreBoardFragment = ScoreBoardFragment.newInstance();
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.ContentFrame, scoreBoardFragment)
          .commit();
    }

    presenter = new ScoreBoardPresenter(scoreBoardFragment, bossScore, inventory);
  }
}
