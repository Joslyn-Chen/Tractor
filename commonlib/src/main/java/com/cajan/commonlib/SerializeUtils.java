package com.cajan.commonlib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ClassName ：SerializeUtils
 * Description : 序列化工具
 * Created : Cajan
 * Time : 2015/8/27
 * Version : 1.0
 */
public class SerializeUtils {

  private SerializeUtils() {
    throw new AssertionError();
  }

  /**
   * deserialization from file
   *
   * @throws RuntimeException if an error occurs
   */
  public static Object deserialization(String filePath) {
    ObjectInputStream in = null;
    try {
      in = new ObjectInputStream(new FileInputStream(filePath));
      Object o = in.readObject();
      in.close();
      return o;
    } catch (FileNotFoundException e) {
      throw new RuntimeException("FileNotFoundException occurred. ", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("ClassNotFoundException occurred. ", e);
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          throw new RuntimeException("IOException occurred. ", e);
        }
      }
    }
  }

  /**
   * serialize to file
   *
   * @throws RuntimeException if an error occurs
   */
  public static void serialization(String filePath, Object obj) {
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(new FileOutputStream(filePath));
      out.writeObject(obj);
      out.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException("FileNotFoundException occurred. ", e);
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          throw new RuntimeException("IOException occurred. ", e);
        }
      }
    }
  }
}
