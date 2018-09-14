package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 内容-图文
 * </p>
 *
 * @author mhs
 * @since 2018-09-15
 */
@ApiModel("内容-图文")
@TableName("wx_content_imgtext")
public class Content_imgtext extends Model<Content_imgtext> {

    private static final long serialVersionUID = 1L;

    /**
     * 图文id
     */
    @ApiModelProperty("图文id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 图文名称
     */
    @ApiModelProperty("图文名称")
    private String name;
    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;
    /**
     * 所属分类id
     */
    @ApiModelProperty("所属分类id")
    private Integer type_id;
    /**
     * 封面样式0默认样式1大图样式2三图样式
     */
    @ApiModelProperty("封面样式0默认样式1大图样式2三图样式")
    private Integer cover_style;
    /**
     * 封面缩略图
     */
    @ApiModelProperty("封面缩略图")
    private String cover_image;
    /**
     * 显示封面缩略图 0不显示 1显示
     */
    @ApiModelProperty("显示封面缩略图 0不显示 1显示")
    private Integer is_show;
    /**
     * 关键词标签用逗号分隔
     */
    @ApiModelProperty("关键词标签用逗号分隔")
    private String word_tags;
    /**
     * 是否付费 0不付费 1付费
     */
    @ApiModelProperty("是否付费 0不付费 1付费")
    private Integer is_pay;
    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;
    /**
     * 观看数
     */
    @ApiModelProperty("观看数")
    private Integer see_num;
    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer dz_num;
    /**
     * 评论数
     */
    @ApiModelProperty("评论数")
    private Integer write_num;
    /**
     * 置顶 0不 1要
     */
    @ApiModelProperty("置顶 0不 1要")
    private Integer is_top;
    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date add_time;
    /**
     * 位置-经度
     */
    @ApiModelProperty("位置-经度")
    private BigDecimal longitude;
    /**
     * 位置-维度
     */
    @ApiModelProperty("位置-维度")
    private BigDecimal latitude;
    /**
     * 作者openId
     */
    @ApiModelProperty("作者openId")
    private String openid;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 作者头像
     */
    @ApiModelProperty("作者头像")
    private String author_icon;


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

    public Integer getSee_num() {
        return see_num;
    }

    public void setSee_num(Integer see_num) {
        this.see_num = see_num;
    }

    public Integer getDz_num() {
        return dz_num;
    }

    public void setDz_num(Integer dz_num) {
        this.dz_num = dz_num;
    }

    public Integer getWrite_num() {
        return write_num;
    }

    public void setWrite_num(Integer write_num) {
        this.write_num = write_num;
    }

    public Integer getIs_top() {
        return is_top;
    }

    public void setIs_top(Integer is_top) {
        this.is_top = is_top;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthor_icon() {
        return author_icon;
    }

    public void setAuthor_icon(String author_icon) {
        this.author_icon = author_icon;
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
        ", see_num=" + see_num +
        ", dz_num=" + dz_num +
        ", write_num=" + write_num +
        ", is_top=" + is_top +
        ", add_time=" + add_time +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", openid=" + openid +
        ", address=" + address +
        ", author_icon=" + author_icon +
        "}";
    }
}
