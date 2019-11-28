package com.example.g0569.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.auth.AuthActivity;
import com.example.g0569.utils.Constants;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the Use
 * the {@link MainFragment#newInstance} factory method to create an instance of this fragment.
 */
public class MainFragment extends Fragment implements MainContract.View {

  private MainContract.Presenter mainPresenter;

  public MainFragment() {}

  /**
   * Use this factory method to create a new instance of this fragment
   *
   * @return A new instance of MainFragment.
   */
  static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    Button loginBtn = view.findViewById(R.id.main_login_btn);
    Button signupBtn = view.findViewById(R.id.main_signup_btn);
    loginBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getContext(), AuthActivity.class);
            intent.putExtra(AuthActivity.AUTH_TYPE, Constants.TO_LOGIN_VIEW);
            startActivity(intent);
          }
        });

    signupBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getContext(), AuthActivity.class);
            intent.putExtra(AuthActivity.AUTH_TYPE, Constants.TO_SIGNUP_VIEW);
            startActivity(intent);
          }
        });
    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  public void setMainPresenter(MainContract.Presenter mainPresenter) {
    this.mainPresenter = mainPresenter;
  }

  @Override
  public void setPresenter(MainContract.Presenter presenter) {
    this.mainPresenter = presenter;
  }
}
