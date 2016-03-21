package com.cajan.commonlib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ClassName ：ActivityLifecycleManager
 * Description ：Activity管理
 * Created : Administrator
 * Time : 2015/11/13
 * Version : 1.0
 */
public class ActivityLifecycleManager {

  private static Stack<Activity> activityStack = new Stack<Activity>();
  private static boolean autoManage = false;

  /**
   * 自动管理Activity，需要 android api 不低于14。
   */
  @TargetApi(14) public static void enableAutoManage(Application application) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      Log.w("AppManager", new UnsupportedOperationException(
          "The android os version of this device is unsupported."));
      return;
    }
    if (!autoManage) {
      application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
        @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
          add(activity);
        }

        @Override public void onActivityStarted(Activity activity) {

        }

        @Override public void onActivityResumed(Activity activity) {

        }

        @Override public void onActivityPaused(Activity activity) {

        }

        @Override public void onActivityStopped(Activity activity) {

        }

        @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override public void onActivityDestroyed(Activity activity) {
          remove(activity);
        }
      });
      autoManage = true;
    }
  }

  /**
   * 添加Activity到堆栈
   *
   * @param activity 要添加进入的Activity
   */
  public static void add(Activity activity) {
    activityStack.push(activity);
  }

  /**
   * 从堆栈中移除指定的Activity
   *
   * @param activity 要移除的Activity
   */
  public static void remove(Activity activity) {
    activityStack.remove(activity);
  }

  /**
   * 获取当前Activity（堆栈中最后一个压入的）
   */
  public static Activity currentActivity() {
    return activityStack.lastElement();
  }

  /**
   * 结束当前Activity（堆栈中最后一个压入的）
   */
  public static void finishCurrentActivity() {
    Activity activity = activityStack.pop();
    if (activity != null) {
      activity.finish();
    }
  }

  /**
   * 结束指定的Activity
   */
  public static void finishActivity(Activity activity) {
    if (activity != null) {
      activityStack.remove(activity);
      activity.finish();
    }
  }

  /**
   * 结束指定类名的Activity
   */
  public static void finishActivity(Class<?> cls) {
    ArrayList<Activity> activities = new ArrayList<Activity>();
    for (Activity activity : activityStack) {
      if (activity.getClass().equals(cls)) {
        activity.finish();
        activities.add(activity);
      }
    }
    for (Activity activity : activities) {
      activityStack.remove(activity);
    }
  }

  /**
   * 结束所有Activity
   */
  public static void finishAllActivity() {
    for (Activity activity : activityStack) {
      if (activity != null) {
        activity.finish();
      }
    }
    activityStack.clear();
  }
}
