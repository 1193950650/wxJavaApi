package com.julu.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 内容-基本配置
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@ApiModel("内容-基本配置")
@TableName("wx_content_config")
public class Content_config extends Model<Content_config> {

    private static final long serialVersionUID = 1L;

    /**
     * 内容管理配置id
     */
    @ApiModelProperty("内容管理配置id")
    private Integer id;
    /**
     * 音视频自动播放 0不开启1开启
     */
    @ApiModelProperty("音视频自动播放 0不开启1开启")
    private Integer is_open_audio;
    /**
     * 评论留言功能：0关闭 1开启
     */
    @ApiModelProperty("评论留言功能：0关闭 1开启")
    private Integer is_open_comment;
    /**
     * 评论审核：0不审核 1审核
     */
    @ApiModelProperty("评论审核：0不审核 1审核")
    private Integer is_need_examine;
    /**
     * 浏览文章增加积分
     */
    @ApiModelProperty("浏览文章增加积分")
    private Integer browse_integral_num;
    /**
     * 评论文章增加积分
     */
    @ApiModelProperty("评论文章增加积分")
    private Integer comment_integral_num;
    /**
     * 显示相关爱好物 0不显示 1显示
     */
    @ApiModelProperty("显示相关爱好物 0不显示 1显示")
    private Integer is_show_like;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIs_open_audio() {
        return is_open_audio;
    }

    public void setIs_open_audio(Integer is_open_audio) {
        this.is_open_audio = is_open_audio;
    }

    public Integer getIs_open_comment() {
        return is_open_comment;
    }

    public void setIs_open_comment(Integer is_open_comment) {
        this.is_open_comment = is_open_comment;
    }

    public Integer getIs_need_examine() {
        return is_need_examine;
    }

    public void setIs_need_examine(Integer is_need_examine) {
        this.is_need_examine = is_need_examine;
    }

    public Integer getBrowse_integral_num() {
        return browse_integral_num;
    }

    public void setBrowse_integral_num(Integer browse_integral_num) {
        this.browse_integral_num = browse_integral_num;
    }

    public Integer getComment_integral_num() {
        return comment_integral_num;
    }

    public void setComment_integral_num(Integer comment_integral_num) {
        this.comment_integral_num = comment_integral_num;
    }

    public Integer getIs_show_like() {
        return is_show_like;
    }

    public void setIs_show_like(Integer is_show_like) {
        this.is_show_like = is_show_like;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Content_config{" +
        ", id=" + id +
        ", is_open_audio=" + is_open_audio +
        ", is_open_comment=" + is_open_comment +
        ", is_need_examine=" + is_need_examine +
        ", browse_integral_num=" + browse_integral_num +
        ", comment_integral_num=" + comment_integral_num +
        ", is_show_like=" + is_show_like +
        "}";
    }
}
