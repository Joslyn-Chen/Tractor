package com.cajan.commonlib;

/**
 * ClassName ：ComparisonUtils
 * Description : 比较工具
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class ComparisonUtils {

  /**
   * 比较版本号大小, 第一个应该为旧版本,第二个为新版本
   *
   * @return 第一个比第二个大, True, 反之false
   */
  public static Boolean compareVersion(String version1, String version2) {
    // 0.0.1 0.0.2
    String[] version1Arr = version1.split("\\.");
    String[] version2Arr = version2.split("\\.");

    Boolean isBig = false;
    int version1ArrLen = version1Arr.length;
    int version2ArrLen = version2Arr.length;
    if (version2ArrLen < version1ArrLen) {
      // 第二个数比第一个数少些倍数,不能比较
      return isBig;
    }

    for (int i = 0; i < version1ArrLen; i++) {
      String string = version1Arr[i];
      String string2 = version2Arr[i];

      int ver1 = Integer.parseInt(string.trim());
      int ver2 = Integer.parseInt(string2.trim());

      isBig = compareInt(ver1, ver2);

      if (isBig) {
        return isBig;
      }
    }
    return isBig;
  }

  /**
   * 数据比对,
   *
   * @param int1 第一个数字
   * @param int2 第二个数字
   * @return 如果第一个大于等于第二个ture, 反之false
   */
  public static Boolean compareInt(Integer int1, Integer int2) {
    if (int1 > int2) {
      return true;
    } else {
      return false;
    }
  }

  public static Integer getPageIndex(Integer pageSize, Integer pageCount) {
    if ((pageCount % pageSize) == 0) {
      return pageCount / pageSize;
    } else {
      return pageCount / pageSize + 1;
    }
  }
}
