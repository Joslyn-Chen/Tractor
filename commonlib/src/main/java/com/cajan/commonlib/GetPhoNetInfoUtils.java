package com.cajan.commonlib;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName ：GetPhoNetInfoUtils
 * Description : 获取手机相关信息
 * Created : Cajan
 * Time : 2015/8/27
 * Version : 1.0
 */
public class GetPhoNetInfoUtils {

  private static Long totalMemory;
  private static DisplayMetrics metrics;
  private static String[] cpuInfo;
  private static String macAddress;
  private static String[] phoneInfo;

  public static Map<String, String> getAllPhoneInfo(Context context) {
    totalMemory = getTotalMemory(context);
    metrics = getDisplayMetrics(context);
    cpuInfo = getCPUInfo();
    macAddress = getMacAddress(context);
    phoneInfo = getPhoneInfo(context);
    Map<String, String> mapPhoneInfo = new HashMap<String, String>();
    mapPhoneInfo.put("totalMemory", totalMemory.toString());
    mapPhoneInfo.put("metricsWidth", metrics.widthPixels + "");
    mapPhoneInfo.put("metricsHeight", metrics.heightPixels + "");
    mapPhoneInfo.put("cpuInfo", cpuInfo[0]);
    mapPhoneInfo.put("macAddress", macAddress);
    mapPhoneInfo.put("phoneMODEL", phoneInfo[1]);
    mapPhoneInfo.put("phoneIMST", phoneInfo[4]);
    return mapPhoneInfo;
  }

  /**
   * 获取手机可用内存.
   *
   * @return long
   */
  public static long getAvailMemory(Context context) {
    // 获取android当前可用内存大小
    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    MemoryInfo memoryInfo = new MemoryInfo();
    activityManager.getMemoryInfo(memoryInfo);
    // 当前系统可用内存 ,将获得的内存大小规格化
    return memoryInfo.availMem;
  }

  /**
   * 获取手机总内存.
   *
   * @return long
   */
  public static long getTotalMemory(Context context) {
    // 系统内存信息文件
    String file = "/proc/meminfo";
    String memInfo;
    String[] strs;
    long memory = 0;

    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);
      // 读取meminfo第一行，系统内存大小
      memInfo = bufferedReader.readLine();
      strs = memInfo.split("\\s+");
      // for(String str:strs){
      // }
      // 获得系统总内存，单位KB
      memory = Integer.valueOf(strs[1]).intValue() * 1024;
      bufferedReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Byte转位KB或MB
    return memory;
  }

  /**
   * 获取屏幕尺寸与密度.
   *
   * @return mDisplayMetrics
   */
  public static DisplayMetrics getDisplayMetrics(Context context) {
    Resources mResources;
    if (context == null) {
      mResources = Resources.getSystem();
    } else {
      mResources = context.getResources();
    }
    // DisplayMetrics{density=1.5, width=480, height=854, scaledDensity=1.5,
    // xdpi=160.421, ydpi=159.497}
    // DisplayMetrics{density=2.0, width=720, height=1280,
    // scaledDensity=2.0, xdpi=160.42105, ydpi=160.15764}
    DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
    return mDisplayMetrics;
  }

  /**
   * 获取手机CPU信息（[0]-cpu型号 、[1]-cpu频率）
   *
   * @return String[]
   */
  public static String[] getCPUInfo() {
    String str1 = "/proc/cpuinfo";
    String str2 = "";
    String[] cpuInfo = { "", "" }; // 1-cpu型号 //2-cpu频率
    String[] arrayOfString;
    try {
      FileReader fr = new FileReader(str1);
      BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
      str2 = localBufferedReader.readLine();
      arrayOfString = str2.split("\\s+");
      for (int i = 2; i < arrayOfString.length; i++) {
        cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
      }
      str2 = localBufferedReader.readLine();
      arrayOfString = str2.split("\\s+");
      cpuInfo[1] += arrayOfString[2];
      localBufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Log.i(TAG, "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
    return cpuInfo;
  }

  /**
   * 获取手机Mac地址
   *
   * @return String
   */
  public static String getMacAddress(Context context) {
    String result = "";
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    result = wifiInfo.getMacAddress();
    return result;
  }

  /**
   * 获取手机型号等信息 依次是：手机号码、手机型号、手机品牌、IMSI、IMEI
   *
   * @return String[]
   */
  public static String[] getPhoneInfo(Context context) {
    String[] phoneInfo = new String[5];
    TelephonyManager telManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    phoneInfo[0] = telManager.getLine1Number();// 手机号码，有的可得，有的不可得
    phoneInfo[1] = android.os.Build.MODEL;// 手机型号
    phoneInfo[2] = android.os.Build.BRAND;// 手机品牌
    phoneInfo[3] = telManager.getSubscriberId();// IMSI号
    phoneInfo[4] = telManager.getDeviceId();// IMEI号
    return phoneInfo;
  }
}
