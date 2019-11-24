package com.example.g0569.auth;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g0569.auth.model.AuthInteractor;
import com.example.g0569.auth.model.User;
import com.example.g0569.auth.model.UserSQLiteAccesser;
import com.example.g0569.utils.SQLiteHelper;
import com.example.g0569.utils.Utils;

public class AuthPresenter implements AuthContract.Presenter, AuthInteractor.OnAuthListener {

  private final AuthContract.View authView;
  private AuthInteractor authInteractor;
  private SQLiteHelper sqLiteHelper;
  private User user;

  public AuthPresenter(
      AuthContract.View authView, AuthInteractor authInteractor, SQLiteHelper sqLitehelper) {
    this.authView = authView;
    this.authInteractor = authInteractor;
    this.sqLiteHelper = sqLitehelper;
  }

  @Override
  public void start() {}

  @Override
  public void onLogin(String email, String password) {
    password = Utils.encodeByMD5(password);
    SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
    UserSQLiteAccesser userSQLiteAccesser = new UserSQLiteAccesser();
    userSQLiteAccesser.setSQLiteHelper(sqLiteHelper);
    try {
      Cursor cursor =
          db.query(
              "users",
              new String[] {"uid", "username"},
              "email=? and password=?",
              new String[] {email, password},
              null,
              null,
              null);
      if (cursor.getCount() > 0) {
        cursor.moveToNext();
        user = new User(cursor.getInt(cursor.getColumnIndex("uid")), userSQLiteAccesser);
        authView.ShowToast("Welcome Back, " + user.getUsername());
      } else {
        authView.ShowToast("Incorrect email or password");
      }
    } catch (Exception e) {
      authView.ShowToast("Unexpected Error");
    } finally {
      db.close();
    }
  }

  @Override
  public void onLoginError() {}

  @Override
  public void onLoginSuccess() {}

  @Override
  public void onSignUp() {}

  @Override
  public void onSignUpSuccess() {}

  @Override
  public void onSignUpError() {}

  @Override
  public void toSignupPage() {

  }

  @Override
  public void toLoginPage() {
    v 
  }
}
