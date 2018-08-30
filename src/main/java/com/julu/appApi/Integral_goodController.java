package com.julu.appApi;


import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Integral_good;
import com.julu.service.IIntegral_goodService;
import com.julu.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.LinkedList;

/**
 * <p>
 * 积分商品 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@Controller
@Api("积分商品接口")
@RequestMapping("/integral_good")
public class Integral_goodController {
    @Autowired
    private IIntegral_goodService integral_goodService;
    @Autowired
    private IRedisService redisService;
    @GetMapping("/get_integral_good_list")
    @ApiOperation("获取商品列表")
    @ApiImplicitParams({@ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage<PageDto<Integral_good>> get_integral_good_list(String login_token){
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
        page.setSize(10);
        try {
            page=integral_goodService.selectPage(page);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询商品列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询商品列表失败");
        }
        return codeMessage;
    }

    @GetMapping("/get_integral_good")
    @ApiOperation("根据id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="商品id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Integral_good> get_integral_good(String login_token,Integer id){
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
            codeMessage.setCode(500);
            codeMessage.setMsg("查询商品信息失败");
        }
        return codeMessage;
    }

    @GetMapping("/update_integral_good")
    @ApiOperation("根据id修改商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="商品信息",name="integral_good",paramType="query",dataType="Integral_good")
    })
    public CodeMessage update_integral_good(String login_token,Integral_good integral_good){
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
            codeMessage.setCode(500);
            codeMessage.setMsg("更新商品信息失败");
        }
        return codeMessage;
    }

    @GetMapping("/update_sale_status")
    @ApiOperation("商品上架下架")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="0下架 1上架",name="sale_status",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="商品id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage update_integral_good(String login_token,Integer sale_status,Integer id){
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


    @GetMapping("/delete_integral_good")
    @ApiOperation("删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="商品id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_integral_good(String login_token,Integer id){
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
}

