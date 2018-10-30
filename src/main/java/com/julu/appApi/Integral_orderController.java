package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Integral_good;
import com.julu.entity.Integral_order;
import com.julu.entity.Sys_user;
import com.julu.service.IIntegral_orderService;
import com.julu.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分商城订单 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "积分商城订单")
@RequestMapping("/app/integral_order")
public class Integral_orderController {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IIntegral_orderService iIntegral_orderService;
    @PostMapping("/get_Integral_order_list")
    @ApiOperation("获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="订单状态：0待支付 1取消 2已支付 3待发货 4已发货 5已收货",name="order_status",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="页码",name="current",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="根据订单时间排序：0升序 1降序",name="sort",paramType="query",dataType="Integer")
    })
    public CodeMessage<PageDto<Integral_order>> get_integral_good_list(@RequestHeader String login_token, Integer order_status, Integer sort, Integer current){
        CodeMessage codeMessage=new CodeMessage();
        if(login_token==null || "".equals(login_token)){
            codeMessage.setCode(403);
            codeMessage.setMsg("token丢失");
            return  codeMessage;
        }
        if(!redisService.isAppLogin(login_token,true)){
            codeMessage.setCode(401);
            codeMessage.setMsg("未登录");
            return codeMessage;
        }
        Sys_user sys_user= redisService.getAppFuser(login_token);

        Page<Integral_order> page=new Page<>();
        EntityWrapper<Integral_order> ew=new EntityWrapper<>();
        if(order_status!=null && !"".equals(order_status)){
            ew.eq("order_status",order_status);
        }
        ew.eq("open_id",sys_user.getOpen_id());
        if(sort!=null && !"".equals(sort)){
            if(sort==0){
                ew.orderBy("order_time",false);
            }else{
                ew.orderBy("order_time",true);
            }
        }

        page.setSize(10);
        if(current!=null && current>0){
            page.setCurrent(current);
        }
        try {
            page=iIntegral_orderService.selectPage(page,ew);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询订单列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询订单列表失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_oder_status")
    @ApiOperation("修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="订单状态：0待支付 1取消 2已支付 3待发货 4已发货 5已收货",name="order_status",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="订单id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage update_integral_good(@RequestHeader String login_token,Integer order_status,Integer id){
        CodeMessage codeMessage=new CodeMessage();
        if(login_token==null || "".equals(login_token)){
            codeMessage.setCode(403);
            codeMessage.setMsg("token丢失");
            return  codeMessage;
        }
        if(!redisService.isAppLogin(login_token,true)){
            codeMessage.setCode(401);
            codeMessage.setMsg("未登录");
            return codeMessage;
        }
        Integral_order integral_order=iIntegral_orderService.selectById(id);
        integral_order.setOrder_status(order_status);
        try {
            if(iIntegral_orderService.updateById(integral_order)){
                codeMessage.setCode(200);
                codeMessage.setMsg("更新订单状态成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("更新订单状态失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("更新订单状态失败");
        }
        return codeMessage;
    }

}

