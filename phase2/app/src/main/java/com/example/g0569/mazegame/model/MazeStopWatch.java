package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;

import java.io.Serializable;

/** The type Maze stop watch. */
public class MazeStopWatch extends Item implements Serializable {
  private long totalTime;
  private long elapsedTime;
  private boolean running;
  private long startTime;
  private long pauseTime;
  private long pauseEnd;
  private boolean isPaused;

  /**
   * Instantiates a new Maze stop watch.
   *
   * @param time the time
   */
  MazeStopWatch(int time) {
    super();
    totalTime = time;
    running = false;
    isPaused = false;
  }
  /** Update */
  @Override
  public void update() {
    if (running) {
      long period = Math.abs(System.nanoTime() - startTime - pauseTime);
      setElapsedTime(period);
    }
  }

  /**
   * Setter of the start time
   *
   * @param startTime the start time that is assigned to the start time;
   */
  private void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  /**
   * Setter of the elapsed time
   *
   * @param elapsedTime the time that is assigned to the elapsed time;
   */
  private void setElapsedTime(long elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  /**
   * A second to nanosecond converter.
   *
   * @param time the time that is used to convert
   * @return the converted time
   */
  private long convertSecondToNano(long time) {
    return time * 1000000000;
  }

  /**
   * A nanosecond to second converter.
   *
   * @param time the time that is used to convert
   * @return the converted second time
   */
  private int convertNanoToSecond(long time) {
    return (int) (time / 1000000000);
  }

  /**
   * Gets remain time.
   *
   * @return the remain time
   */
  public int getRemainTime() {
    return (int) (totalTime - convertNanoToSecond(elapsedTime));
  }

  /**
   * Sets remain time.
   *
   * @param time the time
   */
  void setRemainTime(int time) {
    this.startTime = System.nanoTime() - convertSecondToNano(totalTime) + convertSecondToNano(time);
    this.elapsedTime = convertSecondToNano(totalTime) - convertSecondToNano(time);
    this.running = true;
  }

  /** Start to count the time. */
  public void start() {
    running = true;
    setStartTime(System.nanoTime());
  }

  /** Stop the time counting. */
  void stop() {
    running = false;
    setElapsedTime(0);
    setStartTime(0);
  }

  /** stop the time and which is able to resume */
  public void pause() {
    if (!isPaused) {
      running = false;
      pauseEnd = System.nanoTime();
    }
  }

  /** Resume the time of last pause. */
  public void resume() {
    if (isPaused) {
      running = true;
      pauseTime += System.nanoTime() - pauseEnd;
    }
  }
}
