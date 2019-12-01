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
    Score currentScore = presenter.getCurrentScore();
    ((TextView) view.findViewById(R.id.user_score)).setText(currentScore.getScore());
    ((TextView) view.findViewById(R.id.user_time)).setText(currentScore.getCreatedTime());
    ((TextView) view.findViewById(R.id.user_rank)).setText(currentScore.getRank());
    ((TextView) view.findViewById(R.id.user_username)).setText(currentScore.getUsername());

    List<Score> scoreList = presenter.getScoreList();

    for (Score score : scoreList) {
      TableRow tr =
          (TableRow) inflater.inflate(R.layout.adapter_scoreboard, scoreBoardTable, false);
      ((TextView) tr.findViewById(R.id.sb_score)).setText(score.getScore());
      ((TextView) tr.findViewById(R.id.sb_rank)).setText(score.getRank());
      ((TextView) tr.findViewById(R.id.sb_time)).setText(score.getCreatedTime());
      ((TextView) tr.findViewById(R.id.sb_username)).setText(score.getUsername());
      scoreBoardTable.addView(tr);
    }

    view.findViewById(R.id.sb_btn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ScoreBoardActivity) getActivity()).toLoadPage();
        }
    });

    return view;
  }

  @Override
  public void setPresenter(ScoreBoardContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
