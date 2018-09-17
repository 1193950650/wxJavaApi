package com.julu.appApi;


import com.julu.dto.CodeMessage;
import com.julu.entity.Integral_exchange_socer;
import com.julu.service.IIntegral_exchange_socerService;
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
 * 积分兑换记录 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "积分兑换记录")
@RequestMapping("/integral_exchange_socer")
public class Integral_exchange_socerController {
    @Autowired
    private IIntegral_exchange_socerService integral_exchange_socerService;
    @Autowired
    private IRedisService redisService;
    @PostMapping("/add_integral_good")
    @ApiOperation("新增兑换")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage add_integral_good(@RequestHeader String login_token, Integral_exchange_socer integral_exchange_socer){
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
        if(integral_exchange_socerService.insert(integral_exchange_socer)){
            codeMessage.setCode(200);
            codeMessage.setMsg("新增积分兑换成功");
        }else{
            codeMessage.setCode(500);
            codeMessage.setMsg("新增积分兑换失败");
        }
        return codeMessage;
    }
}

