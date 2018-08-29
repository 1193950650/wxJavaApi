package com.julu.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 参数表
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_sys_config")
public class Sys_config extends Model<Sys_config> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 参数分组
     */
    private String group;
    /**
     * 参数名
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
    /**
     * 参数描述
     */
    private String notes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sys_config{" +
        ", id=" + id +
        ", group=" + group +
        ", name=" + name +
        ", value=" + value +
        ", notes=" + notes +
        "}";
    }
}
