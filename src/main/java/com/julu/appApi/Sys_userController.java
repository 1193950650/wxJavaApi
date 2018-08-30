package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.julu.dto.CodeMessage;
import com.julu.dto.LoginDto;
import com.julu.entity.Sys_user;
import com.julu.service.IRedisService;
import com.julu.service.ISys_userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-29
 */
@Controller
@RequestMapping("/app/sys_user")
@Api("用户接口")
public class Sys_userController {
    @Autowired
    private ISys_userService sys_userService;
    private IRedisService redisService;
    @GetMapping("/login")
    @ApiOperation("微信用户登录")
    @ApiImplicitParams({@ApiImplicitParam(value="open_id",name="open_id",paramType="query",dataType="String")
    })
    public CodeMessage<LoginDto> login(HttpSession httpSession, String open_id){
        CodeMessage codeMessage=new CodeMessage();
        LoginDto loginDto=new LoginDto();
        EntityWrapper<Sys_user> ew=new EntityWrapper<>();
        ew.eq("open_id",open_id);
                Sys_user sys_user=sys_userService.selectOne(ew);
                if(sys_user!=null){
                    codeMessage.setCode(200);
                    codeMessage.setMsg("登录成功");
                    String login_Token = redisService.putAppSession(httpSession,sys_user);
                    loginDto.setLogin_token(login_Token);
                    codeMessage.setData(login_Token);
               }else{
                    codeMessage.setCode(404);
                    codeMessage.setMsg("用户不存在");
                }
        return codeMessage;
    }

    @GetMapping("/update_user_info")
    @ApiOperation("更新用户微信信息")
    @ApiImplicitParams({@ApiImplicitParam(value="openId",name="openid",paramType="query",dataType="String")
      })
    public CodeMessage update_user_info(HttpSession httpSession, String open_id){
        Sys_user sys_user=new Sys_user();
        //TODO openid获取用户微信信息
        //保存至数据库
        EntityWrapper<Sys_user> ew=new EntityWrapper<>();
        ew.eq("open_id",open_id);
        sys_userService.update(sys_user,ew);
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode(200);
        codeMessage.setMsg("获取用户微信信息成功");
        return codeMessage;
    }

    @GetMapping("/get_user_info")
    @ApiOperation("获取用户微信信息")
    @ApiImplicitParams({@ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage<Sys_user> get_user_info(HttpSession httpSession, String login_token){
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

        //login_token获取用户微信信息
        Sys_user sys_user= redisService.getAppFuser(login_token);
        if(sys_user!=null){
            codeMessage.setCode(200);
            codeMessage.setMsg("获取用户成功");
            codeMessage.setData(sys_user);
        }else{
            codeMessage.setCode(401);
            codeMessage.setMsg("不存在当前用户信息！");
        }
        return codeMessage;
    }

    @GetMapping("/delete_user")
    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="待删除用户id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_user(HttpSession httpSession, String login_token,Integer id){
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

        if(sys_userService.deleteById(id)){
            codeMessage.setCode(200);
            codeMessage.setMsg("删除用户成功");
        }else{
            codeMessage.setCode(500);
            codeMessage.setMsg("服务器异常");
        }
        return codeMessage;
    }

    @GetMapping("/add_user")
    @ApiOperation("新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="用户信息",name="sys_user",paramType="query",dataType="Sys_user")
    })
    public CodeMessage add_user(HttpSession httpSession, String login_token,Sys_user sys_user){
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
        if(sys_userService.insert(sys_user)){
            codeMessage.setCode(200);
            codeMessage.setMsg("新增用户成功");
        }else{
            codeMessage.setCode(500);
            codeMessage.setMsg("新增用户失败");
        }
        return codeMessage;
    }

}

