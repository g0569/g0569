package com.example.g0569.savegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.mazegame.MazeActivity;
import com.example.g0569.savegame.model.SaveGameSQLiteAccesser;
import com.example.g0569.utils.SQLiteHelper;

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
      mView = mView.newInstance();
      getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame, mView).commit();
    }

    presenter = new SaveGamePresenter(mView, bundle);
    SaveGameSQLiteAccesser saveGameSQLiteAccesser = new SaveGameSQLiteAccesser();
    saveGameSQLiteAccesser.setSQLiteHelper(sqLitehelper);
    presenter.setSaveGameSQLiteAccesser(saveGameSQLiteAccesser);
  }

  public void startMazeGame(Bundle bundle) {
    Intent intent = new Intent(this, MazeActivity.class);
    intent.putExtras(bundle);
    startActivity(intent);
  }
}
