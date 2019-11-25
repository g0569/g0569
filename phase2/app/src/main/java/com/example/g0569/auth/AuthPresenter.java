package com.example.g0569.auth;

import com.example.g0569.auth.model.AuthInteractor;
import com.example.g0569.auth.model.User;
import com.example.g0569.utils.SQLiteHelper;

/** The type Auth presenter. */
public class AuthPresenter implements AuthContract.Presenter, AuthInteractor.OnAuthListener {

  private final AuthContract.View authView;
  private AuthInteractor authInteractor;
  private SQLiteHelper sqLiteHelper;
  private User user;

  /**
   * Instantiates a new Auth presenter.
   *
   * @param authView the auth view
   * @param authInteractor the auth interactor
   * @param sqLitehelper the sq litehelper
   */
  public AuthPresenter(
      AuthContract.View authView, AuthInteractor authInteractor, SQLiteHelper sqLitehelper) {
    this.authView = authView;
    this.authInteractor = authInteractor;
    this.sqLiteHelper = sqLitehelper;
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
  public void toSignupPage() {
    authView.toSignUp();
  }

  @Override
  public void toLoginPage() {
    authView.toLogin();
  }
}
