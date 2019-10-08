package com.imm.common.ds;

/**
 * 作用：
 * 1、保存一个线程安全的DatabaseType容器
 *
 * @author rjyx_huxinsheng
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}
