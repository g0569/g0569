package com.example.g0569.bossgame.model;

import com.example.g0569.base.model.BaseGame;
import com.example.g0569.utils.NPC;
import java.util.List;

public class BossGame extends BaseGame {
  private Enemy enemy;
  private boolean paused;
  private List bossTeam;
  private int currentTeam;
  private NPC currentNPC;
  private int score;

  public BossGame() {
    super();
    paused = false;
  }

  /**
   * Instantiates everything needed for bossGame to run smoothly Makes a healthbar, enemy, and
   * player Might need to initialize current projectile upon creation
   */
  public void onStart() {

    //    BossPlayer bossPlayer = new BossPlayer();
    enemy = new Enemy();
    //    HealthBar healthBar = new HealthBar(enemy);
  }

  /**
   * Returns what the enemy is currently resistant against
   *
   * @return the element the enemy is resisting at the moment
   */
  public String getEnemyResistance() {
    return enemy.getResist();
  }

  /** Pauses the game or unpauses the game depending on the state it is in */
  @Override
  public void pause() {
    paused = !paused;
  }

  /** Loads teh game after it has been saved */
  @Override
  public void load() {}

  /**
   * Attacks the boss and changes it's health accordingly Might want it to return something later so
   * that healthbar is not needed later
   */
  public void attackBoss() {
    enemy.attacked(currentNPC.getDamage(), currentNPC.getPower());
  }

  /**
   * Switches the npc currently being used to attack the boss in the game Since it is rotational, if
   * it gets to the size then we reset to the first npc. then restart iterating through that.
   */
  public void loadNextNPC() {
    if (bossTeam.size() != 0) {
      currentTeam++;
      if (currentTeam >= bossTeam.size()) {
        currentTeam = 0;
      }
      currentNPC = (NPC) bossTeam.get(currentTeam);
    }
  }

  /**
   * gets the current npc that the game is using.
   *
   * @return the npc that is being used.
   */
  public NPC getCurrentNPC() {
    return currentNPC;
  }

  /**
   * Allows the movement of the enemy to be set since it is dependent on the screen size of the
   * android device
   *
   * @param sizeOfScreen that the game is being played on.
   */
  public void setEnemyMovement(int sizeOfScreen) {
    enemy.setxDirection(sizeOfScreen);
  }

  /**
   * Gets the movement of the enemy to send back to the View so that the view knows how much to
   * change it by each time to enemy moves
   *
   * @return the amount the enemy should move.
   */
  public int getEnemyMovement() {
    return enemy.getXDirection();
  }

  /**
   * When initializing the presenter, we should get data that consists of what npcs we can choose to
   * attack with. We initalize it here and sets currentNPC to a default first in line npc
   *
   * @param team that we are entering the game with
   */
  public void setBossTeam(List team) {
    currentTeam = 0;
    this.bossTeam = team;
    if (bossTeam != null) currentNPC = (NPC) bossTeam.get(0);
  }

  /**
   * Updates the game logic. This only consists of calling action on the enemy since it is the only
   * element of the game which is dynamic in the sense of updating constantly since it's resistance
   * changes.
   */
  public void update() {
    enemy.action();
  }

  /** Adds onto the amount of the a shot was fired. */
  public void shotFired() {
    score++;
  }

  /**
   * Returns the score that the player has at the end of the game. The least amount is the best.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * Allows us to know when the game has come to an end, which is dependent on the enemy's health
   *
   * @return whether or not the enemy is dead
   */
  public boolean determineEnd() {
    return enemy.getHealth() <= 0;
  }


  /**
   * Gets the current health of the enemy.
   *
   * @return health of enemy
   */
  public float getHealth() {
    return enemy.getHealth();
  }

  public float getInitialHealth(){
    return enemy.getInitialHealth();
  }
}
