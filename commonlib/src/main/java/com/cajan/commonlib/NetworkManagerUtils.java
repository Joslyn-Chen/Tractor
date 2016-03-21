package com.cajan.commonlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * ClassName ：NetworkManagerUtils
 * Description : 网络管理工具
 * Created : Cajan
 * Time : 2015/8/27
 * Version : 1.0
 */
public class NetworkManagerUtils {

  public static final String NETWORK_TYPE_WIFI = "wifi";
  public static final String NETWORK_TYPE_3G = "eg";
  public static final String NETWORK_TYPE_2G = "2g";
  public static final String NETWORK_TYPE_WAP = "wap";
  public static final String NETWORK_TYPE_UNKNOWN = "unknown";
  public static final String NETWORK_TYPE_DISCONNECT = "disconnect";

  /**
   * Get network type 获取网络类型
   */
  @SuppressWarnings("deprecation") public static String getNetWorkType(Context context) {
    ConnectivityManager manager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo;
    String type = NETWORK_TYPE_DISCONNECT;
    if (manager == null || (networkInfo = manager.getActiveNetworkInfo()) == null) {
      return type;
    }

    if (networkInfo.isConnected()) {
      String typeName = networkInfo.getTypeName();
      if ("WIFI".equalsIgnoreCase(typeName)) {
        type = NETWORK_TYPE_WIFI;
      } else if ("MOBILE".equalsIgnoreCase(typeName)) {
        String proxyHost = android.net.Proxy.getDefaultHost();
        type = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? NETWORK_TYPE_3G
            : NETWORK_TYPE_2G) : NETWORK_TYPE_WAP;
      } else {
        type = NETWORK_TYPE_UNKNOWN;
      }
    }
    return type;
  }

  /**
   * Whether is fast mobile network 是否快速的移动网络
   */
  private static boolean isFastMobileNetwork(Context context) {
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    if (telephonyManager == null) {
      return false;
    }

    switch (telephonyManager.getNetworkType()) {
      case TelephonyManager.NETWORK_TYPE_1xRTT:
        return false;
      case TelephonyManager.NETWORK_TYPE_CDMA:
        return false;
      case TelephonyManager.NETWORK_TYPE_EDGE:
        return false;
      case TelephonyManager.NETWORK_TYPE_EVDO_0:
        return true;
      case TelephonyManager.NETWORK_TYPE_EVDO_A:
        return true;
      case TelephonyManager.NETWORK_TYPE_GPRS:
        return false;
      case TelephonyManager.NETWORK_TYPE_HSDPA:
        return true;
      case TelephonyManager.NETWORK_TYPE_HSPA:
        return true;
      case TelephonyManager.NETWORK_TYPE_HSUPA:
        return true;
      case TelephonyManager.NETWORK_TYPE_UMTS:
        return true;
      case TelephonyManager.NETWORK_TYPE_EHRPD:
        return true;
      case TelephonyManager.NETWORK_TYPE_EVDO_B:
        return true;
      case TelephonyManager.NETWORK_TYPE_HSPAP:
        return true;
      case TelephonyManager.NETWORK_TYPE_IDEN:
        return false;
      case TelephonyManager.NETWORK_TYPE_LTE:
        return true;
      case TelephonyManager.NETWORK_TYPE_UNKNOWN:
        return false;
      default:
        return false;
    }
  }

  /**
   * 判断是否有网络连接
   *
   * @return boolean
   */
  public static boolean isNetWorkConnected(Context context) {
    if (context != null) {
      ConnectivityManager connectivity =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity == null) {
        return false;
      } else {
        NetworkInfo[] networkInfo = connectivity.getAllNetworkInfo();
        if (networkInfo != null) {
          for (NetworkInfo info : networkInfo) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
              return true;
            }
          }
        } else {
          return false;
        }
      }
    }
    return false;
  }

  /**
   * 获取当前网络的类型
   * -1=无网络；0=Mobile；1=Wi-fi
   *
   * @return int
   */
  public static int getNetWorkConnected(Context context) {
    int type = -1;
    if (context != null) {
      ConnectivityManager connectivity =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity != null) {
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
          type = networkInfo.getType();
        }
      }
    }
    return type;
  }

  /**
   * 判断WI-FI网络是否可用
   *
   * @return boolean
   */
  public static boolean isWifiConnected(Context context) {
    if (context != null) {
      ConnectivityManager connectivity =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      if (networkInfo != null) {
        return networkInfo.isAvailable();
      }
    }
    return false;
  }

  /**
   * 判断MOBILE网络是否可用
   *
   * @return boolean
   */
  public static boolean isMobileConnected(Context context) {
    if (context != null) {
      ConnectivityManager connectivity =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
      if (networkInfo != null) {
        return networkInfo.isAvailable();
      }
    }
    return false;
  }
}
