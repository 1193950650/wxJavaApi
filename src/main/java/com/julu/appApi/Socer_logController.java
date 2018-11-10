package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Content_type;
import com.julu.entity.Integral_good;
import com.julu.entity.Socer_log;
import com.julu.entity.Sys_user;
import com.julu.service.IRedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import com.julu.service.ISocer_logService;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  积分纪录 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-10-29
 */
@Api(tags = "积分纪录")
@RestController
@RequestMapping("/app/socer_log")
public class Socer_logController {
   @Autowired
   private ISocer_logService Socer_logServiceImpl;
   @Autowired
   private IRedisService redisService;
   @PostMapping("/get_content_type_list")
   @ApiOperation("获取积分纪录列表")
   @ApiImplicitParams({
           @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
           @ApiImplicitParam(value="纪录类型 0评论+ 1浏览+ 2发布+ 3兑换码+ 4转发+ 5兑换商品-",name="type",paramType="query",dataType="Integer"),
           @ApiImplicitParam(value="页码",name="current",paramType="query",dataType="Integer")
   })
   public CodeMessage<PageDto<Socer_log>> get_content_type_list(@RequestHeader String login_token, String type, Integer current){
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
      Page<Socer_log> page=new Page<>();
      Sys_user sys_user=redisService.getAppFuser(login_token);

      EntityWrapper<Socer_log> ew=new EntityWrapper<>();
      if(type!=null && !"".equals(type)){
         ew.like(true,"type",type);
      }
      ew.eq("open_id",sys_user.getOpen_id());
      ew.eq("del_flag",0);
      ew.orderBy(true,"create_date");
      page.setSize(20);
      if(current!=null && current>0){
         page.setCurrent(current);
      }
      try {
         page=Socer_logServiceImpl.selectPage(page,ew);
         page.setTotal(sys_user.getSocer());
         codeMessage.setCode(200);
         codeMessage.setMsg("查询积分纪录成功");
         codeMessage.setData(page);
      }catch (Exception e){
         System.out.println(e);
         codeMessage.setCode(500);
         codeMessage.setMsg("查询积分纪录失败");
      }
      return codeMessage;
   }



}

