package com.example.g0569.savegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.bossgame.BossActivity;
import com.example.g0569.mazegame.MazeActivity;
import com.example.g0569.savegame.model.SaveGameSQLiteAccessor;
import com.example.g0569.utils.SQLiteHelper;

/** The Save game activity. */
public class SaveGameActivity extends AppCompatActivity {

  SQLiteHelper sqLitehelper = new SQLiteHelper(this, "g0569");

  private SaveGamePresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_container);
    SaveGameFragment mView =
        (SaveGameFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);

    // initialize and set up then presenter & view here

    Bundle bundle = getIntent().getExtras();

    if (mView == null) {
      mView = SaveGameFragment.newInstance();
      getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame, mView).commit();
    }

    presenter = new SaveGamePresenter(mView, bundle);
    SaveGameSQLiteAccessor saveGameSQLiteAccessor = new SaveGameSQLiteAccessor();
    saveGameSQLiteAccessor.setSQLiteHelper(sqLitehelper);
    presenter.setSaveGameSQLiteAccessor(saveGameSQLiteAccessor);
  }

  /**
   * Start the maze game with the bundle.
   *
   * @param bundle the bundle that need to pass to MazeActivity
   */
  public void startMazeGame(Bundle bundle) {
    Intent intent = new Intent(this, BossActivity.class);
    intent.putExtras(bundle);
    startActivity(intent);
  }
}
