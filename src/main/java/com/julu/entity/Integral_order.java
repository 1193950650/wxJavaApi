package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 积分商城订单
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@TableName("wx_integral_order")
public class Integral_order extends Model<Integral_order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    private String order_num;
    /**
     * 购买用户id
     */
    private Integer user_id;
    /**
     * 积分商品id
     */
    private Integer good_id;
    /**
     * 订单时间
     */
    private Date order_time;
    /**
     * 订单状态 0待支付 1取消 2已支付
     */
    private Integer order_status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_order{" +
        ", id=" + id +
        ", order_num=" + order_num +
        ", user_id=" + user_id +
        ", good_id=" + good_id +
        ", order_time=" + order_time +
        ", order_status=" + order_status +
        "}";
    }
}
