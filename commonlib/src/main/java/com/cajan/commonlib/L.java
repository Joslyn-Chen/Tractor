package com.cajan.commonlib;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cajan.commonlib.logger.LogLevel;
import com.cajan.commonlib.logger.Logger;

/**
 * ClassName ：L
 * Description ：Logger
 * Created : Administrator
 * Time : 2016/3/7
 * Version : 1.0
 */
public class L {

  /**
   * 日志开关
   */
  private static boolean mIsOpen = true;

  public static void init() {
    Logger.init("Logger").hideThreadInfo().methodOffset(1).methodCount(1).logLevel(LogLevel.FULL);
  }

  public static void setOpen(boolean isOpen) {
    mIsOpen = isOpen;
  }

  public static boolean isOpened() {
    return mIsOpen;
  }


  public static void d(String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.d(info, args);
  }

  public static void d(@NonNull String tag, @Nullable String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.t(tag).d(info, args);
  }

  public static void e(String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.e(info, args);
  }

  public static void e(@NonNull String tag, String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.t(tag).e(info, args);
  }

  public static void w(String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.w(info, args);
  }

  public static void w(@NonNull String tag, String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.t(tag).w(info, args);
  }

  public static void i(String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.i(info, args);
  }

  public static void i(@NonNull String tag, String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.t(tag).i(info, args);
  }

  public static void v(String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.v(info, args);
  }

  public static void v(@NonNull String tag, String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.t(tag).v(info, args);
  }

  public static void wtf(String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.wtf(info, args);
  }

  public static void wtf(@NonNull String tag, String info, Object... args) {
    if (!mIsOpen) {
      return;
    }
    Logger.t(tag).wtf(info, args);
  }

  public static void j(String json) {
    if (!mIsOpen) {
      return;
    }
    Logger.json(json);
  }

  public static void x(String xml) {
    if (!mIsOpen) {
      return;
    }
    Logger.xml(xml);
  }

}
