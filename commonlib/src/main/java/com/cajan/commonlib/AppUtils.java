package com.cajan.commonlib;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ClassName ：AppUtils
 * Description : App相关工具（需要优化）
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class AppUtils {

  public static List<String[]> mProcessList = null;

  /**
   * 描述：APP是否第一次启动.
   *
   * @return Boolean
   */
  public static Boolean isFirstStart(Context context) {
    Calendar calendar = Calendar.getInstance();
    Integer day = calendar.get(Calendar.DAY_OF_MONTH);
    SharedPreferences sharedPreferences =
        context.getSharedPreferences("phone", Context.MODE_PRIVATE);
    Boolean isFirstStart = sharedPreferences.getBoolean("firststart", true);
    if (isFirstStart) {
      SharedPreferences.Editor edit = sharedPreferences.edit();
      edit.putBoolean("firststart", false);
      edit.putString("today", day.toString());
      edit.apply();
    }
    return isFirstStart;
  }

  /**
   * 描述：APP是否今天第一次启动.
   *
   * @return Boolean
   */
  public static Boolean isDayFirstStart(Context context) {
    Calendar calendar = Calendar.getInstance();
    Integer day = calendar.get(Calendar.DAY_OF_MONTH);

    SharedPreferences sharedPreferences =
        context.getSharedPreferences("phone", Context.MODE_PRIVATE);
    String todayString = sharedPreferences.getString("today", "0");
    Integer today = Integer.parseInt(todayString);
    if (day.equals(today)) {
      SharedPreferences.Editor edit = sharedPreferences.edit();
      edit.putString("today", day.toString());
      edit.apply();
      return true;
    }
    return false;
  }

  /**
   * 描述：打开并安装文件.
   *
   * @param context the context
   * @param file apk文件路径
   */
  public static void installApk(Context context, File file) {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    context.startActivity(intent);
  }

  /**
   * 描述：卸载程序.
   *
   * @param context the context
   * @param packageName 包名
   */
  public static void uninstallApk(Context context, String packageName) {
    Intent intent = new Intent(Intent.ACTION_DELETE);
    Uri packageURI = Uri.parse("package:" + packageName);
    intent.setData(packageURI);
    context.startActivity(intent);
  }

  /**
   * 用来判断服务是否运行.
   *
   * @param context the context
   * @param className 判断的服务名字 "com.xxx.xx..XXXService"
   * @return true 在运行 false 不在运行
   */
  public static boolean isServiceRunning(Context context, String className) {
    boolean isRunning = false;
    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningServiceInfo> servicesList =
        activityManager.getRunningServices(Integer.MAX_VALUE);
    Iterator<ActivityManager.RunningServiceInfo> l = servicesList.iterator();
    while (l.hasNext()) {
      ActivityManager.RunningServiceInfo si = l.next();
      if (className.equals(si.service.getClassName())) {
        isRunning = true;
      }
    }
    return isRunning;
  }

  /**
   * 停止服务.
   *
   * @param context the context
   * @param className the class name
   * @return true, if successful
   */
  public static boolean stopRunningService(Context context, String className) {
    Intent intent_service = null;
    boolean ret = false;
    try {
      intent_service = new Intent(context, Class.forName(className));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (intent_service != null) {
      ret = context.stopService(intent_service);
    }
    return ret;
  }

  /**
   * Gets the number of cores available in this device, across all processors.
   * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
   *
   * @return The number of cores, or 1 if failed to get result
   */
  public static int getNumCores() {
    try {
      // Get directory containing CPU info
      File dir = new File("/sys/devices/system/cpu/");
      // Filter to only list the devices we care about
      File[] files = dir.listFiles(new FileFilter() {

        @Override public boolean accept(File pathname) {
          // Check if filename is "cpu", followed by a single digit
          // number
          if (Pattern.matches("cpu[0-9]", pathname.getName())) {
            return true;
          }
          return false;
        }
      });
      // Return the number of cores (virtual CPU devices)
      return files.length;
    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }
  }

  /**
   * 描述：判断网络是否有效.
   *
   * @param context the context
   * @return true, if is network available
   */
  public static boolean isNetworkAvailable(Context context) {
    try {
      ConnectivityManager connectivity =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity != null) {
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
          if (info.getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }

  /**
   * Gps是否打开 需要<uses-permission
   * android:name="android.permission.ACCESS_FINE_LOCATION" />权限
   *
   * @param context the context
   * @return true, if is gps enabled
   */
  public static boolean isGpsEnabled(Context context) {
    LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }

  /**
   * 判断当前网络是否是移动数据网络.
   *
   * @param context the context
   * @return boolean
   */
  public static boolean isMobile(Context context) {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
      return true;
    }
    return false;
  }

  /**
   * 导入数据库.
   *
   * @param context the context
   * @param dbName the db name
   * @param rawRes the raw res
   * @return true, if successful
   */
  public static boolean importDatabase(Context context, String dbName, int rawRes) {
    int buffer_size = 1024;
    InputStream is = null;
    FileOutputStream fos = null;
    boolean flag = false;

    try {
      String dbPath = "/data/data/" + context.getPackageName() + "/databases/" + dbName;
      File dbfile = new File(dbPath);
      // 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
      if (!dbfile.exists()) {
        // 欲导入的数据库
        if (!dbfile.getParentFile().exists()) {
          dbfile.getParentFile().mkdirs();
        }
        dbfile.createNewFile();
        is = context.getResources().openRawResource(rawRes);
        fos = new FileOutputStream(dbfile);
        byte[] buffer = new byte[buffer_size];
        int count = 0;
        while ((count = is.read(buffer)) > 0) {
          fos.write(buffer, 0, count);
        }
        fos.flush();
      }
      flag = true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (Exception e) {
        }
      }
      if (is != null) {
        try {
          is.close();
        } catch (Exception e) {
        }
      }
    }
    return flag;
  }

  /**
   * 打开键盘.
   *
   * @param context the context
   */
  public static void showSoftInput(Context context) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
  }

  /**
   * 关闭键盘事件.
   *
   * @param context the context
   */
  public static void closeSoftInput(Context context) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
      inputMethodManager.hideSoftInputFromWindow(
          ((Activity) context).getCurrentFocus().getWindowToken(),
          InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

  /**
   * 获取包信息.
   *
   * @param context the context
   */
  public static PackageInfo getPackageInfo(Context context) {
    PackageInfo info = null;
    try {
      String packageName = context.getPackageName();
      info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }

  /**
   * 跳转到打电话的界面的Intent。
   *
   * @param phoneNumber 要拨打的号码
   * @return 打电话的Intent
   */
  public static final Intent call(String phoneNumber) {
    Uri uri = Uri.parse("tel:" + phoneNumber);
    return new Intent(Intent.ACTION_DIAL, uri);
  }

  /**
   * 浏览器打开地址
   */
  public static final Intent view(String url) {
    Uri uri = Uri.parse("http://" + url);
    return new Intent(Intent.ACTION_VIEW, uri);
  }

  /**
   * 描述：根据进程名返回应用程序.
   */
  public static ApplicationInfo getApplicationInfo(Context context, String processName) {
    if (processName == null) {
      return null;
    }

    PackageManager packageManager = context.getApplicationContext().getPackageManager();
    List<ApplicationInfo> appList =
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    for (ApplicationInfo appInfo : appList) {
      if (processName.equals(appInfo.processName)) {
        return appInfo;
      }
    }
    return null;
  }

  /**
   * 描述：kill进程.
   */
  public static void killProcesses(Context context, int pid, String processName) {
        /*
         * String cmd = "kill -9 "+pid; Process process = null; DataOutputStream
		 * os = null; try { process = Runtime.getRuntime().exec("su"); os = new
		 * DataOutputStream(process.getOutputStream()); os.writeBytes(cmd +
		 * "\n"); os.writeBytes("exit\n"); os.flush(); process.waitFor(); }
		 * catch (Exception e) { e.printStackTrace(); }
		 * AbLogUtil.d(AbAppUtil.class, "#kill -9 "+pid);
		 */

    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    String packageName = null;
    try {
      if (processName.indexOf(":") == -1) {
        packageName = processName;
      } else {
        packageName = processName.split(":")[0];
      }

      activityManager.killBackgroundProcesses(packageName);

      Method forceStopPackage =
          activityManager.getClass().getDeclaredMethod("forceStopPackage", String.class);
      forceStopPackage.setAccessible(true);
      forceStopPackage.invoke(activityManager, packageName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 描述：执行命令.
   */
  public static String runCommand(String[] command, String workdirectory) {
    String result = "";
    try {
      ProcessBuilder builder = new ProcessBuilder(command);
      // set working directory
      if (workdirectory != null) {
        builder.directory(new File(workdirectory));
      }
      builder.redirectErrorStream(true);
      Process process = builder.start();
      InputStream in = process.getInputStream();
      byte[] buffer = new byte[1024];
      while (in.read(buffer) != -1) {
        String str = new String(buffer);
        result = result + str;
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 描述：运行脚本.
   */
  public static String runScript(String script) {
    String sRet = "";
    try {
      final Process m_process = Runtime.getRuntime().exec(script);
      final StringBuilder sbread = new StringBuilder();
      Thread tout = new Thread(new Runnable() {
        public void run() {
          BufferedReader bufferedReader =
              new BufferedReader(new InputStreamReader(m_process.getInputStream()), 8192);
          String ls_1 = null;
          try {
            while ((ls_1 = bufferedReader.readLine()) != null) {
              sbread.append(ls_1).append("\n");
            }
          } catch (IOException e) {
            e.printStackTrace();
          } finally {
            try {
              bufferedReader.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
      tout.start();

      final StringBuilder sberr = new StringBuilder();
      Thread terr = new Thread(new Runnable() {
        public void run() {
          BufferedReader bufferedReader =
              new BufferedReader(new InputStreamReader(m_process.getErrorStream()), 8192);
          String ls_1 = null;
          try {
            while ((ls_1 = bufferedReader.readLine()) != null) {
              sberr.append(ls_1).append("\n");
            }
          } catch (IOException e) {
            e.printStackTrace();
          } finally {
            try {
              bufferedReader.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
      terr.start();

      // int retvalue = m_process.waitFor();
      while (tout.isAlive()) {
        Thread.sleep(50);
      }
      if (terr.isAlive()) terr.interrupt();
      String stdout = sbread.toString();
      String stderr = sberr.toString();
      sRet = stdout + stderr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return sRet;
  }

  /**
   * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
   *
   * @return 应用程序是/否获取Root权限
   */
  public static boolean getRootPermission(Context context) {
    String packageCodePath = context.getPackageCodePath();
    Process process = null;
    DataOutputStream os = null;
    try {
      String cmd = "chmod 777 " + packageCodePath;
      // 切换到root帐号
      process = Runtime.getRuntime().exec("su");
      os = new DataOutputStream(process.getOutputStream());
      os.writeBytes(cmd + "\n");
      os.writeBytes("exit\n");
      os.flush();
      process.waitFor();
    } catch (Exception e) {
      return false;
    } finally {
      try {
        if (os != null) {
          os.close();
        }
        process.destroy();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  /**
   * 描述：获取进程运行的信息.
   */
  public static List<String[]> getProcessRunningInfo() {
    List<String[]> processList = null;
    try {
      String result = runCommandTopN1();
      processList = parseProcessRunningInfo(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return processList;
  }

  /**
   * 描述：top -n 1.
   */
  public static String runCommandTopN1() {
    String result = null;
    try {
      String[] args = { "/system/bin/top", "-n", "1" };
      result = runCommand(args, "/system/bin/");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 描述：解析数据.
   *
   * @param info User 39%, System 17%, IOW 3%, IRQ 0% PID PR CPU% S #THR VSS
   * RSS PCY UID Name 31587 0 39% S 14 542288K 42272K fg u0_a162
   * cn.amsoft.process 313 1 17% S 12 68620K 11328K fg system
   * /system/bin/surfaceflinger 32076 1 2% R 1 1304K 604K bg
   * u0_a162 /system/bin/top
   */
  public static List<String[]> parseProcessRunningInfo(String info) {
    List<String[]> processList = new ArrayList<String[]>();
    int Length_ProcStat = 10;
    String tempString = "";
    boolean bIsProcInfo = false;
    String[] rows = null;
    String[] columns = null;
    rows = info.split("[\n]+");
    // 使用正则表达式分割字符串
    for (int i = 0; i < rows.length; i++) {
      tempString = rows[i];
      // AbLogUtil.d(AbAppUtil.class, tempString);
      if (tempString.indexOf("PID") == -1) {
        if (bIsProcInfo == true) {
          tempString = tempString.trim();
          columns = tempString.split("[ ]+");
          if (columns.length == Length_ProcStat) {
            // 把/system/bin/的去掉
            if (columns[9].startsWith("/system/bin/")) {
              continue;
            }
            // AbLogUtil.d(AbAppUtil.class,
            // "#"+columns[9]+",PID:"+columns[0]);
            processList.add(columns);
          }
        }
      } else {
        bIsProcInfo = true;
      }
    }
    return processList;
  }
}
