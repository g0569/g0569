package com.example.g0569.savegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.g0569.R;
import com.example.g0569.savegame.model.SaveGame;

import java.util.List;

/** The Save game list adapter. */
public class SaveGameListAdapter extends BaseAdapter {

  private Context context;
  private List<SaveGame> beans;
  private int isSelected;

  /**
   * Instantiates a new Save game list adapter.
   *
   * @param context the context
   * @param beans the save games need to be shown
   */
  SaveGameListAdapter(Context context, List<SaveGame> beans) {
    this.context = context;
    this.beans = beans;
  }

  /**
   * Gets the position being selected.
   *
   * @return the position
   */
  int getIsSelected() {
    return isSelected;
  }

  /**
   * Sets save games.
   *
   * @param beans the beans
   */
  public void setSaveGames(List<SaveGame> beans) {
    this.beans = beans;
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return beans.size();
  }

  @Override
  public Object getItem(int position) {
    return beans.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @SuppressLint("InflateParams")
  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    SaveGame saveGame = beans.get(position);
    LayoutInflater inflater = LayoutInflater.from(context);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.adapter_savegame, null);
      viewHolder = new ViewHolder();
      viewHolder.checkBox = convertView.findViewById(R.id.savegame_select);
      viewHolder.progress = convertView.findViewById(R.id.savegame_progress);
      viewHolder.date = convertView.findViewById(R.id.savegame_date);
      viewHolder.saveGameItem = convertView.findViewById(R.id.savegame_item);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    viewHolder.progress.setText(
        saveGame.isNewGame() ? "Start a new game!" : String.valueOf(saveGame.getProgress()));
    viewHolder.date.setText(saveGame.getCreatedTime().toString());
    viewHolder.saveGameItem.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            isSelected = position;
            notifyDataSetChanged();
          }
        });
    viewHolder.checkBox.setChecked(position == isSelected);
    return convertView;
  }

  /** The View holder. */
  class ViewHolder {
    /** The Progress. */
    TextView progress;

    /** The Date. */
    TextView date;

    /** The Check box. */
    CheckBox checkBox;

    /** The Save game item. */
    LinearLayout saveGameItem;
  }
}
