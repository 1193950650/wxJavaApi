package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.*;
import com.julu.service.*;
import com.julu.utils.Redeem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 积分商品 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@RestController
@Api(tags = "积分商品接口")
@RequestMapping("/app/integral_good")
public class Integral_goodController {
    @Autowired
    private IIntegral_goodService integral_goodService;
    @Autowired
    private IIntegral_orderService integral_orderService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private ISocer_logService socer_logService;
    @Autowired
    private IContent_configService content_configService;
    @Autowired
    private ISys_userService sys_userService;
    @PostMapping("/get_integral_good_list")
    @ApiOperation("获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="%商品名称%",name="good_name",paramType="query",dataType="String"),
            @ApiImplicitParam(value="页码",name="current",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="排序：0升序 1降序",name="sort",paramType="query",dataType="Integer")
    })
    public CodeMessage<PageDto<Integral_good>> get_integral_good_list(@RequestHeader String login_token, String good_name, Integer sort,Integer current){
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
        Page<Integral_good> page=new Page<>();
        EntityWrapper<Integral_good> ew=new EntityWrapper<>();
        if(good_name!=null && !"".equals(good_name)){
            ew.eq("good_name",good_name);
        }
        if(sort!=null && !"".equals(sort)){
            if(sort==0){
                ew.orderBy("sale_price",false);
            }else{
                ew.orderBy("sale_price",true);
            }

        }

        page.setSize(10);
        if(current!=null && current>0){
            page.setCurrent(current);
        }
        try {
            page=integral_goodService.selectPage(page,ew);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询商品列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询商品列表失败");
        }
        return codeMessage;
    }

    @PostMapping("/get_integral_good")
    @ApiOperation("根据id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="商品id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Integral_good> get_integral_good(@RequestHeader String login_token,Integer id){
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
        try {
            Integral_good integral_good=integral_goodService.selectById(id);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询商品信息成功");
            codeMessage.setData(integral_good);
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("查询商品信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_integral_good")
    @ApiOperation("根据id修改商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage update_integral_good(@RequestHeader String login_token,Integral_good integral_good){
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
        try {
            if(integral_goodService.updateById(integral_good)){
                codeMessage.setCode(200);
                codeMessage.setMsg("更新商品信息成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("更新商品信息失败");
            }
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("更新商品信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_sale_status")
    @ApiOperation("商品上架下架")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="0下架 1上架",name="sale_status",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="商品id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage update_integral_good(@RequestHeader String login_token,Integer sale_status,Integer id){
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
        Integral_good integral_good=integral_goodService.selectById(id);
        integral_good.setSale_status(sale_status);
        try {
            if(integral_goodService.updateById(integral_good)){
                codeMessage.setCode(200);
                codeMessage.setMsg("更新商品商品上架下架成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("更新商品商品上架下架失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("更新商品商品上架下架失败");
        }
        return codeMessage;
    }


    @PostMapping("/delete_integral_good")
    @ApiOperation("删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="商品id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_integral_good(@RequestHeader String login_token,Integer id){
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
        try {
            if(integral_goodService.deleteById(id)){
                codeMessage.setCode(200);
                codeMessage.setMsg("删除商品成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("删除商品失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("删除商品失败");
        }
        return codeMessage;
    }

    @PostMapping("/add_integral_good")
    @ApiOperation("新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage add_integral_good(@RequestHeader String login_token,Integral_good integral_good){
        CodeMessage codeMessage=new CodeMessage();
        if(login_token==null || "".equals(login_token)){
            codeMessage.setCode(403);
            codeMessage.setMsg("token丢失");
            return codeMessage;
        }
        if(!redisService.isAppLogin(login_token,true)){
            codeMessage.setCode(401);
            codeMessage.setMsg("未登录");
            return codeMessage;
        }
        if(integral_goodService.insert(integral_good)){
            codeMessage.setCode(200);
            codeMessage.setMsg("新增商品成功");
        }else{
            codeMessage.setCode(500);
            codeMessage.setMsg("新增商品失败");
        }
        return codeMessage;
    }

    @PostMapping("/buy_good")
    @ApiOperation("兑换商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="商品ID",name="good_id",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="收获地址",name="address",paramType="query",dataType="String"),
            @ApiImplicitParam(value="邮编",name="code",paramType="query",dataType="String"),
            @ApiImplicitParam(value="收货人姓名",name="name",paramType="query",dataType="String"),
            @ApiImplicitParam(value="联系电话",name="phone",paramType="query",dataType="String")
    })
    public CodeMessage buy_good(@RequestHeader String login_token,Integer good_id,String address,String code,String name,String phone){
        CodeMessage codeMessage=new CodeMessage();
        if(login_token==null || "".equals(login_token)){
            codeMessage.setCode(403);
            codeMessage.setMsg("token丢失");
            return codeMessage;
        }
        if(!redisService.isAppLogin(login_token,true)){
            codeMessage.setCode(401);
            codeMessage.setMsg("未登录");
            return codeMessage;
        }
        Sys_user sys_user=redisService.getAppFuser(login_token);
        Integral_good integral_good=integral_goodService.selectById(good_id);
        if(integral_good.getIntegral_num()>sys_user.getSocer()){
            codeMessage.setCode(3002);
            codeMessage.setMsg("积分不足");
            return  codeMessage;
        }else if(integral_good.getStock_num()<1){
            codeMessage.setCode(500);
            codeMessage.setMsg("库存不足");
            return codeMessage;
        }
        else{
            sys_user.setSocer(sys_user.getSocer()-integral_good.getIntegral_num());
            integral_good.setExchange_num(integral_good.getExchange_num()+1);
            integral_good.setStock_num(integral_good.getIntegral_num()+1);
            //生成订单
            Integral_order integral_order=new Integral_order();
            integral_order.setGood_id(integral_good.getId());
            integral_order.setGood_type(integral_good.getGood_type());
            integral_order.setOrder_time(new Date());
            integral_order.setGood_imgs(integral_good.getGood_imges());
            integral_order.setGood_name(integral_good.getGood_name());
            integral_order.setGood_socer_num(integral_good.getIntegral_num());
            integral_order.setCode(code);
            integral_order.setName(name);
            integral_order.setPhone(phone);
            integral_order.setDel_flag(0);
            integral_order.setOpen_id(sys_user.getOpen_id());
            integral_order.setOrder_num(Redeem.create((byte)1,1,12,Redeem.password).get(0));
            integral_order.setOrder_status(2);
            integral_order.setAddress(address);
            if(sys_userService.updateById(sys_user) &&integral_goodService.updateById(integral_good) && integral_orderService.insert(integral_order)){
                codeMessage.setCode(200);
                codeMessage.setMsg("兑换成功");
                sys_user.setSocer(sys_user.getSocer()-integral_good.getIntegral_num());
                sys_userService.updateById(sys_user);
                Socer_log socer_log=new Socer_log();
                socer_log.setType(5);
                socer_log.setCreate_date(new Date());
                socer_log.setDel_flag(0);
                socer_log.setOpen_id(sys_user.getOpen_id());
                socer_log.setSocer_num(-integral_good.getIntegral_num());
                socer_logService.insert(socer_log);
                return  codeMessage;
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("兑换失败");
                return codeMessage;
            }
        }
    }

}

