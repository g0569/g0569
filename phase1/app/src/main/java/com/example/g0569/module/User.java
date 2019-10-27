package com.example.g0569.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.module.game.GameManager;

public class User {

    private int uid;
    private String email;
    private String username;
    private GameManager gameManager;

    public User(GameManager gameManager, int uid) {
        this.gameManager = gameManager;
        SQLiteDatabase db = gameManager.getSqLitehelper().getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"username"}, "uid=?", new String[]{String.valueOf(uid)}, null, null, null);
        this.username = cursor.getString(cursor.getColumnIndex("username"));
        db.close();
    }

    protected void loadSave() {
        // TODO
    }

    protected void save() {
        // TODO
    }

    public String getUsername() {
        return username;
    }
}
