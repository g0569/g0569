package com.example.g0569.savegame;

import android.os.Bundle;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.savegame.model.SaveGame;

import java.util.List;

public interface SaveGameContract {
  interface View extends BaseView<Presenter> {
    void startGmae(Bundle bundle);
  }

  interface Presenter extends BasePresenter {
    List<SaveGame> getSaveGames();

    void startNewGame(SaveGame saveGame);

    void loadGame(SaveGame saveGame);
  }
}
