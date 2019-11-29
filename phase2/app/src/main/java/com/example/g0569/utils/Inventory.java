package com.example.g0569.utils;

import java.util.ArrayList;

/**
 * TODO change the comment!
 */
public class Inventory {
    /**
     * NPC collected from MazeGame but processing to ChessGame(can't be used).
     */
    private ArrayList<NPC> collectedItem;
    /**
     * NPC haven\t been collected from MazeGame(can't get into corresponding ChessGame).
     */
    private ArrayList<NPC> nonCollectedItem;
    /**
     * NPC gained from ChessGame.
     */
    private ArrayList<NPC> availableItem;

    public Inventory(ArrayList<NPC> defaultNPC) {

        collectedItem = new ArrayList<>();

        nonCollectedItem = new ArrayList<>();

        availableItem = new ArrayList<>();

        for (int i = 0; i < defaultNPC.size(); i++){
            availableItem.add(defaultNPC.get(i));
        }
    }

    public ArrayList<NPC> getAvailableItem() {
        return availableItem;
    }

    public ArrayList getCollectedItem() {
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
