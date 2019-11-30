package com.example.g0569.mazegame;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the to
 * handle interaction events.
 */
public class InventoryFragment extends Fragment implements BaseView<MazeContract.Presenter> {

  private MazeContract.Presenter presenter;
  private Inventory inventory;
  private View view;
  private InventoryGridAdapter inventoryGridAdapter;

  public InventoryFragment(Inventory inventory) {
    this.inventory = inventory;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_inventory, container, false);
    GridView inventoryGridView = view.findViewById(R.id.inventory_npc_grid);
    inventoryGridAdapter =
        new InventoryGridAdapter(
            getActivity(), inventory.getNonCollectedItem(), inventory.getAvailableItem(), this);
    inventoryGridView.setAdapter(inventoryGridAdapter);

    return view;
  }

  public void updateNpcData() {
    ImageView npcAvatar = view.findViewById(R.id.inventory_npc_avatar);
    TextView npcName = view.findViewById(R.id.inventory_npc_name);
    TextView npcDamage = view.findViewById(R.id.inventory_npc_damage);
    TextView npcPower = view.findViewById(R.id.inventory_npc_power);
    NPC selectedNpc = inventoryGridAdapter.getCurrentNPC();
    try {
      npcName.setText(selectedNpc.getName());
      npcDamage.setText(String.valueOf(selectedNpc.getDamage()));
      npcPower.setText(selectedNpc.getPower());
      npcAvatar.setImageDrawable(
          getActivity().getDrawable(Constants.NPCIMAGELOOKUPTABLE.get(selectedNpc.getType())));
    } catch (NullPointerException e) {
      npcName.setText("Please select an NPC");
      npcDamage.setText("0");
      npcPower.setText("none");
    }
  }

  @Override
  public void onResume() {
    super.onResume();
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
  public void setPresenter(MazeContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
