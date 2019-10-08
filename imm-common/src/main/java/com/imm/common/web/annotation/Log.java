package com.imm.common.web.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author rjyx_huxinsheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String value() default "操作日志";
}
