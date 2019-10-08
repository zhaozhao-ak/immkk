package com.imm.kk.config.db.basic;


import com.imm.kk.config.db.DataSourceOperationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sz
 */
@Configuration
@ConfigurationProperties(prefix = "second.druid")
public class SecondDataSourceOperationProperties extends DataSourceOperationProperties {

}
