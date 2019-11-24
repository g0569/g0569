package com.example.g0569.auth;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.auth.model.AuthInteractor;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.SQLiteHelper;

public class AuthActivity extends AppCompatActivity {

  public static final String AUTH_TYPE = "auth_type"; // used in intent
  private AuthContract.Presenter authPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

      setContentView(R.layout.activity_container);
      AuthContract.View mView =
              (AuthContract.View) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);
      String authType = getIntent().getStringExtra(AuthActivity.AUTH_TYPE);
      // initialize and set up then presenter & view here
    if (mView == null) {
        if (authType.equals(Constants.TO_LOGIN_VIEW)){
            mView = new LoginFragment();
        } else{
            mView =  new SignupFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ContentFrame, (Fragment) mView)
                .commit();
    }
      SQLiteHelper sqLitehelper = new SQLiteHelper(this, "users");

      authPresenter = new AuthPresenter(mView, new AuthInteractor(), sqLitehelper);
  }
}
