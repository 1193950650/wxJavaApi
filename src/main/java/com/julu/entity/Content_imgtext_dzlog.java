package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 图文点赞记录
 * </p>
 *
 * @author mhs
 * @since 2018-09-19
 */
@ApiModel("图文点赞记录")
@TableName("wx_content_imgtext_dzlog")
public class Content_imgtext_dzlog extends Model<Content_imgtext_dzlog> {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞记录id
     */
    @ApiModelProperty("点赞记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 图文id
     */
    @ApiModelProperty("图文id")
    private Integer imgtext_id;
    /**
     * 用户标识
     */
    @ApiModelProperty("用户标识")
    private Integer open_id;


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

    public Integer getOpen_id() {
        return open_id;
    }

    public void setOpen_id(Integer open_id) {
        this.open_id = open_id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Content_imgtext_dzlog{" +
        ", id=" + id +
        ", imgtext_id=" + imgtext_id +
        ", open_id=" + open_id +
        "}";
    }
}
