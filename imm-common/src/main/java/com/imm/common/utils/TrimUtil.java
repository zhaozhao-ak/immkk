package com.imm.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author rjyx_huxinsheng
 */
public class TrimUtil {
    /**
     * 将对象中的参数进行去空格
     *
     * @param obj
     * @return
     */
    public static Object trimObject(Object obj) {
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            if ("class java.lang.String".equals(type.toString())) {
                String fieldName = field.getName();
                Object value = getValueByFieldName(fieldName, obj);
                if (value != null) {
                    //去除前后端空格
                    doSetValue(obj, type, fieldName, value.toString().trim());
                    //doSetValue(obj, type, fieldName, value.toString().replace(" ", ""));//去除所有空格
                }
            }

        }
        return obj;

    }

    /**
     * 根据属性名获取该类此属性的值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private static Object getValueByFieldName(String fieldName, Object object) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        try {
            Method method = object.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(object, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 将处理过的值放入对象中
     *
     * @param obj
     * @param classType
     * @param fieldName
     * @param value
     */
    private static void doSetValue(Object obj, Class<?> classType, String fieldName, String value) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String setter = "set" + firstLetter + fieldName.substring(1);
        value = "".equals(value) ? null : value;
        try {
            Method method = obj.getClass().getMethod(setter, new Class[]{classType});
            method.invoke(obj, new Object[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
