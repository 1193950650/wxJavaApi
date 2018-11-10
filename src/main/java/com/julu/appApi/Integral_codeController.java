package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.julu.dto.CodeMessage;
import com.julu.entity.Integral_code;
import com.julu.entity.Socer_log;
import com.julu.entity.Sys_user;
import com.julu.service.*;
import com.julu.utils.Redeem;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    @Autowired
    private ISys_userService sys_userService;

    @Autowired
    private ISocer_logService socer_logService;

    @PostMapping("/get_code")
    @ApiOperation("生成兑换码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "生成个数", name = "num", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(value = "积分", name = "integral_num", paramType = "query", dataType = "Integer")
    })
    public CodeMessage<List<Integral_code>> get_code(Integer num,Integer integral_num) {
        CodeMessage codeMessage = new CodeMessage();

        List<Integral_code> integral_codes=new LinkedList<>();
        LinkedList<String> codes = Redeem.create((byte) 1, num, 12, Redeem.password);
        for (String code : codes) {
            Integral_code integralCode=new Integral_code();
            integralCode.setCode(code);
            integralCode.setIntegral_num(integral_num);
            integralCode.setIs_used(0);
            integral_codes.add(integralCode);
        }
        try {
            if(integral_codeService.insertBatch(integral_codes)){
                codeMessage.setCode(200);
                codeMessage.setMsg("生成兑换码成功");
                codeMessage.setData(integral_codes);
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("生成兑换码失败");
            }
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("生成兑换码失败");
        }
        return codeMessage;
    }
    @PostMapping("/use_code")
    @ApiOperation("使用兑换码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "login_token", name = "login_token", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "兑换码", name = "code", paramType = "query", dataType = "String")
    })
    public CodeMessage<List<Integral_code>> use_code( @RequestHeader  String login_token, String code) {
        CodeMessage codeMessage = new CodeMessage();
        if (login_token == null || "".equals(login_token)) {
            codeMessage.setCode(403);
            codeMessage.setMsg("token丢失");
            return codeMessage;
        }
        if (!redisService.isAppLogin(login_token, true)) {
            codeMessage.setCode(401);
            codeMessage.setMsg("未登录");
            return codeMessage;
        }
        try {
            Sys_user sys_user=redisService.getAppFuser(login_token);
            EntityWrapper<Integral_code> ew=new EntityWrapper<>();
            ew.eq("code",code);
            ew.eq("is_used",0);
            List<Integral_code> integral_codes=integral_codeService.selectList(ew);
            if(integral_codes.size()>0){
                Integral_code integral_code=integral_codes.get(0);
                integral_code.setIs_used(1);
                integral_code.setOpen_id(sys_user.getOpen_id());
                if(integral_codeService.updateById(integral_code)){
                    sys_user.setSocer(sys_user.getSocer()+integral_code.getIntegral_num());
                    if(sys_userService.updateById(sys_user)){
                        Socer_log socer_log=new Socer_log();
                        socer_log.setType(3);
                        socer_log.setDel_flag(0);
                        socer_log.setCreate_date(new Date());
                        socer_log.setOpen_id(sys_user.getOpen_id());
                        socer_log.setSocer_num(integral_code.getIntegral_num());
                        socer_logService.insert(socer_log);
                        codeMessage.setCode(200);
                        codeMessage.setMsg("兑换成功");
                    }else{
                        codeMessage.setCode(500);
                        codeMessage.setMsg("兑换失败");
                    }
                }else{
                    codeMessage.setCode(500);
                    codeMessage.setMsg("兑换失败");
                }
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("兑换失败");
            }
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("兑换失败");
        }
        return codeMessage;
    }
}

