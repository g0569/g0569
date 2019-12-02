package com.example.g0569.utils;

public interface Observable {

  /**
   * Returns the state that it is in
   *
   * @return the state
   */
  boolean getState();

  /**
   * Sets the state that it is in
   *
   * @param state of which it is in
   */
  void setState(boolean state);

  /**
   * Attaches an observer onto itself, when it updates itll update them as well
   *
   * @param observer that we want to attach
   */
  void attach(Observer observer);

  /** Notifies all of the Observers that it is attached to */
  void notifyAllObservers();
}
