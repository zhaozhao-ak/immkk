package com.imm.common.web.annotation;

import java.lang.annotation.*;

/**
 * 版本检查
 *
 * @author rjyx_huxinsheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerCheck {
    String uri() default "";
}
