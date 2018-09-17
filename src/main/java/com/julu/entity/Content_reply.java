package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 图文-评论-回复
 * </p>
 *
 * @author mhs
 * @since 2018-09-16
 */
@ApiModel("图文-评论-回复")
@TableName("wx_content_reply")
public class Content_reply extends Model<Content_reply> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论回复id
     */
    @ApiModelProperty("评论回复id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    private Integer comment_id;
    /**
     * 回复内容
     */
    @ApiModelProperty("回复内容")
    private String cotent;
    /**
     * 用户唯一标识
     */
    @ApiModelProperty("用户唯一标识")
    private String openid;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String user_name;
    /**
     * 被回复者姓名
     */
    @ApiModelProperty("被回复者姓名")
    private String reply_name;
    /**
     * 回复时间
     */
    @ApiModelProperty("回复时间")
    private Date add_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public String getAdd_time() {
        if(add_time!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String dateString = sdf.format(add_time);
            return dateString;
        }else{
            return "";
        }

    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Content_reply{" +
        ", id=" + id +
        ", comment_id=" + comment_id +
        ", cotent=" + cotent +
        ", openid=" + openid +
        ", user_name=" + user_name +
        ", reply_name=" + reply_name +
        ", add_time=" + add_time +
        "}";
    }
}
