package com.example.g0569.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.bossgame.BossActivity;
import com.example.g0569.chessgame.ChessActivity;
import com.example.g0569.savegame.SaveGameActivity;
import com.example.g0569.utils.ActivityManager;
import com.example.g0569.utils.Constants;

/** The type Base activity. */
public class BaseActivity extends AppCompatActivity {
  /** The Bundle. */
  public Bundle bundle;

  /** The Is menu visible. */
  public boolean isMenuVisible = false;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityManager.getInstance().addActivity(this);
  }

  /**
   * To chess game.
   *
   * @param selectedIndex the index of the selected npc
   */
  public void toChessGame(int selectedIndex) {
    bundle.putInt(Constants.BUNDLE_SELECTEDNPC_KEY, selectedIndex);
    Intent intent = new Intent(this, ChessActivity.class);
    intent.putExtras(bundle);
    startActivity(intent);
    finish();
  }

  /** To boss game. */
  public void toBossGame() {
    Intent intent = new Intent(this, BossActivity.class);
    intent.putExtras(bundle);
    startActivity(intent);
    finish();
  }

  /** To load page. */
  public void toLoadPage() {
    Intent intent = new Intent(this, SaveGameActivity.class);
    intent.putExtras(bundle);
    ActivityManager.getInstance().finishAllActivity();
    startActivity(intent);
  }

  /**
   * Show menu.
   *
   * @param menuLayout the menu layout
   */
  public void showMenu(LinearLayout menuLayout) {
    if (isMenuVisible) {
      isMenuVisible = false;
      menuLayout.setVisibility(View.GONE);
    } else {
      isMenuVisible = true;
      menuLayout.setVisibility(View.VISIBLE);
    }
  }
}
