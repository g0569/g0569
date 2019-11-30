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
        HashMap<String, Integer> NPCIMAGELOOKUPTABLE = new HashMap<String, Integer>();
        NPCIMAGELOOKUPTABLE.put("type1", R.drawable.npc_l1);
        NPCIMAGELOOKUPTABLE.put("type2", R.drawable.npc_l2);
        NPCIMAGELOOKUPTABLE.put("type3", R.drawable.npc_l3);
        NPCIMAGELOOKUPTABLE.put("type4", R.drawable.npc_l4);
        NPCIMAGELOOKUPTABLE.put("type5", R.drawable.npc_l5);
        NPCIMAGELOOKUPTABLE.put("type6", R.drawable.npc_l6);

        HashMap<String, Integer> NONSELECTEDNPCIMAGELOOKUPTABLE = new HashMap<String, Integer>();
        NONSELECTEDNPCIMAGELOOKUPTABLE.put("type1", R.drawable.npc_l1_nonselected);
        NONSELECTEDNPCIMAGELOOKUPTABLE.put("type2", R.drawable.npc_l2_nonselected);
        NONSELECTEDNPCIMAGELOOKUPTABLE.put("type3", R.drawable.npc_l3_nonselected);
        NONSELECTEDNPCIMAGELOOKUPTABLE.put("type4", R.drawable.npc_l4_nonselected);
        NONSELECTEDNPCIMAGELOOKUPTABLE.put("type5", R.drawable.npc_l5_nonselected);
        NONSELECTEDNPCIMAGELOOKUPTABLE.put("type6", R.drawable.npc_l6_nonselected);

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
