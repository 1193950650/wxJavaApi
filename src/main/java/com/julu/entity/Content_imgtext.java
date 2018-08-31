package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 内容-图文
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@TableName("wx_content_imgtext")
public class Content_imgtext extends Model<Content_imgtext> {

    private static final long serialVersionUID = 1L;

    /**
     * 图文id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 图文名称
     */
    private String name;
    /**
     * 作者
     */
    private String author;
    /**
     * 所属分类id
     */
    private Integer type_id;
    /**
     * 封面样式0默认样式1大图样式2三图样式
     */
    private Integer cover_style;
    /**
     * 封面缩略图
     */
    private String cover_image;
    /**
     * 显示封面缩略图 0不显示 1显示
     */
    private Integer is_show;
    /**
     * 关键词标签用逗号分隔
     */
    private String word_tags;
    /**
     * 是否付费
     */
    private Integer is_pay;
    /**
     * 文章内容
     */
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getCover_style() {
        return cover_style;
    }

    public void setCover_style(Integer cover_style) {
        this.cover_style = cover_style;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getWord_tags() {
        return word_tags;
    }

    public void setWord_tags(String word_tags) {
        this.word_tags = word_tags;
    }

    public Integer getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(Integer is_pay) {
        this.is_pay = is_pay;
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
        return "Content_imgtext{" +
        ", id=" + id +
        ", name=" + name +
        ", author=" + author +
        ", type_id=" + type_id +
        ", cover_style=" + cover_style +
        ", cover_image=" + cover_image +
        ", is_show=" + is_show +
        ", word_tags=" + word_tags +
        ", is_pay=" + is_pay +
        ", content=" + content +
        "}";
    }
}
