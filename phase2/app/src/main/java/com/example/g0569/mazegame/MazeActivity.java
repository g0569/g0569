package com.example.g0569.mazegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.g0569.R;
import com.example.g0569.base.BaseActivity;
import com.example.g0569.mazegame.model.SaveMaze;
import com.example.g0569.savegame.model.SaveGame;
import com.example.g0569.savegame.model.SaveGameSQLiteAccessor;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.SQLiteHelper;

import java.util.Objects;

/** The type Maze activity. */
public class MazeActivity extends BaseActivity {

  /** The Inventory view. */
  private InventoryFragment inventoryView;

  private MazeView mazeView;
  private MazeContract.Presenter presenter;
  private boolean isInventoryVisible = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_mazegame);
    mazeView = findViewById(R.id.mazeview);
    bundle = getIntent().getExtras();
    assert bundle != null;

    final SaveGame saveGame = (SaveGame) bundle.getSerializable(Constants.BUNDLE_SAVEGAME_KEY);
    final Inventory inventory = (Inventory) bundle.getSerializable(Constants.BUNDLE_INVENTORY_KEY);
    final SaveMaze saveMaze = Objects.requireNonNull(saveGame).getSaveMaze();
    final SaveGameSQLiteAccessor saveGameSQLiteAccessor = new SaveGameSQLiteAccessor();
    saveGameSQLiteAccessor.setSQLiteHelper(new SQLiteHelper(this, "g0569"));

    inventoryView =
        (InventoryFragment) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);
    if (inventoryView == null) {
      inventoryView = new InventoryFragment(inventory);
    }

    ((Switch) findViewById(R.id.maze_enable_acc))
        .setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchAccelerometer(isChecked);
              }
            });

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.ContentFrame, inventoryView)
        .commit();

    presenter = new MazePresenter(mazeView, inventory, saveMaze);

    final LinearLayout menuLayout = findViewById(R.id.menu_layout);
    menuLayout.setVisibility(View.GONE);
    final FrameLayout inventoryLayout = findViewById(R.id.ContentFrame);

    inventoryLayout.setVisibility(View.GONE);
    Button menuBtn = findViewById(R.id.meny_btn);
    Button inventoryBtn = findViewById(R.id.mazegame_inventory_btn);
    findViewById(R.id.menu_load_btn)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                toLoadPage();
              }
            });
    findViewById(R.id.menu_save_btn)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                saveGame.setSaveMaze(presenter.save());
                saveGameSQLiteAccessor.updateSaveGame(saveGame);
              }
            });
    menuBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            showMenu(menuLayout);
          }
        });
    inventoryBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            saveGame.setInventory(inventory);
            saveGame.setSaveMaze(presenter.save());
            saveGameSQLiteAccessor.updateSaveGame(saveGame);
            showInventory(inventoryLayout);
          }
        });
  }

  private void showInventory(FrameLayout inventoryLayout) {
    if (isInventoryVisible) {
      isInventoryVisible = false;
      inventoryLayout.setVisibility(View.GONE);
    } else {
      isInventoryVisible = true;
      inventoryLayout.setVisibility(View.VISIBLE);
      updateNpcData();
    }
  }

  private void switchAccelerometer(boolean isChecked) {
    if (isChecked) {
      mazeView.setEnableSensor(true);
    } else {
      mazeView.setEnableSensor(false);
    }
  }

  private void updateNpcData() {
    inventoryView.updateNpcData();
  }

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
}
