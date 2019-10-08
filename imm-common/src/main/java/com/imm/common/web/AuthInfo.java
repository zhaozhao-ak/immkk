package com.imm.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author rjyx_huxinsheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfo implements Serializable {

    private Integer domainId;

    private String userId;

    private Integer userType;
}
