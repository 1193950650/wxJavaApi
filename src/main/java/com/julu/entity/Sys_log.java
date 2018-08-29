package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_sys_log")
public class Sys_log extends Model<Sys_log> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 日志分类
     */
    private String group;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 访问路径
     */
    private String right_url;
    /**
     * 日志时间
     */
    private Date log_time;
    /**
     * 用户id
     */
    private Integer user_id;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRight_url() {
        return right_url;
    }

    public void setRight_url(String right_url) {
        this.right_url = right_url;
    }

    public Date getLog_time() {
        return log_time;
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sys_log{" +
        ", id=" + id +
        ", group=" + group +
        ", content=" + content +
        ", right_url=" + right_url +
        ", log_time=" + log_time +
        ", user_id=" + user_id +
        "}";
    }
}
