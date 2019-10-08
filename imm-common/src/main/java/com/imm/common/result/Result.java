package com.imm.common.result;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author HuXinsheng
 */
@ApiModel(description = "返回响应数据")
@Data
public class Result implements Serializable {
    @ApiModelProperty(value = "错误编号")
    protected int code;

    @ApiModelProperty(value = "错误信息")
    protected String msg = "";

    @ApiModelProperty(value = "返回多值结果")
    protected Object[] arguments;

    protected static Properties messages;

    @ApiModelProperty(value = "返回对象")
    protected Object data;

    @ApiModelProperty(value = "返回集合")
    protected List rows;

    @ApiModelProperty(value = "返回扩展值")
    protected Object result;

    /**
     * 成功
     */
    protected final static int OK = 0;

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

    static {
        messages = new Properties();
        try {
            String language = LocaleContextHolder.getLocale().getLanguage();
            System.out.println(language);
            messages.load(Result.class.getResourceAsStream("/errors.properties"));
        } catch (Exception e) {
            LogFactory.getLog(Result.class).error(null, e);
        }
    }

    protected Result() {
    }

    public Result(int code, String message, Object obj) {
        this.code = code;
        this.msg = message;
        this.data = obj;
    }

    public Result(String message, Object obj) {
        this.code = OK;
        this.msg = message;
        this.data = obj;
    }

    public static Result error() {
        Result result = new Result();
        result.code = SERVER_ERROR;
        return result;
    }

    public static Result error(int code, String message) {
        Result result = new Result();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static Result error(int code, Object... arguments) {
        Result result = new Result();
        result.code = code;
        result.arguments = arguments;
        return result;
    }

    public static Result error(String message, Object... arguments) {
        Result result = new Result();
        result.code = SERVER_ERROR;
        result.msg = message;
        result.arguments = arguments;
        return result;
    }

    public static Result ok() {
        Result result = new Result();
        result.code = OK;
        return result;
    }

    public static Result ok(Object value) {
        Result result = new Result();
        result.code = OK;
        result.data = value;
        return result;
    }

    public static Result ok(Object value, Object retVal) {
        Result result = new Result();
        result.result = retVal;
        result.code = OK;
        result.data = value;
        return result;
    }

    public static Result list(List<?> rows) {
        Result result = new Result();
        result.code = OK;
        result.rows = rows;
        return result;
    }

    public <T extends Result> T transform(Class<T> clz) {
        try {
            Result result = clz.newInstance();
            result.code = this.code;
            result.msg = this.msg;
            result.arguments = this.arguments;
            result.data = this.data;
            return (T) result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public int getCode() {
        return code;
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


    public Object getData() {
        return data;
    }

    public Object getResult() {
        return result;
    }

    @ApiModelProperty(value = "成功状态")
    public boolean isOK() {
        return code == OK;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(7);
        map.put("code", getCode());
        map.put("msg", getMsg());
        return map;
    }
}
