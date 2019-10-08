package com.imm.kk.config.db.basic;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisMapperRefresh;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Maps;
import com.imm.common.ds.DatabaseType;
import com.imm.common.utils.BlankUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Map;

/**
 * @author HuXinsheng
 */
@Configuration
public class BasisMybatisPlusConf {

    private static Logger logger = LoggerFactory.getLogger(BasisMybatisPlusConf.class);

    /**
     * mybatisPlus全局配置
     *
     * @param idType
     * @param dbColumnUnderline
     * @param isCapitalMode
     * @return
     */
    @Bean(name = "basisGlobalConfig")
    public GlobalConfiguration globalConfig(
            @Value("${mybatisPlus.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
            @Value("${mybatisPlus.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
            @Value("${mybatisPlus.globalConfig.isCapitalMode}") Boolean isCapitalMode //数据库大写下划线转换
    ) {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化GlobalConfiguration");
        }
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        // 主键类型
        if (!BlankUtil.isBlank(idType)) {
            globalConfig.setIdType(idType);
        }
        // 驼峰下划线转换
        if (!BlankUtil.isBlank(dbColumnUnderline)) {
            globalConfig.setDbColumnUnderline(dbColumnUnderline);
        }
        // 数据库大写下划线转换
        if (!BlankUtil.isBlank(isCapitalMode)) {
            globalConfig.setCapitalMode(isCapitalMode);
        }
        globalConfig.setRefresh(true);
        return globalConfig;
    }

    @Bean(name = "basisSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "basisGlobalConfig") GlobalConfiguration globalConfig,
                                               @Qualifier(value = "basisDataSource") DruidDataSource dataSource) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化SqlSessionFactory");
        }
        String mapperLocations = "classpath*:com/imm/kk/mapper/**/xml/*Mapper.xml";
        String configLocation = "classpath:/mybatis/mybatis-sqlconfig.xml";
        String typeAliasesPackage = "com.imm.kk.entity.*";
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        // 数据源
        sqlSessionFactory.setDataSource(dataSource);
        // 全局配置
        sqlSessionFactory.setGlobalConfig(globalConfig);
        Interceptor[] interceptor = {new PaginationInterceptor()};
        // 分页插件
        sqlSessionFactory.setPlugins(interceptor);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // 自动扫描Mapping.xml文件
            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
            sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
            // IMPORTANT: we MUST set the 'VFS',
            // otherwise if you run this project as a 'executable jar',
            // then mybatis will throw an exception saying that it can not find java POJO
            sqlSessionFactory.setVfs(SpringBootVFS.class);
            sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * MyBatis 动态扫描
     *
     * @return
     */
    @Bean(name = "basisMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化basisMapperScannerConfigurer");
        }
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        String basePackage = "com.imm.kk.mapper.*";
        mapperScannerConfigurer.setBasePackage(basePackage);
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }

    /**
     * 配置事务管理
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "basisTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "basisDataSource") DruidDataSource dataSource) {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化basisTransactionManager");
        }
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisMapperRefresh")
    public MybatisMapperRefresh mybatisMapperRefresh(@Qualifier(value = "basisSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        String mapperLocations = "classpath*:com/imm/kk/mapper/**/xml/*Mapper.xml";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(mapperLocations);
        //延时加载(秒)
        int delaySeconds = 5;
        //间隔时间(秒)
        int sleepSeconds = 10;
        //是否启用(生产环境建议关闭)
        boolean enabled = true;
        return new MybatisMapperRefresh(resources, sqlSessionFactory, delaySeconds, sleepSeconds, enabled);
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("basisDataSource") DruidDataSource basisDataSource
            /*,@Qualifier("secondDataSource") DruidDataSource secondDataSource*/) {
        Map<Object, Object> targetDataSources = Maps.newHashMapWithExpectedSize(7);
        targetDataSources.put(DatabaseType.basis, basisDataSource);
        //targetDataSources.put(DatabaseType.second, secondDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为myTestDbDataSource
        dataSource.setDefaultTargetDataSource(basisDataSource);

        return dataSource;
    }
}
