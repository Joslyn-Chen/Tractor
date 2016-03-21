package com.cajan.commonlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName ：StringUtils
 * Description : 字符串工具
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class StringUtils {

  /**
   * 描述：将null转化为“”.
   *
   * @param str 指定的字符串
   * @return 字符串的String类型
   */
  public static String parseEmpty(String str) {
    if (str == null || "null".equals(str.trim())) {
      str = "";
    }
    return str.trim();
  }

  /**
   * 将字符前后的空格去掉
   *
   * @param str 需要处理的字符串
   * @return 返回处理后的字符串
   */
  public static String str2Trim(String str) {
    if (str != null || str != null && !"".equals(str)) {
      return str.trim();
    }
    return null;
  }

  /**
   * 描述：判断一个字符串是否为null或空值.
   *
   * @param str 指定的字符串
   * @return true or false
   */
  public static boolean isEmpty(String str) {
    return str == null || str.trim().length() == 0;
  }

  /**
   * 获取字符串中文字符的长度（每个中文算2个字符）.
   *
   * @param str 指定的字符串
   * @return 中文字符的长度
   */
  public static int chineseLength(String str) {
    int valueLength = 0;
    String chinese = "[\u0391-\uFFE5]";
    /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
    if (!isEmpty(str)) {
      for (int i = 0; i < str.length(); i++) {
        /* 获取一个字符 */
        String temp = str.substring(i, i + 1);
        /* 判断是否为中文字符 */
        if (temp.matches(chinese)) {
          valueLength += 2;
        }
      }
    }
    return valueLength;
  }

  /**
   * 描述：获取字符串的长度.
   *
   * @param str 指定的字符串
   * @return 字符串的长度（中文字符计2个）
   */
  public static int strLength(String str) {
    int valueLength = 0;
    String chinese = "[\u0391-\uFFE5]";
    if (!isEmpty(str)) {
      // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
      for (int i = 0; i < str.length(); i++) {
        // 获取一个字符
        String temp = str.substring(i, i + 1);
        // 判断是否为中文字符
        if (temp.matches(chinese)) {
          // 中文字符长度为2
          valueLength += 2;
        } else {
          // 其他字符长度为1
          valueLength += 1;
        }
      }
    }
    return valueLength;
  }

  /**
   * 描述：获取指定长度的字符所在位置.
   *
   * @param str 指定的字符串
   * @param maxL 要取到的长度（字符长度，中文字符计2个）
   * @return 字符的所在位置
   */
  public static int subStringLength(String str, int maxL) {
    int currentIndex = 0;
    int valueLength = 0;
    String chinese = "[\u0391-\uFFE5]";
    // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
    for (int i = 0; i < str.length(); i++) {
      // 获取一个字符
      String temp = str.substring(i, i + 1);
      // 判断是否为中文字符
      if (temp.matches(chinese)) {
        // 中文字符长度为2
        valueLength += 2;
      } else {
        // 其他字符长度为1
        valueLength += 1;
      }
      if (valueLength >= maxL) {
        currentIndex = i;
        break;
      }
    }
    return currentIndex;
  }

  /**
   * 描述：手机号格式验证.
   *
   * @param str 指定的手机号码字符串
   * @return 是否为手机号码格式:是为true，否则false
   */
  public static Boolean isMobileNo(String str) {
    Boolean isMobileNo = false;
    try {
      Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
      //			Pattern p = Pattern
      //					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
      Matcher m = p.matcher(str);
      isMobileNo = m.matches();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isMobileNo;
  }

  /**
   * 描述：是否只是字母和数字.
   *
   * @param str 指定的字符串
   * @return 是否只是字母和数字:是为true，否则false
   */
  public static Boolean isNumberLetter(String str) {
    Boolean isNoLetter = false;
    String expr = "^[A-Za-z0-9]+$";
    if (str.matches(expr)) {
      isNoLetter = true;
    }
    return isNoLetter;
  }

  /**
   * 描述：是否只是数字.
   *
   * @param str 指定的字符串
   * @return 是否只是数字:是为true，否则false
   */
  public static Boolean isNumber(String str) {
    Boolean isNumber = false;
    String expr = "^[0-9]+$";
    if (str.matches(expr)) {
      isNumber = true;
    }
    return isNumber;
  }

  /**
   * 描述：是否是邮箱.
   *
   * @param str 指定的字符串
   * @return 是否是邮箱:是为true，否则false
   */
  public static Boolean isEmail(String str) {
    Boolean isEmail = false;
    String expr =
        "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    if (str.matches(expr)) {
      isEmail = true;
    }
    return isEmail;
  }

  /**
   * 描述：是否是中文.
   *
   * @param str 指定的字符串
   * @return 是否是中文:是为true，否则false
   */
  public static Boolean isChinese(String str) {
    Boolean isChinese = true;
    String chinese = "[\u0391-\uFFE5]";
    if (!isEmpty(str)) {
      // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
      for (int i = 0; i < str.length(); i++) {
        // 获取一个字符
        String temp = str.substring(i, i + 1);
        // 判断是否为中文字符
        if (temp.matches(chinese)) {
        } else {
          isChinese = false;
        }
      }
    }
    return isChinese;
  }

  /**
   * 描述：是否包含中文.
   *
   * @param str 指定的字符串
   * @return 是否包含中文:是为true，否则false
   */
  public static Boolean isContainChinese(String str) {
    Boolean isChinese = false;
    String chinese = "[\u0391-\uFFE5]";
    if (!isEmpty(str)) {
      // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
      for (int i = 0; i < str.length(); i++) {
        // 获取一个字符
        String temp = str.substring(i, i + 1);
        // 判断是否为中文字符
        if (temp.matches(chinese)) {
          isChinese = true;
        } else {

        }
      }
    }
    return isChinese;
  }

  /**
   * 描述：从输入流中获得String.
   *
   * @param is 输入流
   * @return 获得的String
   */
  public static String convertStreamToString(InputStream is) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }

      // 最后一个\n删除
      if (sb.indexOf("\n") != -1 && sb.lastIndexOf("\n") == sb.length() - 1) {
        sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }

  /**
   * 描述：标准化日期时间类型的数据，不足两位的补0.
   *
   * @param dateTime 预格式的时间字符串，如:2012-3-2 12:2:20
   * @return String 格式化好的时间字符串，如:2012-03-20 12:02:20
   */
  public static String dateTimeFormat(String dateTime) {
    StringBuilder sb = new StringBuilder();
    try {
      if (isEmpty(dateTime)) {
        return null;
      }
      String[] dateAndTime = dateTime.split(" ");
      if (dateAndTime.length > 0) {
        for (String str : dateAndTime) {
          if (str.indexOf("-") != -1) {
            String[] date = str.split("-");
            for (int i = 0; i < date.length; i++) {
              String str1 = date[i];
              sb.append(strFormat2(str1));
              if (i < date.length - 1) {
                sb.append("-");
              }
            }
          } else if (str.indexOf(":") != -1) {
            sb.append(" ");
            String[] date = str.split(":");
            for (int i = 0; i < date.length; i++) {
              String str1 = date[i];
              sb.append(strFormat2(str1));
              if (i < date.length - 1) {
                sb.append(":");
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return sb.toString();
  }

  /**
   * 描述：不足2个字符的在前面补“0”.
   *
   * @param str 指定的字符串
   * @return 至少2个字符的字符串
   */
  public static String strFormat2(String str) {
    try {
      if (str.length() <= 1) {
        str = "0" + str;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  /**
   * 描述：截取字符串到指定字节长度.
   *
   * @param str the str
   * @param length 指定字节长度
   * @return 截取后的字符串
   */
  public static String cutString(String str, int length) {
    return cutString(str, length, "");
  }

  /**
   * 描述：截取字符串到指定字节长度.
   *
   * @param str 文本
   * @param length 字节长度
   * @param dot 省略符号
   * @return 截取后的字符串
   */
  public static String cutString(String str, int length, String dot) {
    int strBLen = strlen(str, "GBK");
    if (strBLen <= length) {
      return str;
    }
    int temp = 0;
    StringBuffer sb = new StringBuffer(length);
    char[] ch = str.toCharArray();
    for (char c : ch) {
      sb.append(c);
      if (c > 256) {
        temp += 2;
      } else {
        temp += 1;
      }
      if (temp >= length) {
        if (dot != null) {
          sb.append(dot);
        }
        break;
      }
    }
    return sb.toString();
  }

  /**
   * 描述：截取字符串从第一个指定字符.
   *
   * @param str1 原文本
   * @param str2 指定字符
   * @param offset 偏移的索引
   * @return 截取后的字符串
   */
  public static String cutStringFromChar(String str1, String str2, int offset) {
    if (isEmpty(str1)) {
      return "";
    }
    int start = str1.indexOf(str2);
    if (start != -1) {
      if (str1.length() > start + offset) {
        return str1.substring(start + offset);
      }
    }
    return "";
  }

  /**
   * 描述：获取字节长度.
   *
   * @param str 文本
   * @param charset 字符集（GBK）
   * @return the int
   */
  public static int strlen(String str, String charset) {
    if (str == null || str.length() == 0) {
      return 0;
    }
    int length = 0;
    try {
      length = str.getBytes(charset).length;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return length;
  }

  /**
   * 获取大小的描述.
   *
   * @param size 字节个数
   * @return 大小的描述
   */
  public static String getSizeDesc(long size) {
    String suffix = "B";
    if (size >= 1024) {
      suffix = "K";
      size = size >> 10;
      if (size >= 1024) {
        suffix = "M";
        // size /= 1024;
        size = size >> 10;
        if (size >= 1024) {
          suffix = "G";
          size = size >> 10;
          // size /= 1024;
        }
      }
    }
    return size + suffix;
  }

  /**
   * 描述：ip地址转换为10进制数.
   *
   * @param ip the ip
   * @return the long
   */
  public static long ip2int(String ip) {
    ip = ip.replace(".", ",");
    String[] items = ip.split(",");
    return Long.valueOf(items[0]) << 24
        | Long.valueOf(items[1]) << 16
        | Long.valueOf(items[2]) << 8
        | Long.valueOf(items[3]);
  }

  /**
   * is null or its length is 0 or it is made by space 是null或它的长度为0或它是由空间
   *
   * <pre>
   * isBlank(null) = true;
   * isBlank(&quot;&quot;) = true;
   * isBlank(&quot;  &quot;) = true;
   * isBlank(&quot;a&quot;) = false;
   * isBlank(&quot;a &quot;) = false;
   * isBlank(&quot; a&quot;) = false;
   * isBlank(&quot;a b&quot;) = false;
   * </pre>
   *
   * @return if string is null or its size is 0 or it is made by space, return
   * true, else return false.
   */
  public static boolean isBlank(String str) {
    return (str == null || str.trim().length() == 0);
  }

  /**
   * is null or its length is 0 是null或它的长度为0
   *
   * <pre>
   * isEmpty(null) = true;
   * isEmpty(&quot;&quot;) = true;
   * isEmpty(&quot;  &quot;) = false;
   * </pre>
   *
   * @return if string is null or its size is 0, return true, else return
   * false.
   */
  public static boolean isEmpty(CharSequence str) {
    return (str == null || str.length() == 0);
  }

  /**
   * compare two string 比较两个字符串
   */
  public static boolean isEquals(String actual, String expected) {
    return ObjectUtils.isEquals(actual, expected);
  }

  /**
   * null Object to empty string 空对象为空字符串
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
   * capitalize first letter 大写首字母
   *
   * <pre>
   * capitalizeFirstLetter(null)     =   null;
   * capitalizeFirstLetter("")       =   "";
   * capitalizeFirstLetter("2ab")    =   "2ab"
   * capitalizeFirstLetter("a")      =   "A"
   * capitalizeFirstLetter("ab")     =   "Ab"
   * capitalizeFirstLetter("Abc")    =   "Abc"
   * </pre>
   */
  public static String capitalizeFirstLetter(String str) {
    if (isEmpty(str)) {
      return str;
    }

    char c = str.charAt(0);
    return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
        : new StringBuilder(str.length()).append(Character.toUpperCase(c))
            .append(str.substring(1))
            .toString();
  }

  /**
   * encoded in utf-8 用utf - 8编码
   *
   * <pre>
   * utf8Encode(null)        =   null
   * utf8Encode("")          =   "";
   * utf8Encode("aa")        =   "aa";
   * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
   * </pre>
   *
   * @throws UnsupportedEncodingException if an error occurs
   */
  public static String utf8Encode(String str) {
    if (!isEmpty(str) && str.getBytes().length != str.length()) {
      try {
        return URLEncoder.encode(str, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
      }
    }
    return str;
  }

  /**
   * encoded in utf-8, if exception, return defultReturn
   */
  public static String utf8Encode(String str, String defultReturn) {
    if (!isEmpty(str) && str.getBytes().length != str.length()) {
      try {
        return URLEncoder.encode(str, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        return defultReturn;
      }
    }
    return str;
  }

  /**
   * get innerHtml from href 得到innerHtml href
   *
   * <pre>
   * getHrefInnerHtml(null)                                  = ""
   * getHrefInnerHtml("")                                    = ""
   * getHrefInnerHtml("mp3")                                 = "mp3";
   * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a
   * innerHtml&lt;/a&gt;";
   * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
   * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
   * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
   * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
   * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
   * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
   * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
   * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        =
   * "innerHtml2";
   * </pre>
   *
   * @return <ul>
   * <li>if href is null, return ""</li>
   * <li>if not match regx, return source</li>
   * <li>return the last string that match regx</li>
   * </ul>
   */
  public static String getHrefInnerHtml(String href) {
    if (isEmpty(href)) {
      return "";
    }

    String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
    Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
    Matcher hrefMatcher = hrefPattern.matcher(href);
    if (hrefMatcher.matches()) {
      return hrefMatcher.group(1);
    }
    return href;
  }

  /**
   * process special char in html
   * 过程特殊字符在html中
   * <pre>
   * htmlEscapeCharsToString(null) = null;
   * htmlEscapeCharsToString("") = "";
   * htmlEscapeCharsToString("mp3") = "mp3";
   * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
   * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
   * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
   * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
   * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
   * </pre>
   */
  public static String htmlEscapeCharsToString(String source) {
    return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;", "<")
        .replaceAll("&gt;", ">")
        .replaceAll("&amp;", "&")
        .replaceAll("&quot;", "\"");
  }

  /**
   * transform half width char to full width char 半宽度字符转换为全宽字符
   *
   * <pre>
   * fullWidthToHalfWidth(null) = null;
   * fullWidthToHalfWidth("") = "";
   * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
   * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
   * </pre>
   */
  public static String fullWidthToHalfWidth(String s) {
    if (isEmpty(s)) {
      return s;
    }

    char[] source = s.toCharArray();
    for (int i = 0; i < source.length; i++) {
      if (source[i] == 12288) {
        source[i] = ' ';
        // } else if (source[i] == 12290) {
        // source[i] = '.';
      } else if (source[i] >= 65281 && source[i] <= 65374) {
        source[i] = (char) (source[i] - 65248);
      } else {
        source[i] = source[i];
      }
    }
    return new String(source);
  }

  /**
   * transform full width char to half width char 全宽字符转换为字符宽度的一半
   *
   * <pre>
   * halfWidthToFullWidth(null) = null;
   * halfWidthToFullWidth("") = "";
   * halfWidthToFullWidth(" ") = new String(new char[] {12288});
   * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
   * </pre>
   */
  public static String halfWidthToFullWidth(String s) {
    if (isEmpty(s)) {
      return s;
    }

    char[] source = s.toCharArray();
    for (int i = 0; i < source.length; i++) {
      if (source[i] == ' ') {
        source[i] = (char) 12288;
        // } else if (source[i] == '.') {
        // source[i] = (char)12290;
      } else if (source[i] >= 33 && source[i] <= 126) {
        source[i] = (char) (source[i] + 65248);
      } else {
        source[i] = source[i];
      }
    }
    return new String(source);
  }

  /**
   * 去掉给定String最后一个字符
   */
  public static String ridLastString(String str) {
    if (IsEmptyUtils.isEmpty(str)) {
      return "";
    }
    String string = str.substring(0, str.length() - 1);
    return string;
  }

  /**
   * 删除最后一个字符, 只会删除一个
   *
   * @param strList 字符列表
   * @param str 需要删除的字符串
   */
  public static String delLastStringInList(List<String> strList, String str) {
    if (strList != null && strList.size() > 0) {
      int strListLen = strList.size();
      String tempStr = null;

      for (int i = 0; i < strListLen; i++) {
        tempStr = strList.get(i);

        int lastIndexOf = str.lastIndexOf(tempStr);
        if (lastIndexOf != -1) {
          int strLen = str.length();
          int tempStrLen = tempStr.length();

          Integer tempLocation = tempStrLen + lastIndexOf;
          if (tempLocation == strLen) {
            str = str.substring(0, lastIndexOf);
          }
        }
      }
    }
    return str;
  }

  /**
   * 删除最后一个字符, 只会删除一个
   *
   * @param str 字符列表
   * @param str1 需要删除的字符串
   */
  public static String delLastString(String str, String str1) {
    if (!StringUtils.isEmpty(str)) {
      int lastIndexOf = str.lastIndexOf(str1);
      if (lastIndexOf != -1) {
        int strLen = str.length();
        int tempStrLen = str1.length();

        Integer tempLocation = tempStrLen + lastIndexOf;
        if (tempLocation == strLen) {
          str = str.substring(0, lastIndexOf);
        }
      }
    }
    return str;
  }

  /**
   * 查找字符2在字符1的中开始位置和结束位置
   */
  public static Integer[] getIndexInStr(String str, String str2) {
    int indexOf = str.indexOf(str2);

    Integer[] location = new Integer[2];
    location[0] = indexOf;
    location[1] = indexOf + str2.length();
    return location;
  }
}
