package com.imm.common.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Set;

/**
 * @author rjyx_huxinsheng
 */
public class CommonUtil {
    /**
     * 分割字符串进SET
     */
    public static Set<String> split(String str) {
        return split(str, ",");
    }

    /**
     * 分割字符串进SET
     */
    public static Set<String> split(String str, String separator) {
        Set<String> set = Sets.newLinkedHashSet();
        if (Strings.isNullOrEmpty(str)) {
            return set;
        }
        for (String s : str.split(separator)) {
            set.add(s);
        }
        return set;
    }

    /**
     * 逗号连接字符串
     */
    public static String join(Collection<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }



    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        String cnStr = "张大宝";
    }

    public static String readfile(String filePath) {
        StringBuilder fileContent = new StringBuilder();
        try {
            File f = new File(filePath);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line);
                }
                read.close();
            }
        } catch (Exception e) {
            return null;
        }
        return fileContent.toString();
    }

    public static String getBody(String val) {
        String start = "<body>";
        String end = "</body>";
        int s = val.indexOf(start) + start.length();
        int e = val.indexOf(end);
        return val.substring(s, e);
    }
}
