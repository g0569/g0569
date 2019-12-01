package com.example.g0569.scoreboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.g0569.R;

public class ScoreBoardFragment extends Fragment implements ScoreBoardContract.View{

    private ScoreBoardContract.Presenter presenter;
  /** Instantiates a new Score board fragment. */
  public ScoreBoardFragment() {}

  /**
   * Use this factory method to create a new instance of this fragment
   *
   * @return A new instance of ScoreBoardFragment.
   */
  static ScoreBoardFragment newInstance() {
    return new ScoreBoardFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragmemt_scoreboard, container, false);
      TableLayout scoreBoardTable = view.findViewById(R.id.score_board);
      return super.onCreateView(inflater, container, savedInstanceState);

  }

    @Override
    public void setPresenter(ScoreBoardContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
