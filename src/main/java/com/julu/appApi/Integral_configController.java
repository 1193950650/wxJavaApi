package com.julu.appApi;


import com.julu.dto.CodeMessage;
import com.julu.entity.Integral_config;
import com.julu.entity.Integral_good;
import com.julu.service.IIntegral_configService;
import com.julu.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分商城配置 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@RestController
@Api(tags = "积分商城配置接口")
@RequestMapping("/app/integral_config")
public class Integral_configController {
    @Autowired
    private IIntegral_configService iIntegral_configService;
    @Autowired
    private IRedisService redisService;

    @GetMapping("/get_integral_config")
    @ApiOperation("获取积分商城配置")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage<Integral_config> get_integral_config(String login_token){
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
            Integral_config integral_config=iIntegral_configService.selectById(1);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询积分商城配置成功");
            codeMessage.setData(integral_config);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询积分商城配置失败");
        }
        return codeMessage;
    }

    @GetMapping("/update_integral_config")
    @ApiOperation("修改积分商城配置")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="积分商城配置信息",name="integral_config",paramType="query",dataType="Integral_config")
    })
    public CodeMessage get_integral_config(String login_token,Integral_config integral_config){
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
            if(iIntegral_configService.updateById(integral_config)){
                codeMessage.setCode(200);
                codeMessage.setMsg("修改积分商城配置成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("修改积分商城配置失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("修改积分商城配置失败");
            return codeMessage;
        }
        return codeMessage;
    }

}

