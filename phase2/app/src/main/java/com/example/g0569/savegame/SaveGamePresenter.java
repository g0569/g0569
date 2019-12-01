package com.example.g0569.savegame;

import android.os.Bundle;

import com.example.g0569.auth.model.User;
import com.example.g0569.base.BaseView;
import com.example.g0569.savegame.model.SaveGame;
import com.example.g0569.savegame.model.SaveGameSQLiteAccessor;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.Collections;
import java.util.List;

/** The Save game presenter. */
public class SaveGamePresenter implements SaveGameContract.Presenter, BaseView {

  private SaveGameContract.View mView;
  private SaveGameSQLiteAccessor saveGameSQLiteAccessor;
  private Bundle bundle;
  private User user;

  /**
   * Instantiates a new Save game presenter.
   *
   * @param mView the save game view
   * @param bundle the bundle
   */
  SaveGamePresenter(SaveGameContract.View mView, Bundle bundle) {
    this.mView = mView;
    this.mView.setPresenter(this);
    this.bundle = bundle;
    user = (User) bundle.getSerializable(Constants.BUNDLE_USER_KEY);
  }

  /**
   * Sets the save game SQLite accessor.
   *
   * @param saveGameSQLiteAccessor the save game SQLite accessor
   */
  void setSaveGameSQLiteAccessor(SaveGameSQLiteAccessor saveGameSQLiteAccessor) {
    this.saveGameSQLiteAccessor = saveGameSQLiteAccessor;
  }

  @Override
  public void start() {}

  @Override
  public void setPresenter(Object presenter) {}

  @Override
  public List<SaveGame> getSaveGames() {
    try {
      return saveGameSQLiteAccessor.getSaveGames(user.getUid());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void startNewGame(SaveGame saveGame) {
    List<NPC> allNPC = saveGameSQLiteAccessor.getAvailableNPCs();
    Collections.shuffle(allNPC);
    Inventory inventory = new Inventory(allNPC.subList(0, 2), allNPC.subList(2, allNPC.size()));
    saveGame.setInventory(inventory);
    saveGame = saveGameSQLiteAccessor.saveNewGame(saveGame);
    bundle.putSerializable(Constants.BUNDLE_INVENTORY_KEY, inventory);
    bundle.putSerializable(Constants.BUNDLE_SAVEGAME_KEY, saveGame);
    System.out.println("Starting New Game");
    mView.startGame(bundle);
  }

  @Override
  public void loadGame(SaveGame saveGame) {
    Inventory inventory = saveGame.getInventory();
    bundle.putSerializable(Constants.BUNDLE_INVENTORY_KEY, inventory);
    bundle.putSerializable(Constants.BUNDLE_SAVEGAME_KEY, saveGame);
    mView.startGame(bundle);
  }
}
