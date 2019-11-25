package com.example.g0569.auth.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.utils.SQLiteHelper;
import com.example.g0569.utils.Utils;

public class AuthInteractor {
  public void login(
      final String email,
      final String password,
      SQLiteHelper sqLiteHelper,
      final OnAuthListener listener) {
    String passwdMD5 = Utils.encodeByMD5(password);
    SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
    UserSQLiteAccesser userSQLiteAccesser = new UserSQLiteAccesser();
    userSQLiteAccesser.setSQLiteHelper(sqLiteHelper);
    try {
      Cursor cursor =
          db.query(
              "users",
              new String[] {"uid", "username"},
              "email=? and password=?",
              new String[] {email, passwdMD5},
              null,
              null,
              null);
      if (cursor.getCount() > 0) {
        cursor.moveToNext();
        User user = new User(cursor.getInt(cursor.getColumnIndex("uid")), userSQLiteAccesser);
        listener.onLoginSuccess(user);
      } else {
        listener.onLoginError("Incorrect email or password");
      }
    } catch (Exception e) {
      listener.onLoginError("Unexpected Error");
    } finally {
      db.close();
    }
  }

  public void signUp(
      final String email,
      final String username,
      final String password,
      SQLiteHelper sqLiteHelper,
      final OnAuthListener listener) {
    SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
    String passwdMD5 = Utils.encodeByMD5(password);
    boolean f = false;
    try {
      ContentValues contentValues = new ContentValues();
      contentValues.put("username", username);
      contentValues.put("email", email);
      contentValues.put("password", password);
      contentValues.put("created_time", System.currentTimeMillis());
      db.insert("users", null, contentValues);
      listener.onSignUpSuccess();
      //      mainActivity.getHandler().sendEmptyMessage(Constants.TO_LOGIN_VIEW);
    } catch (Exception e) {
      listener.onSignUpError("Error occured");
    } finally {
      db.close();
    }
  }

  public interface OnAuthListener {

    void onLogin(String email, String password);

    void onLoginError(String errorMsg);

    void onLoginSuccess(User user);

    void onSignUp(final String email, final String username, final String password);

    void onSignUpSuccess();

    void onSignUpError(String errorMsg);
  }
}
