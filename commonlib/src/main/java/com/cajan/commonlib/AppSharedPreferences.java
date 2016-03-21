package com.cajan.commonlib;

import android.content.Context;

/**
 * ClassName ：AppSharedPreferences
 * Description : App相关存储
 * Created : Cajan
 * Time : 2015/9/21
 * Version : 1.0
 */
public class AppSharedPreferences {

  private static String LoginStatePreName = "Login";
  private static String AppPreName = "app";

  public AppSharedPreferences() {
  }

  /**
   * 当前用户信息配置头
   */
  public static String getUserInfoPreName(Context context) {
    return getUserAccount(context) + "user";
  }

  /**
   * 设置登录状态
   */
  public static void setLoginState(Context context, Boolean state) {
    PreferencesUtils.setPreferenceName(LoginStatePreName);
    PreferencesUtils.putBoolean(context, "loginState", state);
  }

  /**
   * 获取登录状态
   */
  public static boolean getLoginState(Context context) {
    PreferencesUtils.setPreferenceName(LoginStatePreName);
    return PreferencesUtils.getBoolean(context, "loginState");
  }

  /**
   * 保存用户账号、密码、ClientID
   */
  public static void saveLoginNamePwdCID(Context context, String account, String pwd, String cid) {
    PreferencesUtils.setPreferenceName(AppPreName);
    PreferencesUtils.putString(context, "account", account);
    PreferencesUtils.putString(context, "password", pwd);
    PreferencesUtils.putString(context, "clientID", cid);
  }

  /**
   * 获取用户账号、密码、ClientID 以逗号隔开
   */
  public static String getNamePwdCID(Context context) {
    PreferencesUtils.setPreferenceName(AppPreName);
    String account = PreferencesUtils.getString(context, "account");
    String pwd = PreferencesUtils.getString(context, "password");
    String cid = PreferencesUtils.getString(context, "clientID");
    String result = account + "," + pwd + "," + cid;
    return result;
  }

  /**
   * 获取当前ClientID
   */
  public static String getClientID(Context context) {
    PreferencesUtils.setPreferenceName(AppPreName);
    return PreferencesUtils.getString(context, "clientID");
  }

  /**
   * 获取当前用户密码
   */
  public static String getUserPassword(Context context) {
    PreferencesUtils.setPreferenceName(AppPreName);
    return PreferencesUtils.getString(context, "password");
  }

  /**
   * 保存用户账号
   */
  public static void saveLoginAccount(Context context, String account) {
    PreferencesUtils.setPreferenceName(AppPreName);
    PreferencesUtils.putString(context, "account", account);
  }

  /**
   * 获取当前用户账号
   */
  public static String getUserAccount(Context context) {
    PreferencesUtils.setPreferenceName(AppPreName);
    return PreferencesUtils.getString(context, "account");
  }

  /**
   * 保存用户头像url
   */
  public static void setUserHeader(Context context, String headerUrl) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    PreferencesUtils.putString(context, "header", headerUrl);
  }

  /**
   * 获取用户头像url
   */
  public static String getUserHeader(Context context) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getString(context, "header");
  }

  /**
   * 保存用户个人信息
   */
  /*public static void saveUserInfo(Context context, UserEntity userEntity) {
    SharedPreferences preferences =
        context.getSharedPreferences(getUserInfoPreName(context), Context.MODE_APPEND);
    Gson gson = new Gson();
    String user = gson.toJson(userEntity);
    Editor editor = preferences.edit();
    editor.putString("user", user);
    editor.apply();
  }*/

  /**
   * 获取用户个人信息
   *
   * @return Info
   */
  /*public static UserEntity getUserInfo(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(getUserInfoPreName(context), Context.MODE_APPEND);
    String user = preferences.getString("user", "");
    Gson gson = new Gson();
    return gson.fromJson(user, UserEntity.class);
  }*/

  /**
   * 获取用户单个信息
   *
   * @return String
   */
  public static String getUserSingle(Context context, String key) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getString(context, key);
  }

  /**
   * 设置消息开关
   */
  public static void setUserMessageSwitch(Context context, Boolean check) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    PreferencesUtils.putBoolean(context, "news", check);
  }

  /**
   * 获取设置消息状态
   */
  public static Boolean getUserMessageSwitch(Context context) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getBoolean(context, "news");
  }

  /**
   * 设置声音开关
   */
  public static void setUserToneSwitch(Context context, Boolean check) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    PreferencesUtils.putBoolean(context, "tone", check);
  }

  /**
   * 获取设置声音状态
   */
  public static Boolean getUserToneSwitch(Context context) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getBoolean(context, "tone");
  }

  public static String IdCardFrontage = "IdCardFrontage";
  public static String IdCardBack = "IdCardBack";
  public static String HousingVoucher = "HousingVoucher";

  /**
   * 获取身份证正面照的Path
   */
  public static String getIdCardFrontage(Context context) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getString(context, IdCardFrontage);
  }

  /**
   * 保存身份证正面照的Path
   */
  public static void setIdCardFrontage(Context context, String path) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    PreferencesUtils.putString(context, IdCardFrontage, path);
  }

  /**
   * 获取身份证反面照的Path
   */
  public static String getIdCardBack(Context context) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getString(context, IdCardBack);
  }

  /**
   * 保存身份证反面照的Path
   */
  public static void setIdCardBack(Context context, String path) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    PreferencesUtils.putString(context, IdCardBack, path);
  }

  /**
   * 获取公积金凭证的Path
   */
  public static String getHousingVoucher(Context context) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    return PreferencesUtils.getString(context, HousingVoucher);
  }

  /**
   * 保存公积金凭证的Path
   */
  public static void setHousingVoucher(Context context, String path) {
    PreferencesUtils.setPreferenceName(getUserInfoPreName(context));
    PreferencesUtils.putString(context, HousingVoucher, path);
  }
}
