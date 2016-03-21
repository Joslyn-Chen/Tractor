package com.cajan.commonlib;

import java.util.Collection;
import java.util.Map;

/**
 * ClassName ：ObjectUtils
 * Description : 对象操作工具
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class ObjectUtils {

  private ObjectUtils() {
    throw new AssertionError();
  }

  /**
   * compare two object
   *
   * @return <ul>
   * <li>if both are null, return true</li>
   * <li>return actual.{@link Object#equals(Object)}</li>
   * </ul>
   */
  public static boolean isEquals(Object actual, Object expected) {
    return actual == expected || (actual == null ? expected == null : actual.equals(expected));
  }

  /**
   * null Object to empty string
   *
   * <pre>
   * nullStrToEmpty(null) = &quot;&quot;;
   * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
   * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
   * </pre>
   */
  public static String nullStrToEmpty(Object str) {
    return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
  }

  /**
   * convert long array to Long array
   */
  public static Long[] transformLongArray(long[] source) {
    Long[] destin = new Long[source.length];
    for (int i = 0; i < source.length; i++) {
      destin[i] = source[i];
    }
    return destin;
  }

  /**
   * convert Long array to long array
   */
  public static long[] transformLongArray(Long[] source) {
    long[] destin = new long[source.length];
    for (int i = 0; i < source.length; i++) {
      destin[i] = source[i];
    }
    return destin;
  }

  /**
   * convert int array to Integer array
   */
  public static Integer[] transformIntArray(int[] source) {
    Integer[] destin = new Integer[source.length];
    for (int i = 0; i < source.length; i++) {
      destin[i] = source[i];
    }
    return destin;
  }

  /**
   * convert Integer array to int array
   */
  public static int[] transformIntArray(Integer[] source) {
    int[] destin = new int[source.length];
    for (int i = 0; i < source.length; i++) {
      destin[i] = source[i];
    }
    return destin;
  }

  /**
   * compare two object
   * <ul>
   * <strong>About result</strong>
   * <li>if v1 > v2, return 1</li>
   * <li>if v1 = v2, return 0</li>
   * <li>if v1 < v2, return -1</li>
   * </ul>
   * <ul>
   * <strong>About rule</strong>
   * <li>if v1 is null, v2 is null, then return 0</li>
   * <li>if v1 is null, v2 is not null, then return -1</li>
   * <li>if v1 is not null, v2 is null, then return 1</li>
   * <li>return v1.{@link Comparable#compareTo(Object)}</li>
   * </ul>
   */
  public static <V> int compare(V v1, V v2) {
    return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable) v1).compareTo(v2));
  }

  /**
   * 将对象清空, 断开引用, 集合断开前,会清空集合中的数据,慎用
   *
   * @throws Exception
   */
  public static Boolean destroyObject(Object obj) throws Exception {
    if (IsEmptyUtils.isEmpty(obj)) {
      return false;
    }
    if (obj instanceof Map<?, ?>) {
      ((Map<?, ?>) obj).clear();
      obj = null;
      return true;
    } else if (obj instanceof Collection<?>) {
      ((Collection<?>) obj).clear();
      obj = null;
      return true;
    } else {
      obj = null;
      return true;
    }
  }

  /**
   * 将对象清空, 断开引用, 集合断开前,会清空集合中的数据,慎用
   *
   * @throws Exception
   */
  @SuppressWarnings("unused") public static Boolean destroyObject(Object... obj) throws Exception {
    int length = obj.length;
    for (int i = 0; i < length; i++) {
      if (IsEmptyUtils.isEmpty(obj[i])) {
        return false;
      }
      if (obj[i] instanceof Map<?, ?>) {
        ((Map<?, ?>) obj[i]).clear();
        obj[i] = null;
        return true;
      } else if (obj[i] instanceof Collection<?>) {
        ((Collection<?>) obj[i]).clear();
        obj[i] = null;
        return true;
      } else {
        obj[i] = null;
        return true;
      }
    }
    return null;
  }

  /**
   * &#x65ad;&#x5f00;&#x5bf9;&#x8c61;&#x7684;&#x5f15;&#x7528;,&#x4e0d;&#x6e05;&#x7a7a;&#x6570;&#x636e;
   *
   * @throws Exception
   */
  public static Boolean offObject(Object obj) throws Exception {
    if (IsEmptyUtils.isEmpty(obj)) {
      return false;
    } else {
      obj = null;
      return true;
    }
  }

  /**
   * 断开对象的引用,不清空数据
   *
   * @throws Exception
   */
  public static Boolean offObject(Object... obj) throws Exception {
    if (IsEmptyUtils.isEmpty(obj)) {
      return false;
    } else {
      obj = null;
      return true;
    }
  }
}
