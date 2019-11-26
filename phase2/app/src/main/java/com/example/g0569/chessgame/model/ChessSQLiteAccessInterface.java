package com.example.g0569.chessgame.model;

import com.example.g0569.base.SQLiteAccessInterface;
import com.example.g0569.utils.Coordinate;

/**
 * The interface Chess sq lite access interface.
 */
public interface ChessSQLiteAccessInterface extends SQLiteAccessInterface {
    String getChessBoardData();
}
