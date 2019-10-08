package com.imm.common.utils;

import java.lang.Character.UnicodeBlock;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrUtils {
    public StrUtils() {
    }

    public static boolean isChinese(char c) {
        UnicodeBlock ub = UnicodeBlock.of(c);
        return ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == UnicodeBlock.GENERAL_PUNCTUATION;
    }

    public static String trimMessy(String str) {
        StringBuffer buff = new StringBuffer(str.length());

        for(int i = 0; i < str.length(); ++i) {
            char character = str.charAt(i);
            if (character >= 0 && character <= 127) {
                buff.append(character);
            } else if (isChinese(character)) {
                buff.append(character);
            }
        }

        return buff.toString();
    }

    public static String bytes2Hex(byte[] bytes) {
        char[] res = new char[bytes.length * 2];
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int i = 0;

        for(int var4 = 0; i < bytes.length; ++i) {
            res[var4++] = hexDigits[bytes[i] >>> 4 & 15];
            res[var4++] = hexDigits[bytes[i] & 15];
        }

        return new String(res);
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static String firstToUpperCase(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            char[] array = str.toCharArray();
            array[0] = Character.toUpperCase(array[0]);
            return new String(array);
        }
    }

    public static String firstToLowerCase(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            char[] array = str.toCharArray();
            array[0] = Character.toLowerCase(array[0]);
            return new String(array);
        }
    }

    public static boolean equals(String value1, String value2) {
        if (value1 == null) {
            return value2 == null;
        } else {
            return value1.equals(value2);
        }
    }

    public static String getNonceStr() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int maxPos = str.length();
        String noceStr = "";

        for(int i = 0; i < 32; ++i) {
            noceStr = noceStr + String.valueOf(str.charAt((int)Math.floor(Math.random() * (double)maxPos)));
        }

        return noceStr;
    }

    public static String null2Blank(Object o) {
        String result = "";
        if (o != null && !"null".equals(o)) {
            if (!(o instanceof Integer) && !(o instanceof Double)) {
                if (!(o instanceof Timestamp) && !(o instanceof Date)) {
                    result = (String)o;
                } else {
                    Timestamp ts = (Timestamp)o;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                    try {
                        result = sdf.format(ts);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }
            } else {
                result = String.valueOf(o);
            }
        } else {
            result = "";
        }

        return result;
    }
}
