package com.example.g0569.bossgame;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;

/** The type Boss activity. */
public class BossActivity extends AppCompatActivity {

  private BossView bossView;
  private BossContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//    this.getWindow()
//        .setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    if (bossView == null) {
      bossView = new BossView(this);
    }

    setContentView(bossView);
    Bundle bundle = getIntent().getExtras();
    Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
    presenter = new BossPresenter(bossView, inventory);
  }


  @Override
  protected void onStart() {
    super.onStart();
    presenter.start();
  }


}
