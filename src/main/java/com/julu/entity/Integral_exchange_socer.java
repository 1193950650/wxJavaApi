package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 积分兑换记录
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@TableName("wx_integral_exchange_socer")
public class Integral_exchange_socer extends Model<Integral_exchange_socer> {

    private static final long serialVersionUID = 1L;

    /**
     * 兑换积分id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 兑换码
     */
    private String exchange_num;
    /**
     * 积分数量
     */
    private Integer socer_num;
    /**
     * 使用用户id
     */
    private Integer user_id;
    /**
     * 兑换时间
     */
    private Date exchange_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExchange_num() {
        return exchange_num;
    }

    public void setExchange_num(String exchange_num) {
        this.exchange_num = exchange_num;
    }

    public Integer getSocer_num() {
        return socer_num;
    }

    public void setSocer_num(Integer socer_num) {
        this.socer_num = socer_num;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getExchange_time() {
        return exchange_time;
    }

    public void setExchange_time(Date exchange_time) {
        this.exchange_time = exchange_time;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_exchange_socer{" +
        ", id=" + id +
        ", exchange_num=" + exchange_num +
        ", socer_num=" + socer_num +
        ", user_id=" + user_id +
        ", exchange_time=" + exchange_time +
        "}";
    }
}
