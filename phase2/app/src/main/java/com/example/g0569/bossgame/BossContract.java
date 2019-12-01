package com.example.g0569.bossgame;

import com.example.g0569.base.BasePresenter;
import com.example.g0569.base.BaseView;

public interface BossContract {
  interface View extends BaseView<Presenter> {

    /**
     * Draws all components in the view initially then continuously every time the screen updates
     */
    void initView();

    /**
     * Sets the state of the view so that it's throwing a projectile. Allows to view to know whether
     * to move the projectile or not.
     *
     * @param b whether a projectile is being thrown or not.
     */
    void setThrown(boolean b);

    /**
     * Sets the current projectile type to show onto the view. This is dependent on what power the
     * NPC has. This is decided by the NPC.
     *
     * @param typeProjectile the power of the NPC.
     */
    void setCurrentProjectileBitmap(String typeProjectile);

    /**
     * Get the width of the screen/view
     *
     * @return the width of the screen
     */
    int getWidth();

    /**
     * Determines when the game has come to an end or not.
     *
     * @param end whether or not is is ended
     */
    void end(boolean end);

    /**
     * Sets the NPC that is being used in the view. Uses this method everytime we switch NPCS.
     *
     * @param name of the npc it is being switched to
     */
    void setCurrentNPCBitmap(String name);
  }

  interface Presenter extends BasePresenter {

    void pause();

    /**
     * Shoots the current projectile on hand. Then should tell the View to start moving the
     * projectile
     */
    void shoot();

    void showMenu();

    /** Updates the entire game */
    void update();

    /**
     * Attacks the boss and decreases it's health as well as let's view know to update the healthbar
     */
    void attackBoss();

    /**
     * Returns the unit movement of the unit.
     *
     * @return the unit movement of the enemy.
     */
    int getEnemyMovement();

    /**
     * Sets the unit movement of the enemy by letting the game know the dimension of the screen
     *
     * @param screenWidth of the View
     */
    void setEnemyMovement(float screenWidth);

    /**
     * Switches the current NPC we are using, uses a rotational style. Connects the bossGame and
     * viewGame so they are using the same npc and matches it to the correct bitmap
     */
    void switchTeam();

    /**
     * Returns what the enemy is currently resistant to
     *
     * @return the resistance of the enemy
     */
    String getResistance();

    /**
     * Returns the score of the boss level, which is dependent on how many times a projectile was
     * thrown.
     *
     * @return the number of times a projectile was thrown
     */
    int getScore();
  }
}
