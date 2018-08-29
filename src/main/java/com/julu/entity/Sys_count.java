package com.julu.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 统计表
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_sys_count")
public class Sys_count extends Model<Sys_count> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单表id
     */
    private Integer id;
    /**
     * 打开次数
     */
    private Integer open_num;
    /**
     * 访问次数
     */
    private Integer see_num;
    /**
     * 新访问次数
     */
    private Integer new_see_num;
    /**
     * 分享次数
     */
    private Integer share_num;
    /**
     * 时间
     */
    private Date sys_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpen_num() {
        return open_num;
    }

    public void setOpen_num(Integer open_num) {
        this.open_num = open_num;
    }

    public Integer getSee_num() {
        return see_num;
    }

    public void setSee_num(Integer see_num) {
        this.see_num = see_num;
    }

    public Integer getNew_see_num() {
        return new_see_num;
    }

    public void setNew_see_num(Integer new_see_num) {
        this.new_see_num = new_see_num;
    }

    public Integer getShare_num() {
        return share_num;
    }

    public void setShare_num(Integer share_num) {
        this.share_num = share_num;
    }

    public Date getSys_time() {
        return sys_time;
    }

    public void setSys_time(Date sys_time) {
        this.sys_time = sys_time;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sys_count{" +
        ", id=" + id +
        ", open_num=" + open_num +
        ", see_num=" + see_num +
        ", new_see_num=" + new_see_num +
        ", share_num=" + share_num +
        ", sys_time=" + sys_time +
        "}";
    }
}
