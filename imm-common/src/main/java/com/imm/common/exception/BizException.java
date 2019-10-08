package com.imm.common.exception;

import com.imm.common.result.ResponseInfo;
import lombok.Data;

/**
 * 自定义异常
 *
 * @author xxjin
 */
@Data
public class BizException extends RuntimeException {
    /**
     * 服务器异常
     */
    public final static int SERVER_ERROR = 500;
    /**
     * 业务异常
     */
    public final static int BIZ_ERROR = 600;
    /**
     * 友好提示的code码
     */
    private int code;

    private ResponseInfo responseInfo;

    public BizException(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public BizException() {
        super();
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public BizException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
