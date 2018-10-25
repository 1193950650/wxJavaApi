package com.julu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 积分商城订单
 * </p>
 *
 * @author mhs
 * @since 2018-09-20
 */
@ApiModel("积分商城订单")
@TableName("wx_integral_order")
public class Integral_order extends Model<Integral_order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String order_num;
    /**
     * 购买用户id
     */
    @ApiModelProperty("购买用户id")
    private String open_id;
    /**
     * 积分商品id
     */
    @ApiModelProperty("积分商品id")
    private Integer good_id;

    /**
     * 商品类型 0积分 1钱
     */
    @ApiModelProperty("商品类型 0积分 1钱")
    private Integer good_type;

    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private String good_imgs;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String good_name;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品价格")
    private Double good_price;

    /**
     * 订单时间
     */
    @ApiModelProperty("订单时间")
    private Date order_time;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private String address;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    private String code;
    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    private String name;
    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;
    /**
     * 订单状态 0待支付 1取消 2已支付 3待发货 4已发货 5已收货
     */
    @ApiModelProperty("订单状态 0待支付 1取消 2已支付 3待发货 4已发货 5已收货")
    private Integer order_status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Integral_order{" +
        ", id=" + id +
        ", order_num=" + order_num +
        ", open_id=" + open_id +
        ", good_id=" + good_id +
        ", order_time=" + order_time +
        ", address=" + address +
        ", order_status=" + order_status +
        "}";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getGood_imgs() {
        return good_imgs;
    }

    public void setGood_imgs(String good_imgs) {
        this.good_imgs = good_imgs;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public Double getGood_price() {
        return good_price;
    }

    public void setGood_price(Double good_price) {
        this.good_price = good_price;
    }

    public Integer getGood_type() {
        return good_type;
    }

    public void setGood_type(Integer good_type) {
        this.good_type = good_type;
    }
}
