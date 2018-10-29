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
 * 积分纪录
 * </p>
 *
 * @author mhs
 * @since 2018-10-29
 */
@ApiModel("积分纪录")
@TableName("wx_socer_log")
public class Socer_log extends Model<Socer_log> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分纪录ID
     */
    @ApiModelProperty("积分纪录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 积分个数
     */
    @ApiModelProperty("积分个数")
    private Integer socer_num;
    /**
     * 纪录类型 0评论+ 1浏览+ 2发布+ 3兑换码+ 4转发+ 5兑换商品-
     */
    @ApiModelProperty("纪录类型 0评论+ 1浏览+ 2发布+ 3兑换码+ 4转发+ 5兑换商品-")
    private Integer type;
    /**
     * 微信唯一id
     */
    @ApiModelProperty("微信唯一id")
    private String open_id;
    /**
     * 创建者id
     */
    @ApiModelProperty("创建者id")
    private Integer create_by;
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private Date create_date;
    /**
     * 更新者id
     */
    @ApiModelProperty("更新者id")
    private Integer update_by;
    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    private Date update_date;
    /**
     * 删除 0否 1是
     */
    @ApiModelProperty("删除 0否 1是")
    private Integer del_flag;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSocer_num() {
        return socer_num;
    }

    public void setSocer_num(Integer socer_num) {
        this.socer_num = socer_num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Integer getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Integer update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Socer_log{" +
        ", id=" + id +
        ", socer_num=" + socer_num +
        ", type=" + type +
        ", open_id=" + open_id +
        ", create_by=" + create_by +
        ", create_date=" + create_date +
        ", update_by=" + update_by +
        ", update_date=" + update_date +
        ", del_flag=" + del_flag +
        ", remarks=" + remarks +
        "}";
    }
}
