package com.imm.kk.result;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rjyx_huxinsheng
 */
@Data
public class HdisResult implements Serializable {
    /**
     * 返回代码
     */
    protected int code;
    /**
     * 返回信息
     */
    protected String msg = "";
    /**
     * 返回数据对象
     */
    protected Object data;
    /**
     * 返回数据集合
     */
    protected List rows;

    @ApiModelProperty(value = "多值结果")
    protected Object[] arguments;

    protected Object result;

    private Object responseData;

    protected HdisResult() {
    }

    public static HdisResult error() {
        HdisResult result = new HdisResult();
        result.code = StatusCode.SERVER_ERROR;
        return result;
    }

    public static HdisResult error(int code) {
        HdisResult result = new HdisResult();
        result.code = code;
        return result;
    }

    public static HdisResult error(String msg) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.SERVER_ERROR;
        result.msg = msg;
        return result;
    }

    public static HdisResult error(int code, String message) {
        HdisResult result = new HdisResult();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static HdisResult error(int code, Object... arguments) {
        HdisResult result = new HdisResult();
        result.code = code;
        result.arguments = arguments;
        return result;
    }

    public static HdisResult ok() {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        return result;
    }

    public static HdisResult ok(Object value) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        result.data = value;
        return result;
    }

    public static HdisResult ok(Object value, Object ret) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        result.data = value;
        result.result = ret;
        return result;
    }

    public static HdisResult apiOk(Object value) {
        HdisResult result = new HdisResult();
        List<Object> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        if (null != value) {
            obj.put("result", value);
        } else {
            obj.put("result", "ok");
        }
        list.add(obj);
        result.code = StatusCode.OK;
        result.data = list;
        return result;
    }

    public static HdisResult apiObjectOk(Object value) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        if (null != value) {
            result.data = value;
        } else {
            result.data = "";
        }
        result.data = value;
        return result;
    }

    public static HdisResult response(Object value) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        result.responseData = value;
        return result;
    }

    public static HdisResult apiOk() {
        return apiOk(null);
    }

    public static HdisResult list(List<?> rows) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        result.rows = rows;
        return result;
    }
    public static HdisResult list(List<?> rows,Object ret) {
        HdisResult result = new HdisResult();
        result.code = StatusCode.OK;
        result.rows = rows;
        result.result = ret;
        return result;
    }
    public int getCode() {
        return code;
    }

    @ApiModelProperty(value = "成功状态")
    public boolean isOK() {
        return code == StatusCode.OK;
    }

}
