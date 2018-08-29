package com.julu.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 接口资源表
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_sys_right")
public class Sys_right extends Model<Sys_right> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 描述
     */
    private String desc;
    /**
     * 地址
     */
    private String url;
    /**
     * 名称
     */
    private String name;
    /**
     * 分类
     */
    private String group;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sys_right{" +
        ", id=" + id +
        ", desc=" + desc +
        ", url=" + url +
        ", name=" + name +
        ", group=" + group +
        "}";
    }
}
