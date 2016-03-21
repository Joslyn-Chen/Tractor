package com.cajan.commonlib;

import java.lang.reflect.Field;

/**
 * ClassName ：Types
 * Description : 类型
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class Types {

  public final static int BYTE = 1;

  public final static int SHORT = 2;

  public final static int INTEGER = 3;

  public final static int LONG = 4;

  public final static int BINARY = 5;

  public final static int BOOLEAN = 6;

  public final static int STRING = 10;

  public final static int FLOAT = 20;

  public final static int DOUBLE = 21;

  public final static int DATE = 30;

  public final static int TIME = 31;

  public final static int TIMESTAMP = 32;

  public final static int NULL = 50;

  public final static int OTHER = 999;

  public final static int JAVA_OBJECT = 2000;

  public final static int ARRAY = 2003;

  /**
   * 获取数据类型对应的int值
   *
   * @param typeString 数据类型,如: Byte, Short, Integer,  Long, Float, Double, String, Boolean, Date
   * @throws Exception
   */
  public static int getNumType(String typeString) throws Exception {
    int numType = 0;  // 类型对应的编码
    try {
      Types newInstance = Types.class.newInstance();
      Field[] declaredFields = newInstance.getClass().getDeclaredFields();
      int fieldLen = declaredFields.length;
      for (int i = 0; i < fieldLen; i++) {
        // 取得即返回
        if (declaredFields[i].getName().toUpperCase().equals(typeString.toUpperCase())) {
          numType = declaredFields[i].getInt(typeString.toUpperCase());
          return numType;
        }
      }
    } catch (InstantiationException e) {

    } catch (IllegalAccessException e) {

    }
    return numType;
  }
}
