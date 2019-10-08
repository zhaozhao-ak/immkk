package com.imm.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rjyx_huxinsheng
 */
@Data
public class License implements Serializable {
    private String code;
    private String name;
    private String issuedTime;
    private String notBefore;
    private String notAfter;
    private String machineData;
    private String info;
    private int amount;
    private String sign;
}
