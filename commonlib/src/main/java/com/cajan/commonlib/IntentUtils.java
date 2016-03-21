package com.cajan.commonlib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * ClassName ：IntentUtils
 * Description : Intent工具
 * Created : Cajan
 * Time : 2015/8/27
 * Version : 1.0
 */
public class IntentUtils {

  private static Intent intent;

  public IntentUtils() {
  }

  /**
   * 跳转Activity
   */
  public static void startActivity(Context context, Class<?> cls) {
    intent = new Intent(context, cls);
    context.startActivity(intent);
  }

  /**
   * 附加Action的Intent
   */
  public static void startActivityAction(Context context, String action) {
    intent = new Intent(action);
    context.startActivity(intent);
  }

  /**
   * 附加Bundle的Intent
   */
  public static void startActivityBundle(Context context, Class<?> cls, Bundle bundle) {
    intent = new Intent(context, cls);
    intent.putExtras(bundle);
    context.startActivity(intent);
  }

  /**
   * 启动服务
   */
  public static void startService(Context context, Class<?> cls) {
    intent = new Intent(context, cls);
    context.startService(intent);
  }

  /**
   * 停止服务
   */
  public static void stopService(Context context, Class<?> cls) {
    intent = new Intent(context, cls);
    context.stopService(intent);
  }
}
