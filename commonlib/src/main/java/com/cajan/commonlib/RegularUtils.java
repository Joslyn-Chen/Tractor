package com.cajan.commonlib;

import android.app.Activity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName ：RegularUtils
 * Description ：正则工具
 * Created : Administrator
 * Time : 2015/12/15
 * Version : 1.0
 */
public class RegularUtils {

  public RegularUtils() {
  }

  /**
   * 验证是否为正整数包括0
   * 判断正负整数：^-?[0-9]+
   * 判断全部数字：-?[0-9]+.?[0-9]+
   */
  public static Boolean isNumber(String num) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher matcher = pattern.matcher(num);
    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * 验证手机号
   *
   * @param context
   * @param mobiles
   * @return
   */
  public static boolean checkPhoneNum(Activity context, String mobiles) {
    if (!fhoneFormat(mobiles)) {
      T.showToast(context, "手机号格式不正确");
      return false;
    }
    return true;
  }

  private static boolean fhoneFormat(String mobiles) {
    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-1,5-9]))\\d{8}$");
    Matcher m = p.matcher(mobiles);
    return m.matches();
  }

  /**
   * 验证邮箱
   *
   * @param context
   * @param email
   * @return
   */
  public static boolean checkEmail(Activity context, String email) {
    if (!emailFormat(email) || email.length() > 31) {
      T.showToast(context, "邮箱格式不正确");
      return false;
    }
    return true;
  }

  private static boolean emailFormat(String email) {
    Pattern pattern = Pattern.compile("^[A-Za-z\\d]+(\\.[A-Za-z\\d]+)*@([\\dA-Za-z](-[\\dA-Za-z])?)+(\\.{1,2}[A-Za-z]+)+$");
    Matcher mc = pattern.matcher(email);
    return mc.matches();
  }

  /**
   * 验证验证码位数
   *
   * @param context
   * @param code
   * @return
   */
  public static boolean checkCode(Activity context, String code) {
    if (code.length() != 4) {
      T.showToast(context, "请输入正确的四位验证码");
      return false;
    }
    return true;
  }

  /**
   * 验证身份证号
   *
   * @param context
   * @param IDcard
   * @return
   */
  public static boolean checkIDcard(Activity context, String IDcard) {
    Pattern p = Pattern.compile("\\d{15}|\\d{18}");
    Matcher m = p.matcher(IDcard);
    Boolean id = m.matches();
    if (!id) {
      T.showToast(context, "身份证号码不正确");
      return false;
    }
    return true;
  }

  /**
   * 验证密码
   *
   * @param context
   * @param password
   * @return
   */
  public static boolean checkPassword(Activity context, String password) {
    Pattern p = Pattern.compile("[\\da-zA-Z]{4,10}");
    Matcher m = p.matcher(password);
    Boolean pwd = m.matches();
    if (!pwd) {
      T.showToast(context, "密码格式不正确");
      return false;
    }
    return true;
  }
}
