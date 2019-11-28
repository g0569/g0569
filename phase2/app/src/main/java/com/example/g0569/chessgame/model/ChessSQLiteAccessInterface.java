package com.example.g0569.chessgame.model;

import com.example.g0569.base.SQLiteAccessInterface;

/** The interface Chess sq lite access interface. */
public interface ChessSQLiteAccessInterface extends SQLiteAccessInterface {
  String getChessBoardData(String difficulty);
}
