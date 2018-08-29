package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_sys_user_roles")
public class Sys_user_roles extends Model<Sys_user_roles> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer sys_user_id;
    /**
     * 角色id
     */
    private Integer roles_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSys_user_id() {
        return sys_user_id;
    }

    public void setSys_user_id(Integer sys_user_id) {
        this.sys_user_id = sys_user_id;
    }

    public Integer getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(Integer roles_id) {
        this.roles_id = roles_id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sys_user_roles{" +
        ", id=" + id +
        ", sys_user_id=" + sys_user_id +
        ", roles_id=" + roles_id +
        "}";
    }
}
