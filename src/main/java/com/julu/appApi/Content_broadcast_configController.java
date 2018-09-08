package com.julu.appApi;


import com.julu.dto.CodeMessage;
import com.julu.entity.Content_broadcast_config;
import com.julu.service.IContent_broadcast_configService;
import com.julu.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容-轮播配置 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "内容-轮播配")
@RequestMapping("/app/content_broadcast_config")
public class Content_broadcast_configController {
    @Autowired
    private IContent_broadcast_configService content_broadcast_configService;
    @Autowired
    private IRedisService redisService;
    @PostMapping("/get_content_broadcast_config")
    @ApiOperation("获取轮播配置")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage<Content_broadcast_config> get_content_broadcast_config(String login_token){
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
            Content_broadcast_config content_broadcast_config=content_broadcast_configService.selectById(1);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询轮播配置成功");
            codeMessage.setData(content_broadcast_config);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询轮播配置失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_content_broadcast_config")
    @ApiOperation("修改轮播配置")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage update_content_broadcast_config(String login_token,Content_broadcast_config content_broadcast_config){
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
            if(content_broadcast_configService.updateById(content_broadcast_config)){
                codeMessage.setCode(200);
                codeMessage.setMsg("修改轮播配置成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("修改轮播配置失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("修改轮播配置失败");
            return codeMessage;
        }
        return codeMessage;
    }

}

