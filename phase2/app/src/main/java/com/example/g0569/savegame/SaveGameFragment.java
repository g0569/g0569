package com.example.g0569.savegame;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.savegame.model.SaveGame;

import java.util.List;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the Use
 * the {@link SaveGameFragment#newInstance} factory method to create an instance of this fragment.
 */
public class SaveGameFragment extends Fragment implements SaveGameContract.View {

  private SaveGameContract.Presenter presenter;

  /** Instantiates a new Save game fragment. */
  public SaveGameFragment() {}

  /**
   * Use this factory method to create a new instance of this fragment
   *
   * @return A new instance of MainFragment.
   */
  static SaveGameFragment newInstance() {
    return new SaveGameFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_savegame, container, false);
    ListView saveGameListView = view.findViewById(R.id.savegame_listview);
    final List<SaveGame> saveGames = presenter.getSaveGames();
    final SaveGameListAdapter adapter = new SaveGameListAdapter(getActivity(), saveGames);
    saveGameListView.setAdapter(adapter);
    saveGameListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    Button loadBtn = view.findViewById(R.id.savegame_load);
    loadBtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            int selected = adapter.getIsSelected();
            if (selected == 0) {
              presenter.startNewGame(saveGames.get(0));
            } else {
              presenter.loadGame(saveGames.get(selected));
            }
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
  public void setPresenter(SaveGameContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void startGame(Bundle bundle) {
    ((SaveGameActivity) getActivity()).startMazeGame(bundle);
  }
}
