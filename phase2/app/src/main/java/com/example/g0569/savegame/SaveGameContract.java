package com.example.g0569.savegame;

import android.os.Bundle;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;
import com.example.g0569.savegame.model.SaveGame;

import java.util.List;

/** The Save game contract interface. */
public interface SaveGameContract {
  /** The View interface. */
interface View extends BaseView<Presenter> {
    /**
     * Start a new game.
     *
     * @param bundle the bundle
     */
void startGame(Bundle bundle);
  }

  /** The interface Presenter. */
interface Presenter extends BasePresenter {
    /**
     * Gets all save games.
     *
     * @return the save games
     */
List<SaveGame> getSaveGames();

    /**
     * Start a new game with the given save game.
     *
     * @param saveGame the save game
     */
void startNewGame(SaveGame saveGame);

    /**
     * Load a given save game.
     *
     * @param saveGame the save game
     */
void loadGame(SaveGame saveGame);
  }
}
