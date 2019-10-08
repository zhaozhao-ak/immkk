package com.imm.common.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rjyx_huxinsheng
 */
public class PinyinUtils {
    /**
     * 获得汉语拼音首字母
     *
     * @param chines 汉字
     * @return
     */


    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     *
     * @param inputString 汉字
     * @return
     */


    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */



    /**
     * 汉字转换位汉语拼音首字母，英文字符不变,只取第一个中文
     */


    /**
     * 清理特殊字符以便得到
     *
     * @param chines
     * @return
     */
    public static String cleanChar(String chines) {
        // 正则去掉所有字符操作
        chines = chines.replaceAll("[\\p{Punct}\\p{Space}]+", "");
        // 正则表达式去掉所有中文的特殊符号
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}<>《》【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(chines);
        chines = matcher.replaceAll("").trim();
        return chines;
    }

    public static void main(String[] args) {
        String s = "我是mg";
    }
}
