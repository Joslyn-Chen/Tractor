package com.cajan.commonlib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName ：ListMapUtils
 * Description : ListMap工具
 * Created : Cajan
 * Time : 2015/8/26
 * Version : 1.0
 */
public class ListMapUtils {

  /**
   * 将ID拼成的字符串分割
   *
   * @param idStr 格式为 1,2,3...的字符串
   * @param splitWord 分割字符串,可为空(null),为空时,默认为",", 工具类里内置了几种分割符
   * @return 返回一个分割好的list
   * @throws Exception
   */
  public static List<String> splitStrToList(String idStr, String splitWord) throws Exception {
    if (IsEmptyUtils.isEmpty(splitWord)) {
      splitWord = ConstantUtils.SPLIT_COMMA;
    }
    List<String> asList = null;
    String[] split = null;
    try {
      split = idStr.split(splitWord);
      asList = Arrays.asList(split);
    } catch (Exception e) {
      L.d("拆分字符串时出错", e.toString());
      throw e;
    } finally {
      split = null;
    }
    return asList;
  }

  /**
   * 将ID拼成的字符串分割
   *
   * @param idStr 格式为 1,2,3...的字符串
   * @param splitWord 分割字符串,可为空(null),为空时,默认为",", 工具类里内置了几种分割符
   * @return 返回一个分割好的list
   * @throws Exception
   */
  public static List<Integer> splitToListForNum(String idStr, String splitWord) throws Exception {
    if (IsEmptyUtils.isEmpty(splitWord)) {
      splitWord = ConstantUtils.SPLIT_COMMA;
    }
    List<String> asList = null;
    List<Integer> reList = null;
    String[] split = null;
    try {
      reList = new ArrayList<Integer>();
      split = idStr.split(splitWord);
      asList = Arrays.asList(split);
      for (int i = 0; i < asList.size(); i++) {
        reList.add(Integer.parseInt(asList.get(i)));
      }
    } catch (Exception e) {
      L.d("拆分字符串时出错", e.toString());
      throw e;
    } finally {
      split = null;
    }
    return reList;
  }

  /**
   * 将LIST以分隔符隔开,LIST只能有一个值,如LIST.ADD(1);
   *
   * @throws Exception
   */
  public static String listToStringWithSperate(List<Object> list, String sperate) throws Exception {
    Integer listLen = list.size();
    StringBuffer strBuf = new StringBuffer();
    if (!IsEmptyUtils.isEmpty(list) && listLen > 0) {
      for (int i = 0; i < listLen; i++) {
        strBuf.append(list.get(i)).append(sperate);
      }
    }
    return strBuf.substring(0, strBuf.length() - 1).toString();
  }

  /**
   * 将LIST以分隔符隔开,LIST只能有一个值,如LIST.ADD(1);
   *
   * @throws Exception
   */
  public static String listStrToStringWithSperate(List<String> list, String sperate)
      throws Exception {
    Integer listLen = list.size();
    StringBuffer strBuf = new StringBuffer();
    if (!IsEmptyUtils.isEmpty(list) && listLen > 0) {
      for (int i = 0; i < listLen; i++) {
        strBuf.append(list.get(i)).append(sperate);
      }
    }
    return strBuf.substring(0, strBuf.length() - 1).toString();
  }

  /**
   * 将LIST(MAP)中的某一项数据转换提取为LIST
   *
   * @param dataList 需要提取的LIST(MAP)
   * @param field 需要提取的字段
   * @throws Exception
   */
  public static List<Object> getSingleListFromListMap(List<Map<String, Object>> dataList,
      String field) throws Exception {
    Integer dataListLen = dataList.size();
    List<Object> params = new ArrayList<Object>();
    for (int i = 0; i < dataListLen; i++) {
      Object object = dataList.get(i).get(field);
      params.add(object);
    }
    return params;
  }

  /**
   * 从实体类中提取单个值
   *
   * @throws Exception
   */
  public static List<Object> getSingleListFromEntity(List<? extends Object> dataList, String field)
      throws Exception {
    List<Object> reList = new ArrayList<Object>();
    int dataListLen = dataList.size();
    for (int i = 0; i < dataListLen; i++) {
      Object object = dataList.get(i);
      Method[] declaredMethods = object.getClass().getDeclaredMethods();
      int length = declaredMethods.length;
      for (int j = 0; j < length; j++) {
        declaredMethods[j].setAccessible(true);
        String name = declaredMethods[j].getName();
        int indexOf = name.indexOf("get") + 3;
        name = name.toLowerCase().substring(indexOf, name.length());
        if (field.toLowerCase().equals(name.toLowerCase())) {
          Object defaultValue = declaredMethods[j].invoke(object);
          reList.add(defaultValue);
        }
      }
    }
    return reList;
  }

  /**
   * 将所有不是list(integer)的值,转化为list(integer),请确保list(object)的object可转化为integer
   *
   * @throws Exception
   */
  public static List<Integer> changeListObjectToListInteger(List<? extends Object> dataList)
      throws Exception {
    List<Integer> reList = new ArrayList<Integer>();
    int dataListLen = dataList.size();
    for (int i = 0; i < dataListLen; i++) {
      String value = dataList.get(i).toString();
      reList.add(Integer.parseInt(value.toString()));
    }
    return reList;
  }

  /**
   * 将所有LISTOBJECT的值转化为LISTSTRING
   *
   * @throws Exception
   */
  public static List<String> changeListObjectToListStr(List<? extends Object> dataList)
      throws Exception {
    List<String> reList = new ArrayList<String>();
    int dataListLen = dataList.size();
    for (int i = 0; i < dataListLen; i++) {
      String value = dataList.get(i).toString();
      reList.add(value);
    }
    return reList;
  }

  /**
   * 将LIST(MAP)中的某一项数据转换提取为LIST
   *
   * @param dataList 需要提取的LIST(MAP)
   * @param field 需要提取的字段
   * @throws Exception
   */
  public static List<String> getSingleListFromListMapToString(List<Map<String, Object>> dataList,
      String field) throws Exception {
    Integer dataListLen = dataList.size();
    List<String> params = new ArrayList<String>();
    for (int i = 0; i < dataListLen; i++) {
      String object = dataList.get(i).get(field).toString();
      params.add(object);
    }
    return params;
  }

  /**
   * 数组转字符串
   *
   * @throws Exception
   */
  public static String arrayToString(Object[] objs, String sperate) throws Exception {
    Integer listLen = objs.length;
    StringBuffer strBuf = new StringBuffer();
    if (!IsEmptyUtils.isEmpty(objs) && listLen > 0) {
      for (int i = 0; i < listLen; i++) {
        strBuf.append(objs[i]).append(sperate);
      }
    }
    return strBuf.substring(0, strBuf.length() - 1).toString();
  }

  /**
   * 将实体(Entity)转换成MAP
   *
   * @return 返回实体字段, 值组成的MAP, 如果实体为空,则直接返回NULL;
   */
  public static Map<String, Object> convertEntityToMap(Object entity) throws Exception {
    Map<String, Object> reMap = new HashMap<String, Object>();
    if (entity == null) {
      return null;    // 为空时直接返回
    }
    Method[] declaredMethods = entity.getClass().getDeclaredMethods();    // 获取方法列表
    Integer methodsLen = null;

    String key = null;
    Object value = null;
    try {
      methodsLen = declaredMethods.length;
      // 将所有get方法的字段取出,放入MAP的KEY中, 将get方法取出的值放入到value中;
      for (int i = 0; i < methodsLen; i++) {
        if (declaredMethods[i].getName().indexOf("get") == 0) {
          key = declaredMethods[i].getName().substring(3).toLowerCase();
          value = declaredMethods[i].invoke(entity, new Object[0]);
          if (!IsEmptyUtils.isEmpty(value)) {
            if (value instanceof Date) {
              reMap.put(key, DateUtils.date2String((Date) value, "yyyy-MM-dd"));
            } else {
              reMap.put(key, value);
            }
          } else {
            reMap.put(key, value);
          }
        }
      }
    } catch (Exception e) {

      throw e;
    } finally {
      ObjectUtils.offObject(declaredMethods, methodsLen, key, value);
    }
    return reMap;
  }

  /**
   * 将MAP中的值设置到相应的实体中, TransactionData的KEY值必须为标签,
   *
   * @param srcMap MAP数据,从报文转换而来,为Map<标签,值>
   * @param entityClazz 需要将值设置到哪一个实体
   * @return 返回一个需要的实体对象
   * @throws Exception
   */
  public static Object setEntityValue(Map<String, Object> srcMap, Class<?> entityClazz)
      throws Exception {
    HashMap<String, Object> dstMap = null; // 目标数据
    Map<Object, Object> tagEntityMap = null;

    // 标签
    Set<Map.Entry<String, Object>> entrySet = null;
    Iterator<Map.Entry<String, Object>> iterator = null;

    Field[] declaredFields = null;
    Object instance = null;
    try {
      dstMap = new HashMap<String, Object>(); // 目标数据
      dstMap.putAll(srcMap);

      // 取得字段
      instance = entityClazz.newInstance();
      declaredFields = instance.getClass().getDeclaredFields();
      int fieldsLen = declaredFields.length;
      for (int i = 0; i < fieldsLen; i++) {
        declaredFields[i].setAccessible(true);
        setFiledVal(declaredFields[i], instance, dstMap);
      }
    } catch (Exception e) {
      throw e;
    } finally {
      ObjectUtils.offObject(srcMap, dstMap, tagEntityMap, entrySet, iterator, declaredFields);
    }
    return instance;
  }

  /**
   * 将值设置到实体对应的字段中 (实体使用的方法)
   *
   * @param field 字段值
   * @param instance 实体
   * @param dstMap 字段,值MAP
   * @throws Exception
   */
  public static void setFiledVal(Field field, Object instance, Map<String, Object> dstMap)
      throws Exception {
    String fieldName = field.getName();
    /**
     * 常用包装类型 Byte, Short, Integer, Long, Float, Double, String, Boolean,
     * Date
     */
    String simpleName = field.getType().getSimpleName();
    int fieldType = Types.getNumType(simpleName);
    if (dstMap.containsKey(fieldName.toLowerCase())) {
      Object dstObj = dstMap.get(fieldName.toLowerCase());
      String dstStr = null;

      if (dstObj != null) {
        dstStr = dstObj.toString();
        Boolean notNullStr = isStringNull(dstStr);
        if (notNullStr) {
          dstObj = null;
        }
      }
      if (dstObj == null) {
        field.set(instance, dstObj);
      } else {
        switch (fieldType) {
          case Types.BYTE:
          case Types.SHORT:
          case Types.INTEGER:
            field.set(instance, Integer.parseInt(dstObj.toString()));
            break;
          case Types.LONG:
            field.set(instance, Long.parseLong(dstObj.toString()));
            break;
          case Types.FLOAT:
            field.set(instance, Float.parseFloat(dstObj.toString()));
            break;
          case Types.DOUBLE:
            field.set(instance, Double.parseDouble(dstObj.toString()));
            break;
          case Types.BOOLEAN:
            field.set(instance, Boolean.parseBoolean(dstObj.toString()));
            break;
          case Types.DATE:
            field.set(instance, DateUtils.str2Date(dstObj.toString()));
            break;
          default:
            field.set(instance, dstMap.get(fieldName.toLowerCase()));
            break;
        }
      }
    }
  }

  /**
   * 将ListObj全部转化为ListMap
   *
   * @param listObj list(entity).如果不是实体,会出错
   * @return 返回listmap
   * @throws Exception
   */
  public static List<Map<String, Object>> convertListEntityToListMap(List<? extends Object> listObj)
      throws Exception {
    if (IsEmptyUtils.isEmpty(listObj)) {
      L.d("数据为空,将list初始化为空");
      listObj = new ArrayList<Object>();
    }

    List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();

    int listObjLen = listObj.size();
    for (int i = 0; i < listObjLen; i++) {
      Object object = listObj.get(i);
      Map<String, Object> convertEntityToMap = ListMapUtils.convertEntityToMap(object);
      reList.add(convertEntityToMap);
    }

    return reList;
  }

  /**
   * 获取包含这些值的MAP, 寻找一个LIST中,唯一包含这些值的MAP
   * <strong>注:这个MAP要能区别其它的MAP,否则查找出来的值不准确</strong>
   *
   * @param dataList 需要查找的LISTMAP
   * @param params 需要查找的LISTMAP包含的
   * @return 返回找到的第一个包含这些值的所有MAP
   * @throws Exception
   */
  public static List<Map<String, Object>> getListMapFromWithFixV(List<Map<String, Object>> dataList,
      Map<String, Object> params) throws Exception {
    List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();

    if (dataList == null || (dataList != null && dataList.size() < 0)) {
      return reList;
    }

    int isAllHave = 0;    // 包含的项数
    int dataListLen = dataList.size();

    Map<String, Object> dataMap = null;
    for (int i = 0; i < dataListLen; i++) {
      dataMap = dataList.get(i);
      isAllHave = 0;    // 包含的项数

      Set<String> keySet = params.keySet();
      Iterator<String> iterator2 = keySet.iterator();
      while (iterator2.hasNext()) {
        String key = iterator2.next();

        if (dataMap.containsKey(key)) {
          Object obj = dataMap.get(key);
          Object obj2 = params.get(key);

          if (obj == null && obj2 == null) {
            isAllHave++;
          } else {
            if ((obj == null && obj2 != null) || (obj != null && obj2 == null)) {
              // 其它一个为空,跳过
            } else {
              String object = obj.toString();
              String object2 = obj2.toString();

              if (object.equals(object2)) {
                isAllHave++;
              }
            }
          }
        }
      }

      if (isAllHave == params.size()) {
        reList.add(dataMap);
      }
    }

    return reList;
  }

  /**
   * 获取此MAP所在的位置索引值;
   * <strong>注:这个MAP要能区别其它的MAP,否则查找出来的值不准确</strong>
   *
   * @param dataList 需要查找的LISTMAP
   * @param params 需要查找的LISTMAP包含的
   * @return 返回索引值
   * @throws Exception
   */
  public static Integer getIndexDataInListMap(List<Map<String, Object>> dataList,
      Map<String, Object> params) throws Exception {
    if (dataList == null || (dataList != null && dataList.size() < 0)) {
      return null;
    }

    int isAllHave = 0;    // 包含的项数
    int dataListLen = dataList.size();

    Map<String, Object> dataMap = null;
    for (int i = 0; i < dataListLen; i++) {
      dataMap = dataList.get(i);
      isAllHave = 0;    // 包含的项数

      Set<String> keySet = params.keySet();
      Iterator<String> iterator2 = keySet.iterator();
      while (iterator2.hasNext()) {
        String key = iterator2.next();

        if (dataMap.containsKey(key)) {
          Object obj = dataMap.get(key);
          Object obj2 = params.get(key);

          if (obj == null && obj2 == null) {
            isAllHave++;
          } else {
            if ((obj == null && obj2 != null) || (obj != null && obj2 == null)) {
              // 其它一个为空,跳过
            } else {
              String object = obj.toString();
              String object2 = obj2.toString();

              if (object.equals(object2)) {
                isAllHave++;
              }
            }
          }
        }
      }

      if (isAllHave == params.size()) {
        return i;
      }
    }

    return null;
  }

  /**
   * 判断某些值是否存在于listMap中
   *
   * @throws Exception
   */
  public static Boolean isExistsInListMap(List<Map<String, Object>> dataList,
      Map<String, Object> params) throws Exception {
    if (dataList == null || (dataList != null && dataList.size() < 0)) {
      return false;
    }

    int isAllHave = 0;    // 包含的项数
    int dataListLen = dataList.size();

    Map<String, Object> dataMap = null;
    for (int i = 0; i < dataListLen; i++) {
      dataMap = dataList.get(i);
      isAllHave = 0;    // 包含的项数

      Set<String> keySet = params.keySet();
      Iterator<String> iterator2 = keySet.iterator();
      while (iterator2.hasNext()) {
        String key = iterator2.next();

        if (dataMap.containsKey(key)) {
          Object obj = dataMap.get(key);
          Object obj2 = params.get(key);

          if (obj == null && obj2 == null) {
            isAllHave++;
          } else {
            if ((obj == null && obj2 != null) || (obj != null && obj2 == null)) {
              // 其它一个为空,跳过
            } else {
              String object = obj.toString();
              String object2 = obj2.toString();

              if (object.equals(object2)) {
                isAllHave++;
              }
            }
          }
        }
      }

      if (isAllHave == params.size()) {
        return true;
      }
    }

    return false;
  }

  /**
   * LISTMAP排序,仅针对某一个字段,且这个字段必须可转换为数字
   */
  public static void sortListMap(List<Map<String, Object>> srcList, final String sortField)
      throws Exception {

    Collections.sort(srcList, new Comparator<Map<?, ?>>() {
      int num1 = 0;
      int num = 0;

      public int compare(Map<?, ?> map1, Map<?, ?> map2) {
        Object sortField2 = map2.get(sortField);
        Object sortField1 = map1.get(sortField);

        if (!IsEmptyUtils.isEmpty(sortField2)) {
          num1 = Integer.parseInt(sortField2.toString());
        }

        if (!IsEmptyUtils.isEmpty(sortField1)) {
          num = Integer.parseInt(sortField1.toString());
        }
        return num - num1;
      }
    });
  }

  /**
   * 将LISTMAP转换为jqgrid treegrid能使用的树, 里面暂时有些代码是死的
   *
   * @param srcList 需要转换的listmap
   * @param paramMap 转换的初始化, 一般初始化level的开始, parentid的开始
   * @param isRank 是否排序,是的时候sortField必须有值,且正确
   * @param sortField 排序的字段
   * @return 返回组装好的树结构
   * @throws Exception
   */
  public static List<Map<String, Object>> listmapToTree(List<Map<String, Object>> srcList,
      Map<String, Object> paramMap, Boolean isRank, String sortField) throws Exception {
    List<Map<String, Object>> dstList = new ArrayList<Map<String, Object>>();

    if (IsEmptyUtils.isEmpty(srcList)) {
      L.e("源[srcList]为空,数据直接返回不进行处理");
      return srcList;
    }

    if (IsEmptyUtils.isEmpty(isRank)) {
      isRank = false;
    }

    // 提取出所有的父级
    if (IsEmptyUtils.isEmpty(paramMap)) {
      paramMap = new HashMap<String, Object>();
      paramMap.put("level", 0);
      paramMap.put("parentid", null);
    }

    List<Map<String, Object>> parentList = ListMapUtils.getListMapFromWithFixV(srcList, paramMap);

    if (IsEmptyUtils.isEmpty(parentList)) {
      L.e("父级[parentList]没有任何项");
      return parentList;
    }

    // 为true时,排序
    if (isRank) {
      ListMapUtils.sortListMap(parentList, sortField);
    }

    int parentListLen = parentList.size();
    Map<String, Object> map = null;
    for (int i = 0; i < parentListLen; i++) {
      map = parentList.get(i);
      Object id = map.get("uuid");
      Object level = map.get("level");
      Object parentid = map.get("parentid");

      Map<String, Object> paramMap1 = new HashMap<String, Object>();
      paramMap1.put("parentid", id);
      paramMap1.put("level", (Integer.parseInt(level.toString()) + 1));
      Boolean exists = ListMapUtils.isExistsInListMap(srcList, paramMap1);

      map.put("expanded", "false");
      map.put("loaded", "true");
      map.put("parent", parentid);
      if (exists) {
        map.put("isLeaf", "false");
        dstList.add(map);
        List<Map<String, Object>> listmapToTree = listmapToTree(srcList, paramMap1, true, "rank");
        dstList.addAll(listmapToTree);
      } else {
        map.put("isLeaf", "true");
        dstList.add(map);
      }
    }
    return dstList;
  }

  /**
   * 提取listMap中的唯一值
   *
   * @param dataList 需要提取的listmpa
   * @param params 提取的参数,即与map匹配的数目
   * @return 返回Map, 则是唯一MAP, 如果多于1个或者没有则返回空
   * @throws Exception
   */
  public static Map<String, Object> getMapOneFromListMap(List<Map<String, Object>> dataList,
      Map<String, Object> params) throws Exception {
    List<Map<String, Object>> reList = ListMapUtils.getListMapFromWithFixV(dataList, params);
    if (!IsEmptyUtils.isEmpty(reList) && reList.size() == 1) {
      return reList.get(0);
    }
    return null;
  }

  /**
   * 改变listmap中的时间格式 为时间格式 yyyy-MM-dd
   *
   * @param dataList 原始数据
   * @param params MAP对象, KEY为需要更改的字段, 值为需要修改的时间格式
   * @throws Exception
   */
  public static List<Map<String, Object>> changeTimeFormatInlistMap(
      List<Map<String, Object>> dataList, Map<String, Object> params) throws Exception {
    int dataListLen = dataList.size();
    Map<String, Object> dataListMap = null;
    for (int i = 0; i < dataListLen; i++) {
      dataListMap = dataList.get(i);

      Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, Object> next = iterator.next();
        String key = next.getKey();
        String timeFormat = next.getValue().toString();

        if (dataListMap.containsKey(key)) {
          Object dataObj = dataListMap.get(key);
          if (dataObj != null) {
            String timeStr = dataObj.toString();
            if (timeStr.length() > 11) {
              Date str2Date = DateUtils.str2Date(timeStr, DateUtils.YYYY_MM_DD_HH_MM_SS);

              timeStr = DateUtils.date2String(str2Date, timeFormat);
              dataListMap.put(key, timeStr);
            }
          }
        }
      }
    }
    return dataList;
  }

  /**
   * 将String（json）转换为Map
   *
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
    /*public static Map<String, Object> stringToMap(String str) throws Exception {
        if (!IsEmptyUtils.isEmpty(str)) {
            Map<String, Object> dataMap = JsonUtil.JsonToObject(str, Map.class);
            return dataMap;
        }
        return null;
    }*/

  /**
   * @param str
   * @return true is null or ''
   */ public static Boolean isStringNull(String str) {
    if (str == null || str != null && "".equals(str.trim()) || str.trim().length() == 0) {
      return true;
    }
    return false;
  }
}
