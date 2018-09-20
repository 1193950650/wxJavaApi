package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 积分码生成记录
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
     * 积分
     */
    @ApiModelProperty("积分")
    private Integer integral_num;
    /**
     * 使用过 0未使用 1使用过
     */
    @ApiModelProperty("使用过 0未使用 1使用过")
    private Integer is_used;
    /**
     * 使用人
     */
    @ApiModelProperty("使用人")
    private String open_id;


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

    public Integer getIntegral_num() {
        return integral_num;
    }

    public void setIntegral_num(Integer integral_num) {
        this.integral_num = integral_num;
    }

    public Integer getIs_used() {
        return is_used;
    }

    public void setIs_used(Integer is_used) {
        this.is_used = is_used;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
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
        ", integral_num=" + integral_num +
        ", is_used=" + is_used +
        ", open_id=" + open_id +
        "}";
    }
}
