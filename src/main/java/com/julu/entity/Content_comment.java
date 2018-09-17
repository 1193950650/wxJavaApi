package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.julu.utils.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 内容-评论
 * </p>
 *
 * @author mhs
 * @since 2018-09-17
 */
@ApiModel("内容-评论")
@TableName("wx_content_comment")
public class Content_comment extends Model<Content_comment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 图文内容id
     */
    @ApiModelProperty("图文内容id")
    private Integer imgtext_id;
    /**
     * 用户唯一标识
     */
    @ApiModelProperty("用户唯一标识")
    private String open_id;
    /**
     * 显示 0不显示 1显示
     */
    @ApiModelProperty("显示 0不显示 1显示")
    private Integer is_show;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String user_name;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String user_icon;
    /**
     * 评论时间
     */
    @ApiModelProperty("评论时间")
    private Date add_time;
    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImgtext_id() {
        return imgtext_id;
    }

    public void setImgtext_id(Integer imgtext_id) {
        this.imgtext_id = imgtext_id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public Date getAdd_time() {
            return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Content_comment{" +
        ", id=" + id +
        ", imgtext_id=" + imgtext_id +
        ", open_id=" + open_id +
        ", is_show=" + is_show +
        ", user_name=" + user_name +
        ", user_icon=" + user_icon +
        ", add_time=" + add_time +
        ", content=" + content +
        "}";
    }
}
