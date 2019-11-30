package com.example.g0569.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.auth.model.AuthInteractor;
import com.example.g0569.savegame.SaveGameActivity;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.SQLiteHelper;

/**
 * The activity used for authentication.
 */
public class AuthActivity extends AppCompatActivity {

    /**
     * In order to know which view (login or signup) to go to.
     */
    public static final String AUTH_TYPE = "auth_type";

    private AuthContract.Presenter authPresenter;
    private AuthContract.View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_container);
        mView =
                (AuthContract.View) getSupportFragmentManager().findFragmentById(R.id.ContentFrame);
        String authType = getIntent().getStringExtra(AuthActivity.AUTH_TYPE);
        // initialize and set up then presenter & view here
        if (mView == null) {
            if (authType.equals(Constants.TO_LOGIN_VIEW)) {
                mView = new LoginFragment();
            } else {
                mView = new SignUpFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ContentFrame, (Fragment) mView)
                    .commit();
        }
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "g0569");

        authPresenter = new AuthPresenter(mView, new AuthInteractor(), sqLiteHelper);
    }

    /**
     * Replace the existing fragment with a new one.
     *
     * @param targetFragment the target fragment
     */
    public void replaceFragment(String targetFragment) {
        Fragment newFragment;
        switch (targetFragment) {
            case Constants.TO_SIGNUP_VIEW:
                newFragment = new SignUpFragment();
                break;
            case Constants.TO_LOGIN_VIEW:
                newFragment = new LoginFragment();
                break;
            default:
                newFragment = new SignUpFragment();
                break;
        }
        getSupportFragmentManager()
                .beginTransaction().remove((Fragment) mView)
                .replace(R.id.ContentFrame, newFragment)
                .addToBackStack(null)
                .commit();
        mView = (AuthContract.View) newFragment;
        ((AuthContract.View) newFragment).setPresenter(authPresenter);
    }

    /**
     * Go to save game menu.
     *
     * @param bundle the bundle to pass in
     */
    public void toSaveGameMenu(Bundle bundle) {
        Intent intent = new Intent(this, SaveGameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
