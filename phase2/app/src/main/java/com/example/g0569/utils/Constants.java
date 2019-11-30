package com.example.g0569.utils;

import com.example.g0569.R;

import java.util.HashMap;

public interface Constants {
  int TO_MENU_VIEW = 0;
  String TO_LOGIN_VIEW = "1";
  int TO_END_VIEW = 2;
  String TO_SIGNUP_VIEW = "3";
  int TO_BOSS_VIEW = 4;
  int TO_CHESS_VIEW = 5;
  int TO_MAZE_VIEW = 6;
  int END_GAME = 9;
  int TO_STATISTIC_VIEW = 7;

  int MazeGame = 10;
  int ChessGame = 11;
  int BossGame = 12;

  int AT_LEFT_BOUNDARY = 1;
  int AT_RIGHT_BOUNDARY = -1;
  int AT_TOP_BOUNDARY = 1;
  int AT_BOTTOM_BOUNDARY = -1;

  int GRID_WIDTH = 20;
  int GRID_HEIGHT = 20;

  int TO_DEMO_VIEW = 999;

  int NPC_NUM = 6;

  String STAR_TYPE = "type1";
  String TRIANGLE_TYPE = "type2";
  String CIRCLE_TYPE = "type3";
  String DIAMOND_TYPE = "type4";
  String HEART_TYPE = "type5";
  String SQUARE_TYPE = "type6";

  String BUNDLE_USER_KEY = "BUNDLE_USER_KEY";
  String BUNDLE_INVENTORY_KEY = "BUNDLE_INVENTORY_KEY";
  String BUNDLE_SELECTEDNPC_KEY = "BUNDLE_SELECTEDNPC_KEY";

//  HashMap<String, Integer> NPCIMAGELOOKUPTABLE = new HashMap<String, Integer>(){{
//    NPCIMAGELOOKUPTABLE.put("type1", R.drawable.npc_l1);
//    NPCIMAGELOOKUPTABLE.put("type2", R.drawable.npc_l2);
//    NPCIMAGELOOKUPTABLE.put("type3", R.drawable.npc_l3);
//    NPCIMAGELOOKUPTABLE.put("type4", R.drawable.npc_l4);
//    NPCIMAGELOOKUPTABLE.put("type5", R.drawable.npc_l5);
//    NPCIMAGELOOKUPTABLE.put("type6", R.drawable.npc_l6);
//  }};
//
//  HashMap<String, Integer> NONSELECTEDNPCIMAGELOOKUPTABLE = new HashMap<String, Integer>(){{
//    NONSELECTEDNPCIMAGELOOKUPTABLE.put("type1", R.drawable.npc_l1_nonselected);
//    NONSELECTEDNPCIMAGELOOKUPTABLE.put("type2", R.drawable.npc_l2_nonselected);
//    NONSELECTEDNPCIMAGELOOKUPTABLE.put("type3", R.drawable.npc_l3_nonselected);
//    NONSELECTEDNPCIMAGELOOKUPTABLE.put("type4", R.drawable.npc_l4_nonselected);
//    NONSELECTEDNPCIMAGELOOKUPTABLE.put("type5", R.drawable.npc_l5_nonselected);
//    NONSELECTEDNPCIMAGELOOKUPTABLE.put("type6", R.drawable.npc_l6_nonselected);
//  }};

}
