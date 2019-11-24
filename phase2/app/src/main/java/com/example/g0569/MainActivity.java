package com.example.g0569;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.g0569.module.game.GameManager;
import com.example.g0569.utils.Constants;
import com.example.g0569.view.BossView;
import com.example.g0569.view.ChessView;
import com.example.g0569.view.MainMenuView;
import com.example.g0569.mazegame.MazeView;

import java.util.List;

// import com.example.g0569.view.BossView;

/** The Main activity. */
public class MainActivity extends Activity implements View.OnClickListener {

  private MainMenuView mainMenuView;
  private GameManager gameManager;
  private ChessView chessview;
  private BossView bossView;
  private MazeView mazeView;

  @SuppressLint("HandlerLeak")
  private Handler handler =
      new Handler() {
        @Override
        public void handleMessage(Message msg) {
          switch (msg.what) {
            case Constants.TO_MENU_VIEW:
              toMenuView();
              break;
            case Constants.TO_CHESS_VIEW:
              toChessView();
              break;
            case Constants.TO_BOSS_VIEW:
              toBossView();
              break;
            case Constants.TO_MAZE_VIEW:
              toMazeView();
              break;
            case Constants.TO_DEMO_VIEW:
              toDemoView();
              break;
          }
        }
      };

  /**
   * Append statistic view to current view.
   *
   * @param statistics the list of statistic in String
   */
  public void appendStatisticView(List<String> statistics) {
    FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(700, 400);
    params1.gravity = Gravity.CENTER;
    addContentView(LayoutInflater.from(this).inflate(R.layout.page_statistic, null), params1);

    TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, 100);
    params2.weight = 1;
    params2.gravity = Gravity.CENTER_VERTICAL;
    params2.setMargins(2, 2, 2, 2);

    TableLayout statistic_table = (TableLayout) findViewById(R.id.statistic_table);
    for (String statistic : statistics) {
      TableRow tr = new TableRow(this);
      TextView t = new TextView(this);
      t.setText(statistic);
      t.setTextColor(Color.WHITE);
      tr.addView(t, params2);
      statistic_table.addView(tr);
    }
  }

  private void toBossView() {
    setNull();
    setContentView(new BossView(this));
  }

  private void toDemoView() {
    setNull();
    setContentView(R.layout.page_demo);
    ((TextView) findViewById(R.id.welcome_text))
        .setText("Welcome Back, " + gameManager.getUser().getUsername());
  }

  private void toMazeView() {
    setNull();
    setContentView(new MazeView(this));
  }

  private void setNull() {
    mainMenuView = null;
    bossView = null;
    mazeView = null;
    chessview = null;
  }

  private void toMenuView() {
    setNull();
    mainMenuView = new MainMenuView(this);
    setContentView(mainMenuView);
  }

  private void toChessView() {
    setNull();
    setContentView(new ChessView(this));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    mainMenuView = new MainMenuView(this);
    gameManager = new GameManager(this);
  }

  /**
   * Return the handler in MainActivity
   *
   * @return the handler
   */
  public Handler getHandler() {
    return handler;
  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case (R.id.login_button):
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.passsword);
        gameManager.login(email.getText().toString(), password.getText().toString());
        break;
      case (R.id.signup_button):
        gameManager.signUp(
            ((EditText) findViewById(R.id.signup_input_email)).getText().toString(),
            ((EditText) findViewById(R.id.signup_input_name)).getText().toString(),
            ((EditText) findViewById(R.id.signup_input_password)).getText().toString());
        break;
      case (R.id.btn_to_boss):
        gameManager.toBossGame();
        break;
      case (R.id.btn_to_chess):
        gameManager.toChessGame();
        break;
      case (R.id.btn_to_maze):
        gameManager.toMazeGame();
        break;
      case (R.id.btn_to_main):
        toMenuView();
        break;
      case (R.id.rtn_btn_statistic):
        toDemoView();
        break;
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      toDemoView();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  /**
   * return the game manager.
   *
   * @return the game manager
   */
  public GameManager getGameManager() {
    return gameManager;
  }

  /**
   * return the main menu view.
   *
   * @return the main menu view
   */
  public MainMenuView getMainMenuView() {
    return mainMenuView;
  }
}
