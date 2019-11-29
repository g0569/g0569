package com.example.g0569.savegame;

import android.os.Bundle;

import com.example.g0569.auth.model.User;
import com.example.g0569.base.BaseView;
import com.example.g0569.savegame.model.SaveGame;
import com.example.g0569.savegame.model.SaveGameSQLiteAccesser;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.Collections;
import java.util.List;

public class SaveGamePresenter implements SaveGameContract.Presenter, BaseView {

  private SaveGameContract.View mView;
  private SaveGameSQLiteAccesser saveGameSQLiteAccesser;
  private Bundle bundle;
  private User user;

  public SaveGamePresenter(SaveGameContract.View mView, Bundle bundle) {
    this.mView = mView;
    this.mView.setPresenter(this);
    this.bundle = bundle;
    user = (User) bundle.getSerializable(Constants.BUNDLE_USER_KEY);
  }

  public void setSaveGameSQLiteAccesser(SaveGameSQLiteAccesser saveGameSQLiteAccesser) {
    this.saveGameSQLiteAccesser = saveGameSQLiteAccesser;
  }

  @Override
  public void start() {}

  @Override
  public void setPresenter(Object presenter) {}

  @Override
  public List<SaveGame> getSaveGames() {
    return saveGameSQLiteAccesser.getSaveGames(user.getUid());
  }

  @Override
  public void startNewGame(SaveGame saveGame) {
    saveGame = saveGameSQLiteAccesser.saveNewGame(saveGame);
    List<NPC> allNPC = saveGameSQLiteAccesser.getAvaliableNPCs();
    Collections.shuffle(allNPC);
    Inventory inventory = new Inventory(allNPC.subList(0, 2), allNPC.subList(2, allNPC.size()));
    bundle.putSerializable(Constants.BUNDLE_INVENTORY_KEY, inventory);
    System.out.println("Starting New Game");
    mView.startGmae(bundle);
  }

  @Override
  public void loadGame(SaveGame saveGame) {}
}
