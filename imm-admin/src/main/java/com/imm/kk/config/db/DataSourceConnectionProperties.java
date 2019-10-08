package com.imm.kk.config.db;


import lombok.Data;

/**
 * @author sz
 */
@Data
public class DataSourceConnectionProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

}
