package com.example.g0569.auth.model;

public class AuthInteractor {
    public interface OnAuthListener {

        void onLogin(String email, String password);

        void onLoginError();

        void onLoginSuccess();

        void onSignUp();

        void onSignUpSuccess();

        void onSignUpError();
    }

    public void login(final String username, final String password, final OnAuthListener listener) {

    }
}
