package com.cajan.commonlib;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * ClassName ：NumberUtils
 * Description : 数字工具
 * Created : Cajan
 * Time : 2015/9/21
 * Version : 1.0
 */
public class NumberUtils {

  private NumberUtils() {
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  /**
   * 验证是否可以转成字符串
   */
  public static double checkIsNumber(String str) {
    double d = 0.00;
    try {
      d = Double.parseDouble(str);
    } catch (Exception e) {
    }
    return d;
  }

  /**
   * return 返回两位小数点 的数字字符串
   */
  public static String getTwoNumber(String str) {

    double num = checkIsNumber(str);
    DecimalFormat df = new DecimalFormat("#0.000");
    return new BigDecimal(df.format(num)).setScale(2, BigDecimal.ROUND_DOWN).toString();
  }

  /**
   * return 返回 用户指定的位数 的数字字符串
   */
  public static String getDecimalsNumber(String str, int length) {

    double num = checkIsNumber(str);
    DecimalFormat df = new DecimalFormat();
    return new BigDecimal(df.format(num)).setScale(length, BigDecimal.ROUND_DOWN).toString();
  }
}
