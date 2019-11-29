package com.example.g0569.auth;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.auth.model.AuthInteractor;
import com.example.g0569.utils.Constants;

public class SignUpFragment extends Fragment implements AuthContract.View {

  private AuthContract.Presenter authPresenter;

  private TextView email;
  private TextView password;
  private TextView username;

  public SignUpFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment
   *
   * @return A new instance of fragment SignUpFragment.
   */
  public static SignUpFragment newInstance() {
    SignUpFragment fragment = new SignUpFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_signup, container, false);
    this.email = view.findViewById(R.id.signup_input_email);
    this.password = view.findViewById(R.id.signup_input_password);
    this.username = view.findViewById(R.id.signup_input_name);
    Button signUpBtn = view.findViewById(R.id.signup_button);
    signUpBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            ((AuthInteractor.OnAuthListener) authPresenter)
                .onSignUp(
                    email.getText().toString(),
                    username.getText().toString(),
                    password.getText().toString());
          }
        });
    view.findViewById(R.id.signup_to_login)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                authPresenter.toLoginPage();
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

  @Override
  public void setPresenter(AuthContract.Presenter presenter) {
    this.authPresenter = presenter;
  }

  @Override
  public void ShowToast(String text) {
    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void toSignUp() {}

  @Override
  public void toLogin() {
    ((AuthActivity) getActivity()).replaceFragment(Constants.TO_LOGIN_VIEW);
  }

  @Override
  public void toSaveGameMenu(Bundle bundle) {
    ((AuthActivity) getActivity()).toSaveGameMenu(bundle);
  }
}
