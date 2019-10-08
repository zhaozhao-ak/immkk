package com.imm.common.result;

import com.alibaba.druid.util.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author rjyx_huxinsheng
 */
@ApiModel(description = "返回响应数据")
@Data
public class JsonResult implements Serializable {

    /**
     * 成功
     */
    protected final static int OK = 200;
    /**
     * 授权401 需要登录
     */
    public final static int LOGIN_REQUIRE = 4010;
    /**
     * 登录密码错误
     */
    public final static int LOGIN_PWD_ERROR = 4011;
    /**
     * 登录帐号已禁用
     */
    public final static int LOGIN_DISABLED_ERROR = 4012;
    /**
     * 登录次数过多
     */
    public final static int LOGIN_TOO_MANY_ERROR = 4013;
    /**
     * 没有权限
     */
    public final static int LOGIN_NON_PRIVILEGE_ERROR = 4014;
    /**
     * token为空
     */
    public final static int TOKEN_EMPTY = 4015;
    /**
     * token验证失败
     */
    public final static int TOKEN_VERIFY_ERROR = 4016;
    /**
     * 会话超时 超时408
     */
    public final static int SESSION_TIMEOUT_ERROR = 4080;
    /**
     * 不存在404 用户不存在
     */
    public final static int NOT_FOUND = 4040;
    /**
     * 用户不存在
     */
    public final static int USER_NOT_FOUND = 4041;
    /**
     * 参数不存在
     */
    public final static int PARAM_NOT_FOUND = 4042;
    /**
     * 服务器错误 内部500
     */
    public final static int SERVER_ERROR = 5000;
    /**
     * 验证码错误
     */
    public final static int CAPTCHA_ERROR = 5001;
    /**
     * 用户存在
     */
    public final static int USER_EXISTS = 5002;
    /**
     * 数据重复
     */
    public final static int EXISTS = 5003;
    /**
     * 策略有效期重复
     */
    public final static int DATA_RANGE_EXISTS = 5004;

    public final static int PARAM_ERROR = 5005;


    @ApiModelProperty(value = "状态码：200成功；其他失败。")
    private int code = 200;

    @ApiModelProperty(value = "状态描述：失败必须有值")
    private String msg;

    private Object data;

    @ApiModelProperty(value = "返回多值结果")
    protected Object[] arguments;

    @ApiModelProperty(value = "成功状态：200")
    public boolean isOK() {
        return code == OK;
    }

    public JsonResult(){}
    public JsonResult(Object data){
        this.data = data;
    }

    protected static Properties messages;

    public static JsonResult ok(Object data) {
        JsonResult result = new JsonResult();
        result.data = data;
        return result;
    }

    public static JsonResult error() {
        JsonResult result = new JsonResult();
        result.code = SERVER_ERROR;
        return result;
    }

    public static JsonResult error(int code, String msg) {
        JsonResult result = new JsonResult();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static JsonResult error(int code, Object... arguments) {
        JsonResult result = new JsonResult();
        result.code = code;
        result.arguments = arguments;
        return result;
    }

    public static JsonResult error(String msg, Object... arguments) {
        JsonResult result = new JsonResult();
        result.msg = msg;
        result.arguments = arguments;
        return result;
    }

    static {
        messages = new Properties();
        try {
            messages.load(JsonResult.class.getResourceAsStream("/api-errors.properties"));
        } catch (Exception e) {
            LogFactory.getLog(JsonResult.class).error(null, e);
        }
    }

    public String getMsg() {
        String message = "";
        if (!StringUtils.isEmpty(msg)) {
            message = msg;
        } else {
            String key = String.valueOf(code);
            if (messages.containsKey(key)) {
                message = (String) messages.get(key);
            }
        }
        if (arguments != null) {
            int len = arguments.length;
            for (int i = 0; i < len; i++) {
                String num = "{" + i + "}";
                Object value = arguments[i];
                if (value == null) {
                    value = "";
                }
                message = message.replace(num, value.toString());
            }
        }
        return message;
    }
}
