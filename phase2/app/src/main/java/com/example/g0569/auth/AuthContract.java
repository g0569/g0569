package com.example.g0569.auth;

import android.os.Bundle;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

/** The interface for the authentication contract. */
public interface AuthContract {
  /** The View interface. */
  interface View extends BaseView<Presenter> {
    /**
     * Show a toast on the screem with the given text.
     *
     * @param text the text to be shown
     */
    void ShowToast(String text);

    /** To the sign up page. */
    void toSignUp();

    /** To the login page. */
    void toLogin();

    /**
     * Go to the save game menu.
     *
     * @param bundle the bundle to pass in to game menu activity
     */
    void toSaveGameMenu(Bundle bundle);
  }

  /** The Presenter interface. */
  interface Presenter extends BasePresenter {
    /** To the log in page. */
    void toLoginPage();

    /** To the sign up page. */
    void toSignUpPage();
  }
}
