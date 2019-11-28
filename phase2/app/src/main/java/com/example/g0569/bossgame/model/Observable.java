package com.example.g0569.bossgame.model;

import java.util.ArrayList;
import java.util.List;

public interface Observable {

  public int getState();

  public int getInitialState();

  public void setState(int state);

  public void attach(Observer observer);

  public void notifyAllObservers();
  }

