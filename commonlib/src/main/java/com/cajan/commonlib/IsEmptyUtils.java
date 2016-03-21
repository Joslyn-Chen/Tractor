package com.cajan.commonlib;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * ClassName ：IsEmptyUtils
 * Description : 判断对象是否为空
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class IsEmptyUtils {

  /**
   * 判断对象是否为空,传入的对象可以是任何对象
   *
   * @param o 需要判定的对象,当传入LIST时,可能会进入isEmpty(Object... o)方法
   * @return 当对象为空时为真(true), 当对象不为空时为假(false)
   */
  public static Boolean isEmpty(Object o) {
    if (o == null) {
      return true;
    } else if (o instanceof Collection<?>) {
      if (((Collection<?>) o).isEmpty()) {
        return true;
      }
    } else if (o instanceof String) {
      if (((String) o).trim().length() <= 0) {
        return true;
      }
    } else if (o instanceof Map<?, ?>) {
      if (((Map<?, ?>) o).isEmpty()) {
        return true;
      }
    } else if (o.getClass().isArray()) {
      if (Array.getLength(o) <= 0) {
        return true;
      }
    }

    return false;
  }

  /**
   * 判断对象是否为空,传入的对象可以是任何对象
   *
   * @param o 需要判定的对象,当传入LIST时,可能会进入这个方法
   * @return 当对象为空时为真(true), 当对象不为空时为假(false)
   */
  public static Boolean isEmpty(Object... o) {
    if (o == null || o != null && o.length <= 0) {
      return true;
    }

    for (int i = 0; i < o.length; i++) {
      if (o[i] == null) {
        return true;
      } else if (o[i] instanceof Collection<?>) {
        if (((Collection<?>) o[i]).isEmpty()) {
          return true;
        }
      } else if (o[i] instanceof String) {
        if (((String) o[i]).trim().length() <= 0) {
          return true;
        }
      } else if (o[i] instanceof Map<?, ?>) {
        if (((Map<?, ?>) o[i]).isEmpty()) {
          return true;
        }
      } else if (o[i].getClass().isArray()) {
        if (Array.getLength(o[i]) <= 0) {
          return true;
        }
      }
    }
    return false;
  }
}
