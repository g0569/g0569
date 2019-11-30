package com.example.g0569.auth.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.utils.SQLiteHelper;
import com.example.g0569.utils.Utils;

/** The type Auth interactor. */
public class AuthInteractor {

  /**
   * Login a user with email & password
   *
   * @param email the email of the user
   * @param password the password of the user
   * @param sqLiteHelper the SQLite helper
   * @param listener the listener for the auth process
   */
  public void login(
      final String email,
      final String password,
      SQLiteHelper sqLiteHelper,
      final OnAuthListener listener) {
    String pwdMD5 = Utils.encodeByMD5(password);
    SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
    UserSQLiteAccessor userSQLiteAccessor = new UserSQLiteAccessor();
    userSQLiteAccessor.setSQLiteHelper(sqLiteHelper);
    try {
      Cursor cursor =
          db.query(
              "users",
              new String[] {"uid", "username"},
              "email=? and password=?",
              new String[] {email, pwdMD5},
              null,
              null,
              null);
      if (cursor.getCount() > 0) {
        cursor.moveToNext();
        int uid = cursor.getInt(cursor.getColumnIndex("uid"));
        User user = new User(uid, userSQLiteAccessor.getUserName(uid));
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

  /**
   * Sign up a user with given information.
   *
   * @param email the email of the user
   * @param username the username of the user
   * @param password the password of the user
   * @param sqLiteHelper the SQLite helper
   * @param listener the listener for the auth process
   */
  public void signUp(
      final String email,
      final String username,
      final String password,
      SQLiteHelper sqLiteHelper,
      final OnAuthListener listener) {
    SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
    String pwdMD5 = Utils.encodeByMD5(password);
    try {
      ContentValues contentValues = new ContentValues();
      contentValues.put("username", username);
      contentValues.put("email", email);
      contentValues.put("password", pwdMD5);
      contentValues.put("created_time", System.currentTimeMillis());
      db.insert("users", null, contentValues);
      listener.onSignUpSuccess();
    } catch (Exception e) {
      listener.onSignUpError("Error occurred");
    } finally {
      db.close();
    }
  }

  /** The interface for the OnAuth listener. */
  public interface OnAuthListener {

    /**
     * the login callback
     *
     * @param email the email of the user
     * @param password the password of the user
     */
    void onLogin(String email, String password);

    /**
     * the login error callback
     *
     * @param errorMsg the error msg
     */
    void onLoginError(String errorMsg);

    /**
     * the login success callback.
     *
     * @param user the user
     */
    void onLoginSuccess(User user);

    /**
     * the sign up callback.
     *
     * @param email the email
     * @param username the username
     * @param password the password
     */
    void onSignUp(final String email, final String username, final String password);

    /** the sign up success callback. */
    void onSignUpSuccess();

    /**
     * the sign up error callback.
     *
     * @param errorMsg the error msg
     */
    void onSignUpError(String errorMsg);
  }
}
