package com.cajan.commonlib;

import android.content.Context;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName ：ConvertUtils
 * Description : 转换工具
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class ConvertUtils {

  /**
   * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
   */
  public static int dp2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
   */
  public static int px2dp(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * 将大数据类型(BLOB) Byte 转换为字符串
   *
   * @throws Exception
   */
  public static Object getBlobToString(Object value) throws Exception {
    if (!(value instanceof byte[])) {
      if (value == null) {
        value = "";
      } else {
        value = value.toString();
      }
      return value;
    } else {
      if (value == null) {
        value = "";
      } else {
        value = new String((byte[]) value);
      }
      return value;
    }
  }

  /**
   * 分转换为元.
   *
   * @param fen 分
   * @return 元
   */
  public static String fromFenToYuan(final String fen) {
    String yuan = "";
    final int MULTIPLIER = 100;
    Pattern pattern = Pattern.compile("^[0-9][0-9]*{1}");
    Matcher matcher = pattern.matcher(fen);
    if (matcher.matches()) {
      yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
    } else {
      System.out.println("参数格式不正确!");
    }
    return yuan;
  }

  /**
   * 元转换为分.
   *
   * @param yuan 元
   * @return 分
   */
  public static String fromYuanToFen(final String yuan) {
    String fen = "";
    Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,2})?{1}");
    Matcher matcher = pattern.matcher(yuan);
    if (matcher.matches()) {
      try {
        NumberFormat format = NumberFormat.getInstance();
        Number number = format.parse(yuan);
        double temp = number.doubleValue() * 100.0;
        // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
        format.setGroupingUsed(false);
        // 设置返回数的小数部分所允许的最大位数
        format.setMaximumFractionDigits(0);
        fen = format.format(temp);
      } catch (ParseException e) {

      }
    } else {
      System.out.println("参数格式不正确!");
    }
    return fen;
  }

  /**
   * 把byte数组转换为16进制数
   */
  public static String toHex(byte[] source) {
    StringBuffer b = new StringBuffer();

    for (int i = 0; i < source.length; i++) {
      b.append(Integer.toHexString(source[i]));
    }
    return b.toString();
  }
}
