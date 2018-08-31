package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 内容-轮播管理
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@ApiModel("内容-轮播管理")
@TableName("wx_content_broadcast")
public class Content_broadcast extends Model<Content_broadcast> {

    private static final long serialVersionUID = 1L;

    /**
     * 轮番管理id
     */
    @ApiModelProperty("轮番管理id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题名称
     */
    @ApiModelProperty("标题名称")
    private String title_name;
    /**
     * 图片处理
     */
    @ApiModelProperty("图片处理")
    private String image;
    /**
     * 链接地址
     */
    @ApiModelProperty("链接地址")
    private String url;
    /**
     * 显示 0不显示 1显示
     */
    @ApiModelProperty("显示 0不显示 1显示")
    private Integer is_show;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private String sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Content_broadcast{" +
        ", id=" + id +
        ", title_name=" + title_name +
        ", image=" + image +
        ", url=" + url +
        ", is_show=" + is_show +
        ", sort=" + sort +
        "}";
    }
}
