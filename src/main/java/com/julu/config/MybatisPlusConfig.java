package com.julu.config;

import com.baomidou.mybatisplus.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * <b>myBatisPlus全局配置项.</b>
 * <b>Description:</b>
 * <b>Date: ${DATE} ${TIME}   </b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   ${DATE} ${TIME}          641597345@qq.com            new file.
 * <pre>
 */
@Configuration
@MapperScan("com.julu.mapper*")
public class MybatisPlusConfig {

    /**
     * <pre>
     * <b>mybatis-plus SQL执行效率插件【生产环境可以关闭】.</b>
     * <b>Description:</b>
     *
     * @return PerformanceInterceptor
     * <pre>
     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }

    /**
     * <pre>
     * <b>mybatis-plus分页插件.</b>
     * <b>Description:
     * 1、参见官网文档：http://mp.baomidou.com</b>
     * @return PaginationInterceptor
     * <pre>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    /**
     * <pre>
     * <b>CRUD操作前设置公共数据或日志.</b>
     * <b>Description:
     * 1、MetaObjectHandler由组件提供，需在组件中实现
     * 2、后期可提供接口操作，由子项目自行实现</b>
     *
     * @return MetaObjectHandler
     * <pre>
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }


    /**
     * <pre>
     * <b>注入主键生成器.</b>
     * <b>Description:
     * 1、默认用H2KeyGenerator
     * 2、分布式或集群时考虑用雪花算法或其他成熟组件</b>
     *
     * <b>Author: 641597345@qq.com </b>
     * <b>Date: 2018/3/26 17:00 </b>
     * @return IKeyGenerator
     * <pre>
     */
    @Bean
    public IKeyGenerator keyGenerator() {
        return new H2KeyGenerator();
    }

    /**
     * <pre>
     * <b>注入SQL注入器.</b>
     * <b>Description:
     * 1、baomidou依赖选项</b>
     *
     * <b>Author: 641597345@qq.com </b>
     * <b>Date: 2018/3/26 17:00 </b>
     * @return ISqlInjector
     * <pre>
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}