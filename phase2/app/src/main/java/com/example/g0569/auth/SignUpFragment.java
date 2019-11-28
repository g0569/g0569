package com.example.g0569.auth;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.utils.Constants;

public class SignUpFragment extends Fragment implements AuthContract.View {

  private AuthContract.Presenter authPresenter;

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
    return inflater.inflate(R.layout.fragment_signup, container, false);
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
}
