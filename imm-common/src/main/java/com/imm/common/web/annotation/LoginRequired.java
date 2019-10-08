package com.imm.common.web.annotation;

import java.lang.annotation.*;

/**
 * 类Login的功能描述:
 * app登录效验
 *
 * @author rjyx_huxinsheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
