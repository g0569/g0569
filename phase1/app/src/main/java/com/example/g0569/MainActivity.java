package com.example.g0569;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.example.g0569.utils.Constants;
import com.example.g0569.view.BossView;
import com.example.g0569.view.MainMenuView;

public class MainActivity extends Activity {

    private MainMenuView mainMenuView;

  @SuppressLint("HandlerLeak")
  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      if (msg.what == Constants.TO_MENU_VIEW) {
        toMenuView();
      } else if (msg.what == Constants.TO_LOGIN_VIEW) {
        toLoginView();
      }
    }
  };

  private void toMenuView() {
    if (mainMenuView == null) {
      mainMenuView = new MainMenuView(this);
    }
    setContentView(mainMenuView);
  }

  private void toLoginView() {
    setContentView(R.layout.page_login);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    MainMenuView mainMenuView = new MainMenuView(this);
    setContentView(mainMenuView);
//    BossView bossView = new BossView(this);
//    setContentView(bossView);
  }

  public Handler getHandler() {
    return handler;
  }
}
