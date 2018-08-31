package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 内容-评论
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@TableName("wx_content_comment")
public class Content_comment extends Model<Content_comment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 图文内容id
     */
    private Integer imgtext_id;
    /**
     * 用户id
     */
    private Integer user_id;
    /**
     * 被点赞次数
     */
    private Integer thumbs_up_num;
    /**
     * 显示 0不显示 1显示
     */
    private Integer is_show;


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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getThumbs_up_num() {
        return thumbs_up_num;
    }

    public void setThumbs_up_num(Integer thumbs_up_num) {
        this.thumbs_up_num = thumbs_up_num;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
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
        ", user_id=" + user_id +
        ", thumbs_up_num=" + thumbs_up_num +
        ", is_show=" + is_show +
        "}";
    }
}
