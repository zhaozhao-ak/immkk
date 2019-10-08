package com.imm.kk.config.db.basic;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

/**
 * @author sz
 */
/*@Configuration*/
public class SecondDataSourceConf {
    private static Logger logger = LoggerFactory.getLogger(SecondDataSourceConf.class);

    /**
     * 默认配置数据源
     *
     * @param bscp
     * @param bdop
     * @return
     */
    /*@Bean(name = "secondDataSource", initMethod = "init", destroyMethod = "close")*/
    public DruidDataSource initSecondDataSource(SecondDataSourceConnectionProperties bscp,
                                                SecondDataSourceOperationProperties bdop) {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化secondDataSource");
        }
        logger.debug("driverClassName:{}",bscp.getDriverClassName());
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(bscp.getDriverClassName());
        dds.setUrl(bscp.getUrl());
        dds.setUsername(bscp.getUsername());
        dds.setPassword(bscp.getPassword());
        dds.setInitialSize(bdop.getInitialSize());
        dds.setMinIdle(bdop.getMinIdle());
        dds.setMaxActive(bdop.getMaxActive());
        dds.setMaxWait(bdop.getMaxWait());
        dds.setTimeBetweenEvictionRunsMillis(bdop.getTimeBetweenEvictionRunsMillis());
        dds.setMinEvictableIdleTimeMillis(bdop.getMinEvictableIdleTimeMillis());
        dds.setValidationQuery(bdop.getValidationQuery());
        dds.setTestWhileIdle(bdop.getTestWhileIdle());
        dds.setTestOnBorrow(bdop.getTestOnBorrow());
        dds.setTestOnReturn(bdop.getTestOnReturn());
        dds.setPoolPreparedStatements(bdop.getPoolPreparedStatements());
        dds.setMaxPoolPreparedStatementPerConnectionSize(bdop.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dds.setFilters(bdop.getFilters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }

    /**
     * 默认druid数据源状态监控.
     *
     * @return
     */
    /*@Bean(name = "secondServletRegistrationBean")*/
    public ServletRegistrationBean druidServlet(SecondDataSourceOperationProperties bdop) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", bdop.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", bdop.getLoginPassword());
        return servletRegistrationBean;
    }
}
