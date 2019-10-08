package com.imm.common.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rjyx_huxinsheng
 */
@Data
public class LoginInfo implements Serializable {
    private String userId;
    protected String loginName;
    protected String password;
    private String realName;
    private String token;
    private String ipAddr;
    private String deptId;
    private String deptName;
    private String hospitalId;
    private Integer hospitalLogo;
    private String orgCode;
    private String orgName;
    private String hospitalName;
    private Integer level;
}
