package com.example.g0569.module.game;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.g0569.MainActivity;
import com.example.g0569.auth.model.User;
import com.example.g0569.module.game.Boss.BossGame;
import com.example.g0569.utils.SQLiteHelper;
import com.example.g0569.utils.Utils;
import com.example.g0569.utils.Constants;

import java.util.List;

/** The Game manager. */
public class GameManager {

  private float screenWidth;
  private float screenHeight;
  private MainActivity mainActivity;
  private Game currentGame;
  private User user;
  private SQLiteHelper sqLitehelper;

  /**
   * Instantiates a new Game manager.
   *
   * @param mainActivity the main activity
   */
  public GameManager(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
    this.onStart();
  }

  /** Called when the game starts */
  public void onStart() {
    sqLitehelper = new SQLiteHelper(mainActivity, "users");
    // TODO Need to modify the way to get width and height since theses methods are deprecated now.
    this.screenWidth = mainActivity.getWindowManager().getDefaultDisplay().getWidth();
    this.screenHeight = mainActivity.getWindowManager().getDefaultDisplay().getHeight();
    mainActivity.getHandler().sendEmptyMessage(Constants.TO_MENU_VIEW);
  }

  /** Start the maze game */
  public void toMazeGame() {
    mainActivity.getHandler().sendEmptyMessage(Constants.TO_MAZE_VIEW);
    currentGame = new MazeGame(this);
  }

  /** Start the boss game. */
  public void toBossGame() {
    mainActivity.getHandler().sendEmptyMessage(Constants.TO_BOSS_VIEW);
    currentGame = new BossGame(this);
  }

  /** Start the chess game. */
  public void toChessGame() {
    mainActivity.getHandler().sendEmptyMessage(Constants.TO_CHESS_VIEW);
    currentGame = new AutoChessGame(this);
  }

  /**
   * Switch between different games and save current game TODO add new params
   *
   * @param nextGame the next game
   */
  public void changeGame(int nextGame) {
    //        call this.save()
    // TODO
  }

  /**
   * Log in a user with given email and password
   *
   * @param email the email of the user
   * @param password the password of the user
   * @return true if the email matches the password
   */
  public boolean login(String email, String password) {
    password = Utils.encodeByMD5(password);
    SQLiteDatabase db = sqLitehelper.getReadableDatabase();
    boolean f = false;
    try {
      Cursor cursor =
          db.query(
              "users",
              new String[] {"uid", "username"},
              "email=? and password=?",
              new String[] {email, password},
              null,
              null,
              null);
      if (cursor.getCount() > 0) {
        cursor.moveToNext();
//        user = new User(this, cursor.getInt(cursor.getColumnIndex("uid")));
//        Toast.makeText(mainActivity, "Welcome Back, " + user.getUsername(), Toast.LENGTH_SHORT)
//            .show();
        mainActivity.getHandler().sendEmptyMessage(Constants.TO_DEMO_VIEW);
        f = true;
      } else {
        Toast.makeText(mainActivity, "Incorrect email or password", Toast.LENGTH_SHORT).show();
      }
    } catch (Exception e) {
      Toast.makeText(mainActivity, "Unexpected Error", Toast.LENGTH_SHORT).show();
    } finally {
      db.close();
    }
    return f;
  }

  /**
   * Sign up a new user
   *
   * @param email the email of the user
   * @param username the username of the user
   * @param password the password of the user
   * @return true if sign up successfully
   */
  public boolean signUp(String email, String username, String password) {
    SQLiteDatabase db = sqLitehelper.getWritableDatabase();
    password = Utils.encodeByMD5(password);
    boolean f = false;
    try {
      ContentValues contentValues = new ContentValues();
      contentValues.put("username", username);
      contentValues.put("email", email);
      contentValues.put("password", password);
      contentValues.put("created_time", System.currentTimeMillis());
      db.insert("users", null, contentValues);
      Toast.makeText(mainActivity, "Sign up Successfully", Toast.LENGTH_SHORT).show();
      f = true;
//      mainActivity.getHandler().sendEmptyMessage(Constants.TO_LOGIN_VIEW);
    } catch (Exception e) {
      Toast.makeText(mainActivity, "Error occured", Toast.LENGTH_SHORT).show();
    } finally {
      db.close();
    }
    return f;
  }

  /** Save the current game. */
  public void save() {
    //        call currentGame.save() and store to SQLite using user.save(arg)
  }

  /** @return ture if someone has log in */
  public boolean isLogin() {
    return user != null;
  }

  /** @return the SQLite Helper */
  public SQLiteHelper getSqLitehelper() {
    return sqLitehelper;
  }

  /**
   * Gets the current game.
   *
   * @return the current game
   */
  public Game getCurrentGame() {
    return currentGame;
  }

  /**
   * Gets the screen width.
   *
   * @return the screen width
   */
  public float getScreenWidth() {
    return screenWidth;
  }

  /**
   * Gets the screen height.
   *
   * @return the screen height
   */
  public float getScreenHeight() {
    return screenHeight;
  }

  /**
   * Gets the main activity.
   *
   * @return the main activity
   */
  public MainActivity getMainActivity() {
    return mainActivity;
  }

  /**
   * Gets the user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * Show statistic on the screen
   *
   * @param statistic the statistic to be shown in a list.
   */
  public void showStatistic(List<String> statistic) {
    this.mainActivity.appendStatisticView(statistic);
  }
}
