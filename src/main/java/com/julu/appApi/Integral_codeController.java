package com.julu.appApi;


import com.julu.dto.CodeMessage;
import com.julu.entity.Content_imgtext;
import com.julu.service.IIntegral_codeService;
import com.julu.service.IRedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
/**
 * <p>
 * 积分码生成记录 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-09-20
 */
@Api(tags = "积分码生成记录")
@RestController
@RequestMapping("/app/integral_code")
public class Integral_codeController {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IIntegral_codeService integral_codeService;

    @PostMapping("/get_codes")
    @ApiOperation("生成兑换码")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="图文id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Content_imgtext> get_codes(@RequestHeader String login_token, Integer id){
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

        return codeMessage;
    }

}

