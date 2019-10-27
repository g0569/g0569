package com.example.g0569.module.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.g0569.MainActivity;
import com.example.g0569.module.User;
import com.example.g0569.module.utils.SQLiteHelper;
import com.example.g0569.module.utils.Utils;
import com.example.g0569.utils.Constants;

public class GameManager {

    private MainActivity mainActivity;
    private Game currentGame;
    private User user;
    private SQLiteHelper sqLitehelper;


    public GameManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.onStart();
    }

    public void onStart() {
        sqLitehelper = new SQLiteHelper(mainActivity, "users");
        mainActivity.getHandler().sendEmptyMessage(Constants.TO_MENU_VIEW);
    }

    public void changeGame(int nextGame) {
//        call this.save()
        // TODO
    }

    public boolean login(String email, String password) {
        password = Utils.encodeByMD5(password);
        SQLiteDatabase db = sqLitehelper.getReadableDatabase();
        boolean f = false;
        try {
            Cursor cursor = db.query("users", new String[]{"uid", "username"}, "email=?&password=?", new String[]{email, password}, null, null, null);
            if (cursor.getCount() > 0) {
                user = new User(this, cursor.getInt(cursor.getColumnIndex("uid")));
                Toast.makeText(mainActivity, "Welcome Back, " + user.getUsername(), Toast.LENGTH_SHORT).show();
                f = true;
            } else {
                Toast.makeText(mainActivity, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(mainActivity, "Unexpected Error", Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
        return f;
    }

    public boolean signUp(String email, String username, String password) {
        SQLiteDatabase db = sqLitehelper.getWritableDatabase();
        password = Utils.encodeByMD5(password);
        boolean f =false;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("email", email);
            contentValues.put("password", password);
            db.insert("users", null, contentValues);
            Toast.makeText(mainActivity, "Sign up Successfully", Toast.LENGTH_SHORT).show();
            f = true;
            mainActivity.getHandler().sendEmptyMessage(Constants.TO_LOGIN_VIEW);
        } catch (Exception e) {
            Toast.makeText(mainActivity, "Error occured", Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
        return f;
    }

    public void save() {
//        call currentGame.save() and store to SQLite using user.save(arg)
    }

    public boolean isLogin() {
        return user != null;
    }

    public SQLiteHelper getSqLitehelper() {
        return sqLitehelper;
    }
}
