package com.cajan.commonlib;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * ClassName ：T
 * Description ：Toast
 * Created : Administrator
 * Time : 2016/3/10
 * Version : 1.0
 */
public class T {

  /**
   * 上下文.
   */
  private static Context mContext = null;

  /**
   * 显示Toast.
   */
  public static final int SHOW_TOAST = 0;

  /**
   * 主要Handler类，在线程中可用 what：0.提示文本信息
   */
  private static final Handler baseHandler =
      new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override public boolean handleMessage(Message message) {
          switch (message.what) {
            case SHOW_TOAST:
              String text = message.getData().getString("TEXT");
              if (!TextUtils.isEmpty(text)) {
                showToast(mContext, text);
              }
              break;
            default:
              break;
          }
          return false;
        }
      });

  /**
   * 自定义Toast位置
   */
  public static Toast makeText(Context context, int resId, int duration) {
    return T.makeText(context, context.getString(resId), duration);
  }

  public static Toast makeText(Context context, CharSequence text, int duration) {
    Toast msg = Toast.makeText(context, text, duration);
    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
    msg.show();
    return msg;
  }

  /**
   * 描述：Toast提示文本.
   */
  public static void showToast(Context context, int resId) {
    showToast(context, context.getString(resId));
  }

  public static void showToast(Context context, @NonNull String text) {
    mContext = context;
    if (!TextUtils.isEmpty(text)) {
      Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * 描述：在线程中提示文本信息.
   *
   * @param resId 要提示的字符串资源ID，消息what值为0,
   */
  public static void showToastInThread(Context context, int resId) {
    mContext = context;
    showToastInThread(context, context.getString(resId));
  }

  /**
   * 描述：在线程中提示文本信息.
   *
   * @param text 消息what值为0
   */
  public static void showToastInThread(Context context, String text) {
    mContext = context;
    Message msg = baseHandler.obtainMessage(SHOW_TOAST);
    Bundle bundle = new Bundle();
    bundle.putString("TEXT", text);
    msg.setData(bundle);
    baseHandler.sendMessage(msg);
  }
}
