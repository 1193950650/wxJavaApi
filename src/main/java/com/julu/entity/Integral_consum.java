package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 积分消费记录
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@TableName("wx_integral_consum")
public class Integral_consum extends Model<Integral_consum> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分消费id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单id
     */
    private Integer order_id;
    /**
     * 消费积分个数
     */
    private Integer socer_num;
    /**
     * 用户id
     */
    private Integer user_id;
    /**
     * 消费时间
     */
    private Date consum_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_consum{" +
        ", id=" + id +
        ", order_id=" + order_id +
        ", socer_num=" + socer_num +
        ", user_id=" + user_id +
        ", consum_time=" + consum_time +
        "}";
    }

    public Date getConsum_time() {
        return consum_time;
    }

    public void setConsum_time(Date consum_time) {
        this.consum_time = consum_time;
    }
}
