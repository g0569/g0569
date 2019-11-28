package com.example.g0569.auth;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

public interface AuthContract {
  interface View extends BaseView<Presenter> {
    void ShowToast(String text);

    void toSignUp();

    void toLogin();
  }

  interface Presenter extends BasePresenter {
    void toLoginPage();

    void toSignupPage();
  }
}
