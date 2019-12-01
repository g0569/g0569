package com.example.g0569.mazegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.g0569.R;
import com.example.g0569.base.BaseView;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Inventory;
import com.example.g0569.utils.NPC;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the to
 * handle interaction events.
 */
public class InventoryFragment extends Fragment implements BaseView<MazeContract.Presenter> {

  private MazeContract.Presenter presenter;
  private Inventory inventory;
  private View view;
  private InventoryGridAdapter inventoryGridAdapter;

  /**
   * Instantiates a new Inventory fragment.
   *
   * @param inventory the inventory
   */
  InventoryFragment(Inventory inventory) {
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
            getActivity(), inventory.getCollectedItem(), inventory.getAvailableItem(), this);
    inventoryGridView.setAdapter(inventoryGridAdapter);
    view.findViewById(R.id.inventory_tochessgame_btn)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                int selectedIndex = inventoryGridAdapter.getSelected();
                if (selectedIndex == -1) {
                  Toast.makeText(getActivity(), "Please select a NPC you want", Toast.LENGTH_LONG)
                      .show();
                } else {
                  ((MazeActivity) Objects.requireNonNull(getActivity())).toChessGame(selectedIndex);
                }
              }
            });
    view.findViewById(R.id.inventory_tobossgame_btn)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                ((MazeActivity) getActivity()).toBossGame();
              }
            });
    return view;
  }

  /** Update npc data. */
  @SuppressLint("SetTextI18n")
  void updateNpcData() {
    inventoryGridAdapter.updateInventory(
        inventory.getCollectedItem(), inventory.getAvailableItem());
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
          Objects.requireNonNull(getActivity())
              .getDrawable(Constants.NPCIMAGELOOKUPTABLE.get(selectedNpc.getType())));
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
