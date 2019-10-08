package com.imm.common.web.session;


import com.imm.common.web.HttpSession;

/**
 * @author rjyx_huxinsheng
 */
public class SessionTomcatImpl extends HttpSession {
    private javax.servlet.http.HttpSession session;

    public SessionTomcatImpl(javax.servlet.http.HttpSession session) {
        this.session = session;
    }

    @Override
    public Object getAttribute(String key) {
        return this.session.getAttribute(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        this.session.setAttribute(key, value);
    }

    @Override
    public void remove(String key) {
        this.session.removeAttribute(key);
    }

    @Override
    public void abandon() {
        this.session.invalidate();
    }
}
