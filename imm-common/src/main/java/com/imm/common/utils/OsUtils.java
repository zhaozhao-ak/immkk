package com.imm.common.utils;


import java.io.*;
import java.util.Scanner;

/**
 * 操作系统工具类
 *
 * @author rjyx_huxinsheng
 */
public class OsUtils {

    public static void main(String[] args) {
        try {
            getMacAddressByWindows();
            getHardDiskSnByWindows();
            getCpuSnByWindows();
            getHardDiskSizeByWindows();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static String executeLinuxCmd(String cmd) {
        try {
            System.out.println("got cmd job : " + cmd);
            Runtime run = Runtime.getRuntime();
            Process process;
            process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }

            in.close();
            process.destroy();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param cmd    命令语句
     * @param record 要查看的字段
     * @param symbol 分隔符
     * @return
     */
    public static String getSerialNumber(String cmd, String record, String symbol) {
        String execResult = executeLinuxCmd(cmd);
        String[] infos = execResult.split("\n");

        for (String info : infos) {
            info = info.trim();
            if (info.indexOf(record) != -1) {
                info.replace(" ", "");
                String[] sn = info.split(symbol);
                return sn[1];
            }
        }

        return null;
    }


    private static String getMacAddressByWindows() {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));

            String line;
            int index = -1;
            while ((line = br.readLine()) != null) {
                index = line.toLowerCase().indexOf("物理地址");
                // 找到了
                if (index >= 0) {
                    index = line.indexOf(":");
                    if (index >= 0) {
                        result = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            result = null;
        }
        return result;
    }

    private static String getHardDiskSnByWindows() {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c dir C:");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("卷的序列号是 ")) {
                    result = line.substring(line.indexOf("卷的序列号是 ") + "卷的序列号是 ".length());
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            result = null;
        }
        return result;
    }

    private static long getHardDiskSizeByWindows() {
        File[] roots = File.listRoots();
        long size = 0L;
        for (File file : roots) {
            size += file.getTotalSpace();
        }
        return size;
    }

    private static String getCpuSnByWindows() {
        try {
            Process process = Runtime.getRuntime().exec(
                    new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            return serial;
        } catch (IOException e) {
            return null;
        }
    }
}
