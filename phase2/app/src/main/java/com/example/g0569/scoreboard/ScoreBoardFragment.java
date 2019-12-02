package com.example.g0569.scoreboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.scoreboard.model.Score;

import java.util.List;

public class ScoreBoardFragment extends Fragment implements ScoreBoardContract.View {
  private TableLayout scoreBoardTable;
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
    scoreBoardTable = view.findViewById(R.id.score_board_tableLayout);
    Score currentScore = presenter.getCurrentScore();
    ((TextView) scoreBoardTable.findViewById(R.id.user_score))
        .setText(String.valueOf(currentScore.getScore()));
    ((TextView) scoreBoardTable.findViewById(R.id.user_time))
        .setText(currentScore.getCreatedTime());
    ((TextView) scoreBoardTable.findViewById(R.id.user_rank))
        .setText(String.valueOf(currentScore.getRank()));
    ((TextView) scoreBoardTable.findViewById(R.id.user_username))
        .setText(currentScore.getUsername());

    List<Score> scoreList = presenter.getScoreList();

    for (Score score : scoreList) {
      TableRow row = new TableRow(getActivity());
      row.setLayoutParams(
          new TableLayout.LayoutParams(
              TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
      View tr = inflater.inflate(R.layout.adapter_scoreboard, row, true);
      ((TextView) tr.findViewById(R.id.sb_score)).setText(String.valueOf(score.getScore()));
      ((TextView) tr.findViewById(R.id.sb_rank)).setText(String.valueOf(score.getRank()));
      ((TextView) tr.findViewById(R.id.sb_time)).setText(score.getCreatedTime());
      ((TextView) tr.findViewById(R.id.sb_username)).setText(score.getUsername());
      scoreBoardTable.addView(row);
    }

    view.findViewById(R.id.sb_btn)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                ((ScoreBoardActivity) getActivity()).toLoadPage();
              }
            });

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void setPresenter(ScoreBoardContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
