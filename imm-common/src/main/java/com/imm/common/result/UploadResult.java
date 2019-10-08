package com.imm.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rjyx_huxinsheng
 */
@Data
public class UploadResult implements Serializable {
    private int code;
    private String message;
    private UploadInfo info;

    public static UploadResult ok() {
        UploadResult result = new UploadResult();
        result.code = 0;
        return result;
    }

    public static UploadResult ok(UploadInfo info) {
        UploadResult result = new UploadResult();
        result.code = 0;
        result.info = info;
        return result;
    }

    public static UploadResult error(int code) {
        UploadResult result = new UploadResult();
        result.code = code;
        return result;
    }

    public static UploadResult error(String msg) {
        UploadResult result = new UploadResult();
        result.code = 1;
        result.message = msg;
        return result;
    }

    public static UploadResult error(int code, String msg) {
        UploadResult result = new UploadResult();
        result.code = code;
        result.message = msg;
        return result;
    }

    public boolean isOk() {
        return code == 0;
    }
}
