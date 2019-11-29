package com.example.g0569.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {
    private ArrayList<NPC> collectedItem = new ArrayList<>();;
    private ArrayList<NPC> nonCollectedItem = new ArrayList<>();;

    public Inventory(ArrayList<NPC> defaultNPC) {
        for (int i = 0; i < defaultNPC.size(); i++){
            collectedItem.add(defaultNPC.get(i));
        }
    }

    public Inventory(List<NPC> collectedItem, List<NPC> nonCollectedItem) {
        this.collectedItem.addAll(collectedItem);
        this.nonCollectedItem.addAll(nonCollectedItem);
    }

    public ArrayList<NPC> getCollectedItem() {
        return collectedItem;
    }

    public ArrayList<NPC> getNonCollectedItem() {
        return nonCollectedItem;
    }

    public void addCollectedItem(NPC e){
        collectedItem.add(e);
        if (nonCollectedItem.contains(e)) {
            deleteNoneCollectedItem(e);
        }
    }

    public void addNonCollectedItem(NPC e){
        nonCollectedItem.add(e);
    }

    public void deleteNoneCollectedItem(NPC e){
        nonCollectedItem.remove(e);
    }

    public void deleteCollectedItem(NPC e){
        collectedItem.remove(e);
    }
}
