package com.imm.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.ArrayUtils;
import com.google.common.collect.Maps;
import com.imm.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author rjyx_huxinsheng
 */
public class ValueHelper {

    private static Logger logger = LoggerFactory.getLogger(ValueHelper.class);

    public static boolean paramRequiredCheck(Map<String, String> param, String... paramNames) {
        if (CollectionUtils.isEmpty(param)) {
            logger.warn("传入参数为空:{}", param);
            return false;
        }
        if (ArrayUtils.isEmpty(paramNames)) {
            return true;
        }
        for (String paramName : paramNames) {
            if (!param.containsKey(paramName)) {
                logger.warn("参数名:{},传入值空。传入参数:{}", paramName, param);
                break;
            }
            String paramValue = param.get(paramName);
            if (StringUtils.isEmpty(paramValue)) {
                logger.warn("参数名:{},传入值空。传入参数:{}", paramName, param);
                break;
            }
        }
        return true;
    }

    public static List<String> checkObjFieldIsNull(Object obj, List<String> nullFields) {
        List<String> s = new ArrayList<>();
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (nullFields.contains(f.getName())) {
                continue;
            }
            try {
                if (f.get(obj) == null) {
                    s.add(f.getName());
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
                throw new BizException(e.getMessage());
            }
        }
        return s;
    }

    /**
     * 拼接请求参数
     *
     * @param params
     * @param isSort      是否Asii排序
     * @param isUrlEncode 是否编码
     * @return
     */
    public static void createLinkString(Map<String, Object> params, boolean isSort, boolean isUrlEncode, StringJoiner sb) {
        List<String> keys = new ArrayList<>(params.keySet());
        if (isSort) {
            Collections.sort(keys);
        }
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            if (value instanceof ArrayList) {
                List data = (List) value;
                if (!CollectionUtils.isEmpty(data)) {
                    StringJoiner osb = new StringJoiner(",");
                    for (Object datum : data) {
                        if (datum instanceof String) {
                            osb.add((String) datum);
                        } else if (datum instanceof LinkedHashMap) {
                            createLinkString((Map<String, Object>) datum, isSort, isUrlEncode, osb);
                        }
                    }
                    sb.add(key + "={" + osb.toString() + "}");
                }

            } else if (value instanceof LinkedHashMap) {
                StringJoiner osb = new StringJoiner(",");
                createLinkString((Map<String, Object>) value, isSort, isUrlEncode, osb);
                sb.add(key + "={" + osb.toString() + "}");
            } else {
                //是否编码
                if (isUrlEncode) {
                    value = URLEncoder.encode((String) value);
                }
                //拼接时，不包括最后一个&字符
                sb.add(key + "=" + value);
            }
        }
    }

    public static Map<String, String> filterEmpty(Map<String, String> params) {
        Map<String, String> result = new HashMap<String, String>();
        if (params == null || params.size() <= 0) {
            return result;
        }
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null || "".equals(value)
                    || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    public static Map<String, String> parseURLParams(String input) {
        Map<String, String> result = new HashMap<String, String>();
        String[] array = input.split("&");
        for (int i = 0; i < array.length; i++) {
            int nPos = array[i].indexOf("=");
            int nLen = array[i].length();
            String key = array[i].substring(0, nPos);
            String value = array[i].substring(nPos + 1, nLen);
            result.put(key, value);
        }
        return result;
    }

    public static Map<String, Object> getDataMap(Object obj) {
        Map<String, Object> dataMap = Maps.newHashMapWithExpectedSize(7);
        Class c;
        try {
            c = Class.forName(obj.getClass().getName());
            Method[] methods = c.getMethods();
            for (int i = 0, l = methods.length; i < l; i++) {
                String method = methods[i].getName();
                if (method.startsWith("get")) {
                    Object value = methods[i].invoke(obj);
                    if (value != null) {
                        //处理自定义的对象类型
                        if (value.getClass().getClassLoader() != null) {
                            getDataMap(value);
                        }
                        String key = method.substring(3);
                        key = key.substring(0, 1).toLowerCase() + key.substring(1);
                        dataMap.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    private JSONObject mergeObjectValue(Object s, Object t) {
        JSONObject target = JSON.parseObject(JSON.toJSONString(t), JSONObject.class);
        if (null != t && null != s) {
            JSONObject source = JSON.parseObject(JSON.toJSONString(s), JSONObject.class);
            target.putAll(source);
        }
        return target;
    }
}
