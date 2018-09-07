package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author mhs
 * @since 2018-09-08
 */
@ApiModel("用户表")
@TableName("wx_sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     *  用户id
     */
    @ApiModelProperty(" 用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    private String userName;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String icon;
    /**
     * 微信唯一标识
     */
    @ApiModelProperty("微信唯一标识")
    @TableField("open_id")
    private String openId;
    /**
     * 积分
     */
    @ApiModelProperty("积分")
    private Integer socer;
    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 登录次数
     */
    @ApiModelProperty("登录次数")
    @TableField("login_num")
    private Integer loginNum;
    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private Double longi;
    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private Double lati;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getSocer() {
        return socer;
    }

    public void setSocer(Integer socer) {
        this.socer = socer;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        ", id=" + id +
        ", password=" + password +
        ", userName=" + userName +
        ", icon=" + icon +
        ", openId=" + openId +
        ", socer=" + socer +
        ", lastLoginTime=" + lastLoginTime +
        ", loginNum=" + loginNum +
        ", longi=" + longi +
        ", lati=" + lati +
        "}";
    }
}
