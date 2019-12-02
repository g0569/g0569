package com.example.g0569.chessgame.model;

import android.util.Pair;

import com.example.g0569.utils.Coordinate;

import java.util.HashMap;

/** The interface Chess game coordinate data maps. */
public interface ChessGameCoordinateDataMap {

  /**
   * The constant DRAW_CHESS_GRID_LOOKUP_TABLE. This HashMap stores the offset values for drawing.
   */
  HashMap<Integer, Pair> DRAW_CHESS_GRID_LOOKUP_TABLE =
      new HashMap<Integer, Pair>() {
        {
          Pair pair1 = Pair.create(0.32f, 0.46f);
          Pair pair2 = Pair.create(0.42f, 0.46f);
          Pair pair3 = Pair.create(0.52f, 0.46f);
          Pair pair4 = Pair.create(0.62f, 0.46f);
          Pair pair5 = Pair.create(0.3f, 0.61f);
          Pair pair6 = Pair.create(0.41f, 0.61f);
          Pair pair7 = Pair.create(0.525f, 0.61f);
          Pair pair8 = Pair.create(0.64f, 0.61f);
          Pair pair9 = Pair.create(0.27f, 0.8f);
          Pair pair10 = Pair.create(0.395f, 0.8f);
          Pair pair11 = Pair.create(0.53f, 0.8f);
          Pair pair12 = Pair.create(0.67f, 0.8f);
          Pair pair13 = Pair.create(0.0f, 0.0f);
          Pair pair14 = Pair.create(0.5f, 0.0f);
          Pair pair15 = Pair.create(0.0f, 0.33f);
          Pair pair16 = Pair.create(0.5f, 0.33f);
          Pair pair17 = Pair.create(0.0f, 0.66f);
          Pair pair18 = Pair.create(0.5f, 0.66f);
          put(101, pair1);
          put(102, pair2);
          put(103, pair3);
          put(104, pair4);
          put(201, pair5);
          put(202, pair6);
          put(203, pair7);
          put(204, pair8);
          put(301, pair9);
          put(302, pair10);
          put(303, pair11);
          put(304, pair12);
          put(1010, pair13);
          put(1020, pair14);
          put(2010, pair15);
          put(2020, pair16);
          put(3010, pair17);
          put(3020, pair18);
        }
      };

  HashMap<Integer, Coordinate> FIND_CHESS_GRID_LOOKUP_TABLE =
      new HashMap<Integer, Coordinate>() {
        {
          Coordinate BOARD_ROW_1_COL_1 = Coordinate.create(1, 1);

          Coordinate BOARD_ROW_2_COL_1 = Coordinate.create(2, 1);

          Coordinate BOARD_ROW_3_COL_1 = Coordinate.create(3, 1);

          Coordinate BOARD_ROW_1_COL_2 = Coordinate.create(1, 2);

          Coordinate BOARD_ROW_2_COL_2 = Coordinate.create(2, 2);

          Coordinate BOARD_ROW_3_COL_2 = Coordinate.create(3, 2);

          Coordinate INVENTORY_ROW_1_COL_1 = Coordinate.create(10, 10);

          Coordinate INVENTORY_ROW_2_COL_1 = Coordinate.create(20, 10);

          Coordinate INVENTORY_ROW_3_COL_1 = Coordinate.create(30, 10);

          Coordinate INVENTORY_ROW_1_COL_2 = Coordinate.create(10, 20);

          Coordinate INVENTORY_ROW_2_COL_2 = Coordinate.create(20, 20);

          Coordinate INVENTORY_ROW_3_COL_2 = Coordinate.create(30, 20);

          put(1, BOARD_ROW_1_COL_1);
          put(2, BOARD_ROW_2_COL_1);
          put(3, BOARD_ROW_3_COL_1);
          put(4, BOARD_ROW_1_COL_2);
          put(5, BOARD_ROW_2_COL_2);
          put(6, BOARD_ROW_3_COL_2);
          put(7, INVENTORY_ROW_1_COL_1);
          put(8, INVENTORY_ROW_2_COL_1);
          put(9, INVENTORY_ROW_3_COL_1);
          put(10, INVENTORY_ROW_1_COL_2);
          put(11, INVENTORY_ROW_2_COL_2);
          put(12, INVENTORY_ROW_3_COL_2);
        }
      };
}
