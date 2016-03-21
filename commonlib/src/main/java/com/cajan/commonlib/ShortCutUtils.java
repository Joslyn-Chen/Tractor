package com.cajan.commonlib;

import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

/**
 * ClassName ：ShortCutUtils
 * Description : 快捷方式工具
 * Created : Cajan
 * Time : 2015/8/27
 * Version : 1.0
 */
public class ShortCutUtils {

  /**
   * 快捷方式添加的action
   */
  private final static String SHORTCUT_ADD_ACTION = "com.android.launcher.action.INSTALL_SHORTCUT";
  /**
   * 快捷方式删除的action
   */
  private final static String SHORTCUT_DEL_ACTION =
      "com.android.launcher.action.UNINSTALL_SHORTCUT";
  /**
   * 读取数据库需要的权限
   */
  private final static String READ_SETTINGS_PERMISSION =
      "com.android.launcher.permission.READ_SETTINGS";

  /**
   * 添加快捷方式到桌面，添加快捷方式需要添加用户权限
   * <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
   */
  public static void addShortCut(Context context, String shortCutName, int resourceId,
      Class<?> cls) {
    Intent shortCutIntent = new Intent(SHORTCUT_ADD_ACTION);
    //添加快捷方式的名字
    shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
    //不允许重复添加
    shortCutIntent.putExtra("duplicate", false);

    //指定当前的Activity为快捷方式启动的对象
    shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent().setClass(context, cls));

    //添加快捷方式的图标
    ShortcutIconResource iconRes = ShortcutIconResource.fromContext(context, resourceId);
    shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

    context.sendBroadcast(shortCutIntent);
  }

  /**
   * 删除桌面上的快捷方式，需要添加权限
   * <uses-permission android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT" />
   */
  public static void delShortcut(Context context) {
    Intent shortcut = new Intent(SHORTCUT_DEL_ACTION);
    // 获取当前应用名称
    String appName = null;
    try {
      appName = getAppName(context);
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    // 快捷方式名称
    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, appName);
    Intent shortcutIntent =
        context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
    context.sendBroadcast(shortcut);
  }

  /**
   * 判断快捷方式是否存在
   *
   * @return Boolean
   */
  public static Boolean hasShortCut(Context context) {
    String AUTHORITY = getAuthorityFromPermission(context, READ_SETTINGS_PERMISSION);
    if (AUTHORITY == null) {
      return false;
    }

    Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + ".settings/favorites?notify=true");
    String appName = "";
    try {
      appName = getAppName(context);
    } catch (NameNotFoundException e) {
      return false;
    }

    Cursor cursor = context.getContentResolver()
        .query(CONTENT_URI, new String[] { "title" }, "title=?", new String[] { appName }, null);
    if (cursor != null && cursor.getCount() > 0) {
      cursor.close();
      return true;
    } else {
      cursor.close();
      return false;
    }
  }

  /**
   * 获取不同手机的相应权限
   *
   * @return String
   */
  private static String getAuthorityFromPermission(Context context, String permission) {
    if (StringUtils.isEmpty(permission)) {
      return null;
    }

    List<PackageInfo> infos =
        context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
    if (IsEmptyUtils.isEmpty(infos)) {
      return null;
    }

    for (PackageInfo packageInfo : infos) {
      ProviderInfo[] providers = packageInfo.providers;
      if (providers != null) {
        for (ProviderInfo providerInfo : providers) {
          if (permission.equals(providerInfo.readPermission) || permission.equals(
              providerInfo.writePermission)) {
            return providerInfo.authority;
          }
        }
      }
    }

    return null;
  }

  /**
   * 获取App的名字
   *
   * @return String
   * @throws NameNotFoundException
   */
  private static String getAppName(Context context) throws NameNotFoundException {
    PackageManager packageManager = context.getPackageManager();
    String appName = packageManager.getApplicationLabel(
        packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA))
        .toString();
    return appName;
  }
}
