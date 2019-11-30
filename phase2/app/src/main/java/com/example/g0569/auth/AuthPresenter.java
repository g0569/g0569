package com.example.g0569.auth;

import android.os.Bundle;

import com.example.g0569.auth.model.AuthInteractor;
import com.example.g0569.auth.model.User;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.SQLiteHelper;

/** The authentication presenter. */
public class AuthPresenter implements AuthContract.Presenter, AuthInteractor.OnAuthListener {

  private final AuthContract.View authView;
  private AuthInteractor authInteractor;
  private SQLiteHelper sqLiteHelper;
  private User user;

  /**
   * Instantiates a new authentication presenter.
   *
   * @param authView the authentication view
   * @param authInteractor the authentication interactor
   * @param sqLiteHelper the SQLite helper
   */
  AuthPresenter(
      AuthContract.View authView, AuthInteractor authInteractor, SQLiteHelper sqLiteHelper) {
    this.authView = authView;
    this.authInteractor = authInteractor;
    this.sqLiteHelper = sqLiteHelper;
    authView.setPresenter(this);
  }

  @Override
  public void start() {}

  @Override
  public void onLogin(String email, String password) {
    authInteractor.login(email, password, sqLiteHelper, this);
  }

  @Override
  public void onLoginError(String errorMsg) {
    authView.ShowToast(errorMsg);
  }

  @Override
  public void onLoginSuccess(User user) {
    this.user = user;
    authView.ShowToast("Welcome Back, " + user.getUsername());
    Bundle bundle = new Bundle();
    bundle.putSerializable(Constants.BUNDLE_USER_KEY, user);
    authView.toSaveGameMenu(bundle);
  }

  @Override
  public void onSignUp(final String email, final String username, final String password) {
    authInteractor.signUp(email, username, password, sqLiteHelper, this);
  }

  @Override
  public void onSignUpSuccess() {
    authView.ShowToast("Sign up Successfully!");
    this.toLoginPage();
  }

  @Override
  public void onSignUpError(String errorMsg) {
    authView.ShowToast(errorMsg);
  }

  @Override
  public void toSignUpPage() {
    authView.toSignUp();
  }

  @Override
  public void toLoginPage() {
    authView.toLogin();
  }
}
