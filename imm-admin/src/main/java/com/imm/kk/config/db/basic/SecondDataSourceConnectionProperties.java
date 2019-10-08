package com.imm.kk.config.db.basic;


import com.imm.kk.config.db.DataSourceConnectionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sz
 */
@Configuration
@ConfigurationProperties(prefix = "second.connection")
public class SecondDataSourceConnectionProperties extends DataSourceConnectionProperties {

}
