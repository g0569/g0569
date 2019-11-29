package com.example.g0569.chessgame.model;

import com.example.g0569.utils.SQLiteHelper;

public class ChessSQLiteAccesser implements ChessSQLiteAccessInterface {
    private SQLiteHelper sqLiteHelper;
    @Override
    public String getChessBoardData(String difficulty) {
        return null;
    }

    @Override
    public void setSQLiteHelper(SQLiteHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
    }
}
