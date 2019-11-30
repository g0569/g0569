package com.example.g0569.mazegame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.utils.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.g0569.utils.Constants.NONSELECTEDNPCIMAGELOOKUPTABLE;
import static com.example.g0569.utils.Constants.NPCIMAGELOOKUPTABLE;

public class InventoryGridAdapter extends BaseAdapter {

    private int selected = -1;
    private List<NPC> nonCollectedNPCs;
    private List<NPC> availableNPCs;
    private List<NPC> allNPCs = new ArrayList<>();
    private Context context;
    private NPC currentNPC;
    private InventoryFragment inventoryFragment;

    public NPC getCurrentNPC() {
        return currentNPC;
    }

    public InventoryGridAdapter(Context context, List<NPC> nonCollectedNPCs, List<NPC> availableNPCs, InventoryFragment inventoryFragment) {
        this.context = context;
        this.nonCollectedNPCs = nonCollectedNPCs;
        this.availableNPCs = availableNPCs;
        this.allNPCs.addAll(availableNPCs);
        this.allNPCs.addAll(nonCollectedNPCs);
        this.inventoryFragment = inventoryFragment;
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

    public int getSelected() {
        return selected;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        currentNPC = allNPCs.get(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    R.layout.adapter_inventory_item, null);
            GridView.LayoutParams params = (GridView.LayoutParams) convertView.getLayoutParams();
            if (params == null) {
                params = new GridView.LayoutParams(parent.getWidth() / 4,
                        parent.getHeight() / 3);
                convertView.setLayoutParams(params);
            } else {
                params.height = parent.getWidth() / 4;
                params.width = parent.getHeight() / 3;
            }
            viewHolder = new ViewHolder();
            viewHolder.npcAvatar = convertView.findViewById(R.id.inventory_item_npc_avatar);
            viewHolder.backGround = convertView.findViewById(R.id.inventory_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Drawable drawable = getDrawable(currentNPC);
        viewHolder.npcAvatar.setImageDrawable(drawable);

        viewHolder.npcAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = position;
                notifyDataSetChanged();
                currentNPC = allNPCs.get(position);
                inventoryFragment.updateNpcData();
            }
        });
        int highLightColor = (selected == position) ? Color.argb(255, 255, 235, 39) : Color.argb(0, 0, 0, 0);
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

    class ViewHolder {
        ImageView npcAvatar;
        ConstraintLayout backGround;
    }
}
