package com.cajan.commonlib;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * ClassName ：MapUtils
 * Description : Map操作工具
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class MapUtils {

  /**
   * 将指定位置的资源文件读取到MAP中,位置从classpath位置开始
   *
   * @throws Exception
   */
  public static Map<String, Object> readToMapString(String filePath) throws Exception {
    // define Properties property = new Properties();
    Properties property = new Properties();
    InputStream inputFile = null;
    Iterator<Map.Entry<Object, Object>> iter = null;
    Map.Entry<Object, Object> entry = null;
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      // classloader can be use this class, um, rt: ABC.class.ClassLoader
      // inputFile = ClassLoader.getSystemResourceAsStream(filePath);
      inputFile = MapUtils.class.getResourceAsStream(filePath);
      // 装载配置文件
      property.load(inputFile);
      iter = property.entrySet().iterator();
      while (iter.hasNext()) {
        entry = iter.next();
        map.put(entry.getKey().toString().toLowerCase().trim(),
            entry.getValue().toString().toLowerCase().trim());
      }
    } finally {
      // 关闭输入流
      if (inputFile != null) {
        inputFile.close();
      }
    }
    return map;
  }

  /**
   * 将指定位置的资源文件读取到MAP中,位置从classpath位置开始
   *
   * @throws Exception
   */
  public static Map<Object, Object> readToMapLower(String filePath) throws Exception {
    // define Properties property = new Properties();
    Properties property = new Properties();
    InputStream inputFile = null;
    Iterator<Map.Entry<Object, Object>> iter = null;
    Map.Entry<Object, Object> entry = null;
    Map<Object, Object> map = new HashMap<Object, Object>();
    try {
      // classloader can be use this class, um, rt: ABC.class.ClassLoader
      // inputFile = ClassLoader.getSystemResourceAsStream(filePath);
      inputFile = MapUtils.class.getResourceAsStream(filePath);
      // 装载配置文件
      property.load(inputFile);
      iter = property.entrySet().iterator();
      while (iter.hasNext()) {
        entry = iter.next();
        map.put(entry.getKey().toString(), entry.getValue().toString().toLowerCase());
      }
    } finally {
      // 关闭输入流
      if (inputFile != null) {
        inputFile.close();
      }
    }
    return map;
  }

  /**
   * 将指定位置的资源文件读取到MAP中,位置从classpath位置开始
   *
   * @throws Exception
   */
  public static Map<Object, Object> readToMap(String filePath) throws Exception {
    // define Properties property = new Properties();
    Properties property = new Properties();
    InputStream inputFile = null;
    try {
      // classloader can be use this class, um, rt: ABC.class.ClassLoader
      // inputFile = ClassLoader.getSystemResourceAsStream(filePath);
      inputFile = MapUtils.class.getResourceAsStream(filePath);
      // 装载配置文件
      property.load(inputFile);
    } finally {
      // 关闭输入流
      if (inputFile != null) {
        inputFile.close();
      }
    }
    return property;
  }
}
