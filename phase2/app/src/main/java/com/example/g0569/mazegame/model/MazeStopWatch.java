package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;

import java.io.Serializable;

public class MazeStopWatch extends Item implements Serializable {
  private long totalTime;
  private long elapsedTime;
  private boolean running;
  private long startTime;
  private long pauseTime;
  private long pauseEnd;
  private boolean isPaused;

  MazeStopWatch(int time) {
    super();
    totalTime = time;
    running = false;
  }
  /** Update */
  @Override
  public void update() {
    if (running) {
      //        System.out.println(System.nanoTime());
      //        System.out.println(startTime);
      long period = Math.abs(System.nanoTime() - startTime - pauseTime);
      setElapsedTime(period);
    }
    if (getRemainTime() == 0) {}
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(long elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  private long convertSecondToNano(long time) {
    return time * 1000000000;
  }

  private int convertNanoToSecond(long time) {
    //        return (int)TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS);
    return (int) (time / 1000000000);
  }

  public int getRemainTime() {
    //        System.out.println(totalTime);
    //        System.out.println(convertNanoToSecond(elapsedTime));
    return (int) (totalTime - convertNanoToSecond(elapsedTime));
  }

  public void setRemainTime(int time) {
    this.startTime = System.nanoTime() - convertSecondToNano(totalTime) + convertSecondToNano(time);
    this.elapsedTime = convertSecondToNano(totalTime) - convertSecondToNano(time);
    this.running = true;
  }

  public void start() {
    running = true;
    setStartTime(System.nanoTime());
  }

  public void stop() {
    running = false;
    setElapsedTime(0);
    setStartTime(0);
  }

  public boolean isRunning() {
    return running;
  }

  /** TODO call when doing save and pause */
  public void pause() {
    if (!isPaused) {
      running = false;
      pauseEnd = System.nanoTime();
    }
  }

  public void resume() {
    if (isPaused) {
      running = true;
      pauseTime += System.nanoTime() - pauseEnd;
    }
  }

  public String toString() {
    return (getRemainTime()) + " Seconds";
  }
}
