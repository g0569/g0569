package com.example.g0569.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {
    private ArrayList<NPC> collectedItem = new ArrayList<>();;
    private ArrayList<NPC> nonCollectedItem = new ArrayList<>();;
    private ArrayList<NPC> availableItem = new ArrayList<>();

    public Inventory(ArrayList<NPC> defaultNPC) {
        availableItem.addAll(defaultNPC);
    }

    public Inventory(List<NPC> avaliableItem, List<NPC> nonCollectedItem) {
        this.availableItem.addAll(avaliableItem);
        this.nonCollectedItem.addAll(nonCollectedItem);
    }

    public ArrayList<NPC> getCollectedItem() {
        return collectedItem;
    }

    public ArrayList<NPC> getNonCollectedItem() {
        return nonCollectedItem;
    }

    /**
     * do in MazeGame when the player comes across NPC.
     * @param e
     */
    public void addCollectedItem(NPC e){
        collectedItem.add(e);
        if (nonCollectedItem.contains(e)) {
            deleteNoneCollectedItem(e);
        }
    }

    public List<NPC> getAvailableItem(){
        return availableItem;
    }

    /**
     * do when initiating the project.
     * @param e
     */
    public void addNonCollectedItem(NPC e){
        nonCollectedItem.add(e);
    }

    /**
     * do if its corresponding ChessGame wins
     * @param e
     */
    public void addAvailableItem(NPC e){
        availableItem.add(e);
        if (collectedItem.contains(e)) {
            deleteCollectedItem(e);
        }
    }

    public void deleteNoneCollectedItem(NPC e){
        nonCollectedItem.remove(e);
    }

    public void deleteCollectedItem(NPC e){
        collectedItem.remove(e);
    }

}
