package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 积分商城配置
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_integral_config")
@ApiModel("积分商城配置")
public class Integral_config extends Model<Integral_config> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分配置id
     */
    @ApiModelProperty("积分配置id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 0关闭 1开启
     */
    @ApiModelProperty("0关闭 1开启")
    private Integer status;
    /**
     * 登录积分奖励
     */
    @ApiModelProperty("登录积分奖励")
    private Integer login_get_num;
    /**
     * 每消费多少个
     */
    @ApiModelProperty("每消费多少个")
    private Integer consumer_num;
    /**
     * 消费可获得的积分
     */
    @ApiModelProperty("消费可获得的积分")
    private Integer consumer_get_num;
    /**
     * 积分规则
     */
    @ApiModelProperty("积分规则")
    private String rule;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLogin_get_num() {
        return login_get_num;
    }

    public void setLogin_get_num(Integer login_get_num) {
        this.login_get_num = login_get_num;
    }

    public Integer getConsumer_num() {
        return consumer_num;
    }

    public void setConsumer_num(Integer consumer_num) {
        this.consumer_num = consumer_num;
    }

    public Integer getConsumer_get_num() {
        return consumer_get_num;
    }

    public void setConsumer_get_num(Integer consumer_get_num) {
        this.consumer_get_num = consumer_get_num;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_config{" +
        ", id=" + id +
        ", status=" + status +
        ", login_get_num=" + login_get_num +
        ", consumer_num=" + consumer_num +
        ", consumer_get_num=" + consumer_get_num +
        ", rule=" + rule +
        "}";
    }
}
