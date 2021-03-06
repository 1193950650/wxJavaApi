package com.julu.appApi;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.binarywang.wxpay.service.WxPayService;
import com.julu.dto.CodeMessage;
import com.julu.dto.LoginDto;
import com.julu.entity.Content_config;
import com.julu.entity.Integral_config;
import com.julu.entity.Socer_log;
import com.julu.entity.Sys_user;
import com.julu.service.*;
import com.julu.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@RestController
@RequestMapping("/app/sys_user")
@Api(tags = "用户")
public class Sys_userController {
    @Autowired
    private ISys_userService sys_userService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private ISocer_logService socer_logService;
    @Autowired
    private IContent_configService content_configService;
    @Autowired
    private WxMaService wxService;

    @Autowired
    private WxPayService wxPayService;

    @PostMapping("/login")
    @ApiOperation("后台用户登录")
    @ApiImplicitParams({@ApiImplicitParam(value = "用户姓名", name = "user_name", paramType = "query", dataType = "String")
    })
    public CodeMessage<LoginDto> login(HttpSession httpSession, String user_name,String password) {
        CodeMessage codeMessage = new CodeMessage();
        LoginDto loginDto = new LoginDto();
        EntityWrapper<Sys_user> ew = new EntityWrapper<>();
        ew.eq("user_name", user_name);
        ew.eq("password", MD5.getMD5_32_xx(password));
        Sys_user sys_user = sys_userService.selectOne(ew);
        if (sys_user != null) {
            codeMessage.setCode(200);
            codeMessage.setMsg("登录成功");
            String login_Token = redisService.putAppSession(httpSession, sys_user);
            loginDto.setLogin_token(login_Token);
            codeMessage.setData(login_Token);
        } else {
            codeMessage.setCode(404);
            codeMessage.setMsg("用户不存在");
        }
        return codeMessage;
    }

    @PostMapping("/update_user_info")
    @ApiOperation("更新用户微信信息")
    @ApiImplicitParams({@ApiImplicitParam(value = "open_id", name = "open_id", paramType = "query", dataType = "String")
    })
    public CodeMessage update_user_info(HttpSession httpSession, String open_id) {
        Sys_user sys_user = new Sys_user();
        //TODO openid获取用户微信信息
        //保存至数据库
        EntityWrapper<Sys_user> ew = new EntityWrapper<>();
        ew.eq("open_id", open_id);
        sys_userService.update(sys_user, ew);
        CodeMessage codeMessage = new CodeMessage();
        codeMessage.setCode(200);
        codeMessage.setMsg("获取用户微信信息成功");
        return codeMessage;
    }

    @PostMapping("/get_user_info")
    @ApiOperation("获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(value = "login_token", name = "login_token", paramType = "query", dataType = "String")
    })
    public CodeMessage<Sys_user> get_user_info(HttpSession httpSession, @RequestHeader String login_token) {
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

        //login_token获取用户微信信息
        Sys_user sys_user = redisService.getAppFuser(login_token);
        if (sys_user != null) {
            codeMessage.setCode(200);
            codeMessage.setMsg("获取用户成功");
            codeMessage.setData(sys_user);
        } else {
            codeMessage.setCode(401);
            codeMessage.setMsg("不存在当前用户信息！");
        }
        return codeMessage;
    }

    @PostMapping("/delete_user")
    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "login_token", name = "login_token", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "待删除用户id", name = "id", paramType = "query", dataType = "Integer")
    })
    public CodeMessage delete_user(HttpSession httpSession, @RequestHeader String login_token, Integer id) {
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

        if (sys_userService.deleteById(id)) {
            codeMessage.setCode(200);
            codeMessage.setMsg("删除用户成功");
        } else {
            codeMessage.setCode(500);
            codeMessage.setMsg("服务器异常");
        }
        return codeMessage;
    }

    @PostMapping("/add_user")
    @ApiOperation("用户新增+登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "login_token", name = "login_token", paramType = "query", dataType = "String")
    })
    public CodeMessage<LoginDto> add_user(HttpSession httpSession, Sys_user sys_user,String code) {
        CodeMessage<LoginDto> codeMessage = new CodeMessage();
        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
            sys_user.setOpen_id(openId);
        } catch (Exception e) {
            System.out.print(e);
             codeMessage.setCode(500);
             codeMessage.setMsg("获取不到openid");
             return codeMessage;
        }

        EntityWrapper<Sys_user> ew = new EntityWrapper<>();
        ew.eq("open_id", sys_user.getOpen_id());
        List<Sys_user> sys_userList = sys_userService.selectList(ew);
        if (sys_userList.size() > 0) {
            //zcg
            sys_user = sys_userList.get(0);
            sys_user.setLogin_num(sys_user.getLogin_num() + 1);
            sys_user.setLast_login_time(new Date());
            sys_userService.updateById(sys_user);
            codeMessage.setCode(200);
            codeMessage.setMsg("用户登录成功");
        } else {
            if (sys_user.getOpen_id() != null && !"".equals(sys_user.getOpen_id()))
                sys_user.setLogin_num(1);
            sys_user.setLast_login_time(new Date());
            sys_user.setDel_flag(0);
            Content_config content_config=content_configService.selectById(1);
            sys_user.setSocer(content_config.getLogin_integral_num());
            sys_userService.insert(sys_user);
            Socer_log socer_log=new Socer_log();
            socer_log.setType(1);
            socer_log.setDel_flag(0);
            socer_log.setOpen_id(sys_user.getOpen_id());
            socer_log.setCreate_date(new Date());
            socer_log.setSocer_num(content_config.getBrowse_integral_num());
            socer_logService.insert(socer_log);
            codeMessage.setCode(200);
            codeMessage.setMsg("注册并登录成功");
        }
        LoginDto loginDto = new LoginDto();
        String login_token = redisService.putAppSession(httpSession, sys_user);
        loginDto.setLogin_token(login_token);
        codeMessage.setData(loginDto);
        return codeMessage;
    }

}

