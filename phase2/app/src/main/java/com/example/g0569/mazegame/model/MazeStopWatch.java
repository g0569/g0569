package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;

public class MazeStopWatch extends Item {
    private long totalTime;
    private long elapsedTime;
    private boolean running;
    private long startTime;
    private long pauseTime;
    private long pauseEnd;


    MazeStopWatch(int time){
        super();
        totalTime = convertSecondToNano(time);
        running = false;
    }
    /**
     * Update
     */
    @Override
    public void update() {
    if (running) {
      long period = System.nanoTime() - startTime - pauseTime;
      setElapsedTime(period);
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    private long convertSecondToNano(int time){
        return time*1000000000;
    }

    private int convertNanoToSecond(long time){
        return (int)time/1000000000;
    }

    public int getRemainTime(){
        return convertNanoToSecond((totalTime - elapsedTime));
    }

    public void start(){
        running = true;
        setStartTime(System.nanoTime());
    }

    public void stop(){
        running = false;
        setElapsedTime(0);
        setStartTime(0);
    }

    public boolean isRunning(){
        return running;
    }

    /**
     * TODO call when doing save and pause
     */
    public void pause(){
        running = false;
        pauseEnd = System.nanoTime();
    }

    public void resume(){
        running = true;
        pauseTime += System.nanoTime() - pauseEnd;

    }

    public String toString() {
        return (getRemainTime()) + " Seconds";
    }
}

