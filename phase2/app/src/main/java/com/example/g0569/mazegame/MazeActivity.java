package com.example.g0569.mazegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g0569.R;
import com.example.g0569.bossgame.BossActivity;
import com.example.g0569.chessgame.ChessActivity;
import com.example.g0569.savegame.model.SaveGame;
import com.example.g0569.savegame.model.SaveGameSQLiteAccessor;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.SQLiteHelper;

/** The type Maze activity. */
public class MazeActivity extends AppCompatActivity {

  /** The Inventory view. */
  InventoryFragment inventoryView;

  private MazeView mazeView;
  private MazeContract.Presenter presenter;
  private boolean isMenuVisible = false;
  private boolean isInventoryVisible = false;
  private Bundle bundle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //        if (mazeView == null) {
    //            mazeView = new MazeView(this);
    //        }
    setContentView(R.layout.activity_mazegame);
    mazeView = findViewById(R.id.mazeview);
    bundle = getIntent().getExtras();
    final SaveGame saveGame = (SaveGame) bundle.getSerializable(Constants.BUNDLE_SAVEGAME_KEY);
  try{
    Boolean fromChessGame =
            getIntent().getStringExtra(Constants.CHESS_GAME_OVER).equals(Constants.CHESS_GAME_OVER);
  } catch (NullPointerException e) {
    Boolean fromChessGame = false;
  }
    final Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);

    inventoryView =
        (InventoryFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);
    if (inventoryView == null) {
      inventoryView = new InventoryFragment(inventory);
    }
    final SaveGameSQLiteAccessor saveGameSQLiteAccessor = new SaveGameSQLiteAccessor();
    saveGameSQLiteAccessor.setSQLiteHelper(new SQLiteHelper(this, "g0569"));

    ((Switch) findViewById(R.id.maze_enable_acc))
        .setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                  mazeView.setEnableSensor(true);
                } else {
                  mazeView.setEnableSensor(false);
                }
              }
            });

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.ContentFrame, inventoryView)
        .commit();

    presenter = new MazePresenter(mazeView, inventory);

    final LinearLayout menuLayout = findViewById(R.id.menu_layout);
    menuLayout.setVisibility(View.GONE);
    final FrameLayout inventoryLayout = findViewById(R.id.ContentFrame);

    inventoryLayout.setVisibility(View.GONE);
    Button menuBtn = findViewById(R.id.meny_btn);
    Button inventoryBtn = findViewById(R.id.mazegame_inventory_btn);
    menuBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (isMenuVisible) {
              isMenuVisible = false;
              menuLayout.setVisibility(View.GONE);
            } else {
              isMenuVisible = false;
              menuLayout.setVisibility(View.VISIBLE);
            }
          }
        });
    inventoryBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            saveGame.setInventory(inventory);
            saveGameSQLiteAccessor.updateSaveGame(saveGame);
            if (isInventoryVisible) {
              isInventoryVisible = false;
              inventoryLayout.setVisibility(View.GONE);
            } else {
              isInventoryVisible = true;
              inventoryLayout.setVisibility(View.VISIBLE);
              updateNpcData();
            }
          }
        });
  }

  private void updateNpcData() {
    inventoryView.updateNpcData();
  }

  /**
   * Dispatch onResume() to fragments. Note that for better inter-operation with older versions of
   * the platform, at the point of this call the fragments attached to the activity are <em>not</em>
   * resumed. This means that in some cases the previous state may still be saved, not allowing
   * fragment transactions that modify the state. To correctly interact with fragments in their
   * proper state, you should instead override {@link #onResumeFragments()}.
   */
  @Override
  protected void onResume() {
    super.onResume();
    presenter.resumeStopWatch();
  }

  /** Dispatch onPause() to fragments. */
  @Override
  protected void onPause() {
    super.onPause();
    presenter.pauseStopWatch();
  }

  @Override
  protected void onStart() {
    super.onStart();
    presenter.start();
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
}
