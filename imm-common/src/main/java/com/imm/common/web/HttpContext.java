package com.imm.common.web;

import com.google.common.collect.Maps;
import com.imm.common.web.session.SessionTomcatImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;


/**
 * @author rjyx_huxinsheng
 */
public class HttpContext {

    private static Logger logger = LoggerFactory.getLogger(HttpContext.class);

    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<>();
    private static final String LOCAL_IP = "0:0:0:0:0:0:0:1";

    public static void setCurrentRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static void setCurrentResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static HttpServletResponse getResponse() {
        return responseLocal.get();
    }


    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext content) {
        applicationContext = content;
    }

    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    private static void assertApplicationContext() {
        if (HttpContext.applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest request = getRequest();
        return new SessionTomcatImpl(request.getSession());
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        return new SessionTomcatImpl(request.getSession());
    }

    public static String getUrlRoot() {
        HttpServletRequest request = getRequest();
        String scheme = request.getScheme();
        String host = request.getHeader("host");
        String path = request.getContextPath();
        return scheme + "://" + host + (path.length() > 0 ? path : "");
    }

    public static String getContextPath() {
        HttpServletRequest request = getRequest();
        return request.getContextPath();
    }

    public static String getRequestUrl(HttpServletRequest request) {
        String host = request.getHeader("host");
        StringBuffer requestUrl = request.getRequestURL();
        int index = requestUrl.indexOf("://") + 3;
        int index2 = requestUrl.indexOf("/", index);
        requestUrl.replace(index, index2, host);
        String queryString = request.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            requestUrl.append("?").append(queryString);
        }
        String url = requestUrl.toString();
        String scheme = url.substring(0, url.indexOf(":"));
        if (!scheme.equals("https")) {
            url = "https" + url.substring(url.indexOf(":"), url.length());
        }
        return url;
    }

    /**
     * 获取所有request请求参数key-value
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(7);
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }

    public static String getScheme() {
        HttpServletRequest request = getRequest();
        return request.getScheme();
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddr() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (LOCAL_IP.equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
