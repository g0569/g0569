package com.example.g0569.savegame;

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

public class SaveGameListAdapter extends BaseAdapter {

  private Context context;
  private List<SaveGame> beans;
  private int isSelected;

  public SaveGameListAdapter(Context context, List<SaveGame> beans) {
    this.context = context;
    this.beans = beans;
  }

  public int getIsSelected() {
    return isSelected;
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

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder = null;
    SaveGame saveGame = beans.get(position);
    LayoutInflater inflater = LayoutInflater.from(context);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.adapter_savegame, null);
      viewHolder = new ViewHolder();
      viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.savegame_select);
      viewHolder.progress = (TextView) convertView.findViewById(R.id.savegame_progress);
      viewHolder.date = (TextView) convertView.findViewById(R.id.savegame_date);
      viewHolder.savegameItem = (LinearLayout) convertView.findViewById(R.id.savegame_item);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    viewHolder.progress.setText(
        saveGame.isNewGame() ? "Start a new game!" : String.valueOf(saveGame.getProgress()));
    viewHolder.date.setText(saveGame.getCreatedTime().toString());
    viewHolder.savegameItem.setOnClickListener(
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

  class ViewHolder {
    TextView progress;
    TextView date;
    CheckBox checkBox;
    LinearLayout savegameItem;
  }
}
