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
 * 用户表
 * </p>
 *
 * @author mhs
 * @since 2018-09-09
 */
@ApiModel("用户表")
@TableName("wx_sys_user")
public class Sys_user extends Model<Sys_user> {

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
    private String user_name;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String icon;
    /**
     * 微信唯一标识
     */
    @ApiModelProperty("微信唯一标识")
    private String open_id;
    /**
     * 积分
     */
    @ApiModelProperty("积分")
    private Integer socer;
    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    private Date last_login_time;
    /**
     * 登录次数
     */
    @ApiModelProperty("登录次数")
    private Integer login_num;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getSocer() {
        return socer;
    }

    public void setSocer(Integer socer) {
        this.socer = socer;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Integer getLogin_num() {
        return login_num;
    }

    public void setLogin_num(Integer login_num) {
        this.login_num = login_num;
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
        return "Sys_user{" +
        ", id=" + id +
        ", password=" + password +
        ", user_name=" + user_name +
        ", icon=" + icon +
        ", open_id=" + open_id +
        ", socer=" + socer +
        ", last_login_time=" + last_login_time +
        ", login_num=" + login_num +
        ", longi=" + longi +
        ", lati=" + lati +
        "}";
    }
}
