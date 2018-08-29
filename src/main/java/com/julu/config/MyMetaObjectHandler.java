package com.julu.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;


/**
 * <pre>
 * <b>注入公共字段自动填充,任选注入方式即可.</b>
 * <b>Description:
 * 1、后期考虑接口方式，由子项目自行实现规则</b>
 *
 */
//@Component
@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * <pre>
     * <b> 新增数据前设置共有数据.</b>
     * <b>Description:</b>
     * @param metaObject 插入元数据
     * @return 封装实体 <{@link  }>
     * <pre>
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//        log.info("新增步骤设置公共属性");
    }

    /**
     * <pre>
     * <b> 更新数据前设置共有数据.</b>
     * <b>Description:</b>
     *
     * <b>Author: 641597345@qq.com </b>
     * @return 封装实体 <{@link  }>
     * <pre>
     */
    @Override
    public void updateFill(MetaObject metaObject) {
//        log.info("更新步骤设置公共属性");
    }
}
