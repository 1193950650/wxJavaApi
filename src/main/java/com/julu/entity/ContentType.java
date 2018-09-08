package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 内容-分类
 * </p>
 *
 * @author mhs
 * @since 2018-09-08
 */
@ApiModel("内容-分类")
@TableName("wx_content_type")
public class ContentType extends Model<ContentType> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;
    /**
     * 是否显示
     */
    @ApiModelProperty("是否显示")
    @TableField("is_show")
    private Integer isShow;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;


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

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ContentType{" +
        ", id=" + id +
        ", name=" + name +
        ", isShow=" + isShow +
        ", sort=" + sort +
        ", icon=" + icon +
        "}";
    }
}
