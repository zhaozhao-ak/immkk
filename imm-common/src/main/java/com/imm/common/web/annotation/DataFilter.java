package com.imm.common.web.annotation;

import java.lang.annotation.*;

/**
 * 数据过滤
 *
 * @author rjyx_huxinsheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**
     * sql中数据创建用户（通常传入creator）的别名
     */
    String userAlias() default "";

    /**
     * sql中数据institutionCode的别名
     */
    String institutionAlias() default "";
    /**
     * sql中数据orgCode的别名
     */
    String orgCodeAlias() default "";

    /**
     * true：没有医疗机构数据权限，也能查询本人数据
     */
    boolean self() default true;
}
