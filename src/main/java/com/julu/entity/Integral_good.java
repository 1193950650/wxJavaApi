package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 积分商品
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@TableName("wx_integral_good")
public class Integral_good extends Model<Integral_good> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品名称
     */
    private String good_name;
    /**
     * 市场价格
     */
    private Double market_price;
    /**
     * 销售价格
     */
    private Double sale_price;
    /**
     * 库存
     */
    private Integer stock_num;
    /**
     * 兑换次数
     */
    private Integer exchange_num;
    /**
     * 0下架 1上架
     */
    private Integer sale_status;
    /**
     * 0不需要 1需要
     */
    private Integer need_address;
    /**
     * 缩略图
     */
    private String thumbnail_url;
    /**
     * 商品图册，用逗号隔开
     */
    private String good_imges;
    /**
     * 商品简介
     */
    private String goog_desc;
    /**
     * 截止时间
     */
    private Date end_time;
    /**
     * 商品说明
     */
    private String good_notes;
    /**
     * 商品排序
     */
    private Integer good_sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public Double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public Double getSale_price() {
        return sale_price;
    }

    public void setSale_price(Double sale_price) {
        this.sale_price = sale_price;
    }

    public Integer getStock_num() {
        return stock_num;
    }

    public void setStock_num(Integer stock_num) {
        this.stock_num = stock_num;
    }

    public Integer getExchange_num() {
        return exchange_num;
    }

    public void setExchange_num(Integer exchange_num) {
        this.exchange_num = exchange_num;
    }

    public Integer getSale_status() {
        return sale_status;
    }

    public void setSale_status(Integer sale_status) {
        this.sale_status = sale_status;
    }

    public Integer getNeed_address() {
        return need_address;
    }

    public void setNeed_address(Integer need_address) {
        this.need_address = need_address;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getGood_imges() {
        return good_imges;
    }

    public void setGood_imges(String good_imges) {
        this.good_imges = good_imges;
    }

    public String getGoog_desc() {
        return goog_desc;
    }

    public void setGoog_desc(String goog_desc) {
        this.goog_desc = goog_desc;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getGood_notes() {
        return good_notes;
    }

    public void setGood_notes(String good_notes) {
        this.good_notes = good_notes;
    }

    public Integer getGood_sort() {
        return good_sort;
    }

    public void setGood_sort(Integer good_sort) {
        this.good_sort = good_sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_good{" +
        ", id=" + id +
        ", good_name=" + good_name +
        ", market_price=" + market_price +
        ", sale_price=" + sale_price +
        ", stock_num=" + stock_num +
        ", exchange_num=" + exchange_num +
        ", sale_status=" + sale_status +
        ", need_address=" + need_address +
        ", thumbnail_url=" + thumbnail_url +
        ", good_imges=" + good_imges +
        ", goog_desc=" + goog_desc +
        ", end_time=" + end_time +
        ", good_notes=" + good_notes +
        ", good_sort=" + good_sort +
        "}";
    }
}
