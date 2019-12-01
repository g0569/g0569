package com.example.g0569.main;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.utils.ActivityManager;

/** The MainActivity. */
public class MainActivity extends AppCompatActivity {

  private MainPresenter mainPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityManager.getInstance().addActivity(this);

    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_container);
    MainFragment mainFragment =
        (MainFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);

    // initialize and set up then presenter & view here
    if (mainFragment == null) {
      mainFragment = MainFragment.newInstance();
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.ContentFrame, mainFragment)
          .commit();
    }
    mainPresenter = new MainPresenter(mainFragment);
  }
}
