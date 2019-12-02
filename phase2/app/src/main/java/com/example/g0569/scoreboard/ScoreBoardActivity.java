package com.example.g0569.scoreboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.g0569.R;
import com.example.g0569.auth.model.User;
import com.example.g0569.base.BaseActivity;
import com.example.g0569.savegame.SaveGameActivity;
import com.example.g0569.scoreboard.model.ScoreBoardSQLiteAccessor;
import com.example.g0569.utils.ActivityManager;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.SQLiteHelper;

public class ScoreBoardActivity extends BaseActivity {

  private ScoreBoardPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    bundle = getIntent().getExtras();
    int bossScore = getIntent().getIntExtra(Constants.BUNDLE_BOSSSCORE_KEY, 0);
    ScoreBoardSQLiteAccessor scoreBoardSQLiteAccessor = new ScoreBoardSQLiteAccessor();
    scoreBoardSQLiteAccessor.setSQLiteHelper(new SQLiteHelper(this, "g0569"));
    Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
    User user = (User) bundle.getSerializable(Constants.BUNDLE_USER_KEY);

    presenter = new ScoreBoardPresenter(bossScore, inventory, user);
    presenter.setScoreBoardSQLiteAccessor(scoreBoardSQLiteAccessor);

    ScoreBoardFragment scoreBoardFragment =
        (ScoreBoardFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);
    if (scoreBoardFragment == null) {
      scoreBoardFragment = ScoreBoardFragment.newInstance();
      getSupportFragmentManager()
              .beginTransaction()
              .replace(R.id.ContentFrame, scoreBoardFragment)
              .commit();
    }
    setContentView(R.layout.activity_container);

    scoreBoardFragment.setPresenter(presenter);
    presenter.setView(scoreBoardFragment);

  }

}
