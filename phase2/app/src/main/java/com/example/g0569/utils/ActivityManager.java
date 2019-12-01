package com.example.g0569.utils;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

/** The Activity manager. */
public class ActivityManager {

  private static final ActivityManager ourInstance = new ActivityManager();
  private Stack<WeakReference<Activity>> mActivityStack;

  private ActivityManager() {}

  /**
   * Gets instance.of the ActivityManager
   *
   * @return the instance
   */
  public static ActivityManager getInstance() {
    return ourInstance;
  }

  /** Check weak reference. */
  private void checkWeakReference() {
    if (mActivityStack != null) {
      for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
        WeakReference<Activity> activityReference = it.next();
        Activity temp = activityReference.get();
        if (temp == null) {
          it.remove();
        }
      }
    }
  }

  /**
   * Add an activity to stack.
   *
   * @param activity the activity
   */
  public void addActivity(Activity activity) {
    if (mActivityStack == null) {
      mActivityStack = new Stack<>();
    }
    mActivityStack.add(new WeakReference<>(activity));
  }

  /**
   * Get Current activity
   *
   * @return the activity
   */
  public Activity currentActivity() {
    checkWeakReference();
    if (mActivityStack != null && !mActivityStack.isEmpty()) {
      return mActivityStack.lastElement().get();
    }
    return null;
  }

  /** Finish current activity. */
  public void finishCurrentActivity() {
    Activity activity = currentActivity();
    if (activity != null) {
      finishActivity(activity);
    }
  }

  /**
   * Finish activity.with given class name
   *
   * @param cls the cls
   */
  public void finishActivity(Class<?> cls) {
    if (mActivityStack != null) {
      for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
        WeakReference<Activity> activityReference = it.next();
        Activity activity = activityReference.get();
        if (activity == null) {
          it.remove();
          continue;
        }
        if (activity.getClass().equals(cls)) {
          it.remove();
          activity.finish();
        }
      }
    }
  }

  /**
   * Finish a certain activity .
   *
   * @param activity the activity
   */
  public void finishActivity(Activity activity) {
    if (activity != null && mActivityStack != null) {
      for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
        WeakReference<Activity> activityReference = it.next();
        Activity temp = activityReference.get();
        if (temp == null) {
          it.remove();
          continue;
        }
        if (temp == activity) {
          it.remove();
        }
      }
      activity.finish();
    }
  }

  /** Finish all activity. */
  public void finishAllActivity() {
    if (mActivityStack != null) {
      for (WeakReference<Activity> activityReference : mActivityStack) {
        Activity activity = activityReference.get();
        if (activity != null) {
          activity.finish();
        }
      }
      mActivityStack.clear();
    }
  }
}
