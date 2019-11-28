package com.example.g0569.utils;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<NPC> collectedItem;
    private ArrayList<NPC> nonCollectedItem;

    public Inventory(ArrayList<NPC> defaultNPC) {

        collectedItem = new ArrayList<>();

        nonCollectedItem = new ArrayList<>();

        for (int i = 0; i < defaultNPC.size(); i++){
            collectedItem.add(defaultNPC.get(i));
        }
    }

    public ArrayList getCollectedItem() {
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
