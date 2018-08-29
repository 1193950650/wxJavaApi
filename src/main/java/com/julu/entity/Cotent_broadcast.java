package com.julu.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 内容-轮播配置
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_cotent_broadcast")
public class Cotent_broadcast extends Model<Cotent_broadcast> {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播管理id
     */
    private Integer id;
    /**
     * 面板指示灯 0不显示 1显示
     */
    private Integer is_show_point;
    /**
     * 自动切换 0不能 1可以
     */
    private Integer is_auto_change;
    /**
     * 自动切换时间间隔（毫秒）
     */
    private Integer auto_change_time;
    /**
     * 滑动动画时长（毫秒）
     */
    private Integer animation_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIs_show_point() {
        return is_show_point;
    }

    public void setIs_show_point(Integer is_show_point) {
        this.is_show_point = is_show_point;
    }

    public Integer getIs_auto_change() {
        return is_auto_change;
    }

    public void setIs_auto_change(Integer is_auto_change) {
        this.is_auto_change = is_auto_change;
    }

    public Integer getAuto_change_time() {
        return auto_change_time;
    }

    public void setAuto_change_time(Integer auto_change_time) {
        this.auto_change_time = auto_change_time;
    }

    public Integer getAnimation_time() {
        return animation_time;
    }

    public void setAnimation_time(Integer animation_time) {
        this.animation_time = animation_time;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Cotent_broadcast{" +
        ", id=" + id +
        ", is_show_point=" + is_show_point +
        ", is_auto_change=" + is_auto_change +
        ", auto_change_time=" + auto_change_time +
        ", animation_time=" + animation_time +
        "}";
    }
}
