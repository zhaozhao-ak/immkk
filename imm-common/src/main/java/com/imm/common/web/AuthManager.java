package com.imm.common.web;

import org.springframework.stereotype.Component;

/**
 * @author rjyx_huxinsheng
 */
@Component
public class AuthManager {

    public AuthManager() {
    }

    public static void setCurrent(AuthInfo authInfo) {
        HttpSession session = HttpContext.getHttpSession();
        session.setAttribute("authSessionKey", authInfo);
    }

    public static AuthInfo getCurrent() {
        HttpSession session = HttpContext.getHttpSession();
        Object value = session.getAttribute("authSessionKey");
        return (AuthInfo) value;
    }
}
