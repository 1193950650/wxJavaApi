package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *  积分码生成记录
 * </p>
 *
 * @author mhs
 * @since 2018-09-20
 */
@ApiModel("积分码生成记录")
@TableName("wx_integral_code")
public class Integral_code extends Model<Integral_code> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @ApiModelProperty("积分id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 积分兑换码
     */
    @ApiModelProperty("积分兑换码")
    private String code;
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Integer good_id;
    /**
     * 使用过 0未使用 1使用过
     */
    @ApiModelProperty("使用过 0未使用 1使用过")
    private Integer is_used;
    /**
     * 失效时间
     */
    @ApiModelProperty("失效时间")
    private Date end_date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Integer getIs_used() {
        return is_used;
    }

    public void setIs_used(Integer is_used) {
        this.is_used = is_used;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_code{" +
                ", id=" + id +
                ", code=" + code +
                ", good_id=" + good_id +
                ", is_used=" + is_used +
                ", end_date=" + end_date +
                "}";
    }
}
