package com.example.g0569.mazegame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.g0569.R;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.List;

import static com.example.g0569.utils.Constants.NONSELECTEDNPCIMAGELOOKUPTABLE;
import static com.example.g0569.utils.Constants.NPCIMAGELOOKUPTABLE;

/** The type Inventory grid adapter. */
public class InventoryGridAdapter extends BaseAdapter {

  private int selected = -1;
  private List<NPC> availableNPCs;
  private List<NPC> allNPCs = new ArrayList<>();
  private Context context;
  private NPC currentNPC;
  private InventoryFragment inventoryFragment;

  /**
   * Instantiates a new Inventory grid adapter.
   *
   * @param context the context
   * @param nonCollectedNPCs the non collected NPCs
   * @param availableNPCs the available NPCs
   * @param inventoryFragment the inventory fragment
   */
  InventoryGridAdapter(
      Context context,
      List<NPC> nonCollectedNPCs,
      List<NPC> availableNPCs,
      InventoryFragment inventoryFragment) {
    this.context = context;
    this.availableNPCs = availableNPCs;
    this.allNPCs.addAll(availableNPCs);
    this.allNPCs.addAll(nonCollectedNPCs);
    this.inventoryFragment = inventoryFragment;
  }

  /**
   * Gets current npc.
   *
   * @return the current npc
   */
  NPC getCurrentNPC() {
    try {
      currentNPC = allNPCs.get(selected);
    } catch (ArrayIndexOutOfBoundsException e) {
      currentNPC = null;
    }
    return currentNPC;
  }

  @Override
  public int getCount() {
    return allNPCs.size();
  }

  @Override
  public Object getItem(int position) {
    return allNPCs.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  /**
   * Gets selected.
   *
   * @return the selected
   */
  int getSelected() {
    return selected;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    currentNPC = allNPCs.get(position);

    if (convertView == null) {
      convertView = layoutInflater.inflate(R.layout.adapter_inventory_item, null);
      viewHolder = new ViewHolder();
      viewHolder.npcAvatar = convertView.findViewById(R.id.inventory_item_npc_avatar);
      viewHolder.backGround = convertView.findViewById(R.id.inventory_item);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    Drawable drawable = getDrawable(currentNPC);
    viewHolder.npcAvatar.setImageDrawable(drawable);

    viewHolder.npcAvatar.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            selected = position;
            notifyDataSetChanged();
            currentNPC = allNPCs.get(position);
            inventoryFragment.updateNpcData();
          }
        });
    int highLightColor =
        (selected == position) ? Color.argb(255, 255, 235, 39) : Color.argb(0, 0, 0, 0);
    viewHolder.backGround.setBackgroundColor(highLightColor);

    return convertView;
  }

  private Drawable getDrawable(NPC currentNPC) {

    if (availableNPCs.contains(currentNPC)) {
      return context.getDrawable(NPCIMAGELOOKUPTABLE.get(currentNPC.getType()));
    } else {
      return context.getDrawable(NONSELECTEDNPCIMAGELOOKUPTABLE.get(currentNPC.getType()));
    }
  }

  /**
   * Update inventory.
   *
   * @param nonCollectedNPCs the non collected NPCs
   * @param availableNPCs the available NPCs
   */
  void updateInventory(List<NPC> nonCollectedNPCs, List<NPC> availableNPCs) {
    this.availableNPCs = availableNPCs;
    this.allNPCs = new ArrayList<>();
    this.allNPCs.addAll(availableNPCs);
    this.allNPCs.addAll(nonCollectedNPCs);
    notifyDataSetChanged();
  }

  /** The type View holder. */
  class ViewHolder {
    /** The Npc avatar. */
    ImageView npcAvatar;

    /** The Back ground. */
    ConstraintLayout backGround;
  }
}
