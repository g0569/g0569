package com.example.g0569;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.g0569.module.game.GameManager;
import com.example.g0569.utils.Constants;
import com.example.g0569.view.BossView;
import com.example.g0569.view.ChessView;
import com.example.g0569.view.MainMenuView;

public class MainActivity extends Activity implements View.OnClickListener {

    private MainMenuView mainMenuView;
    private GameManager gameManager;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Constants.TO_MENU_VIEW) {
                toMenuView();
            } else if (msg.what == Constants.TO_LOGIN_VIEW) {
                toLoginView();
            } else if (msg.what == Constants.TO_SIGNUP_VIEW) {
                toSignUpView();
            }
        }
    };

    private void toSignUpView() {
        mainMenuView = null;
        setContentView(R.layout.page_signup);
        ((Button) findViewById(R.id.signup_button)).setOnClickListener(this);
        ((TextView) findViewById(R.id.to_login)).setOnClickListener(this);
    }

    private void toMenuView() {
        if (mainMenuView == null) {
            mainMenuView = new MainMenuView(this);
        }
        setContentView(mainMenuView);
    }

    private void toLoginView() {
        mainMenuView = null;
        setContentView(R.layout.page_login);
        ((Button) findViewById(R.id.login_button)).setOnClickListener(this);
        ((TextView) findViewById(R.id.to_signup)).setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        gameManager = new GameManager(this);
setContentView(new ChessView(this));
    }


    public Handler getHandler() {
        return handler;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.login_button):
                EditText email = (EditText) findViewById(R.id.email);
                EditText password = (EditText) findViewById(R.id.passsword);
                gameManager.login(email.getText().toString(), password.getText().toString());
                break;
            case (R.id.to_signup):
                handler.sendEmptyMessage(Constants.TO_SIGNUP_VIEW);
                break;
            case (R.id.to_login):
                handler.sendEmptyMessage(Constants.TO_LOGIN_VIEW);
                break;
            case (R.id.signup_button):
                gameManager.signUp(((EditText) findViewById(R.id.signup_input_email)).getText().toString(),
                        ((EditText) findViewById(R.id.signup_input_name)).getText().toString(),
                        ((EditText) findViewById(R.id.signup_input_password)).getText().toString());

        }
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
