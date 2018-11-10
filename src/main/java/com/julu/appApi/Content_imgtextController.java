package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.*;
import com.julu.service.*;
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
 * 内容-图文 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "内容-图文")
@RequestMapping("/app/content_imgtext")
public class Content_imgtextController {
    @Autowired
    private IContent_imgtextService content_imgtextService;
    @Autowired
    private IContent_imgtext_dzlogService content_imgtext_dzlogService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private ISocer_logService socer_logService;
    @Autowired
    private IContent_configService content_configService;
    @Autowired
    private ISys_userService sys_userService;
    @PostMapping("/get_content_imgtext_list")
    @ApiOperation("获取图文列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="当前id",name="id",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="所属分类id",name="type_id",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="显示封面缩略图 0不显示 1显示",name="is_show",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="%图文名称%",name="name",paramType="query",dataType="String"),
            @ApiImplicitParam(value="经度,维度",name="point",paramType="query",dataType="String"),
            @ApiImplicitParam(value="排序 1：最新 2：热门 3：距离",name="order",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="页码",name="current",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="1表示查询自己的 其他表示所有",name="is_my",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="所属模块 0首页-状态 1资讯-图文 2资讯-视频 3资讯-图文+视频",name="modle",paramType="query",dataType="Integer")
    })
    public CodeMessage<PageDto<Content_imgtext>> get_content_imgtext_list(@RequestHeader String login_token,Integer id,Integer type_id, Integer is_show,Integer order,String point,Integer current, String name,Integer modle,Integer is_my){
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
        Sys_user sys_user=redisService.getAppFuser(login_token);
        Page<Content_imgtext> page=new Page<>();
        EntityWrapper<Content_imgtext> ew=new EntityWrapper<>();
        if(type_id!=null && !"".equals(type_id)){
            ew.eq("type_id",type_id);
        }
        if(is_my!=null && is_my==1){
         ew.eq("open_id",sys_user.getOpen_id());
        }
        if(id!=null && !"".equals(id)){
            ew.ne("id",id);
        }
        if(is_show!=null){
            ew.eq("is_show",is_show);
        }else{
            ew.eq("is_show",1);
        }
        if(name!=null && !"".equals(name)){
            ew.like(true,"name",name);
        }
        if(modle!=null && !"".equals(modle)){
            if(modle==0){
                ew.eq("modle",0);
            }else if(modle==3){
                ew.andNew().eq("modle",1).or().eq("modle",2);
            } else{
                ew.eq("modle",modle);
            }

        }

        if(order!=null && order>0){
            if(order==1){
                ew.orderBy("add_time",false);
            }
            if(order==2){
                ew.orderBy("see_num",false);
            }
            if(order==3){
                String[] points=point.split(",");
                ew.orderBy("SQRT(("+points[0]+"-longitude)*("+points[0]+"-longitude)+("+points[1]+"-latitude)*("+points[1]+"-latitude)) ",false);

            }
        }

        page.setSize(10);
        if(current!=null && current>0){
            page.setCurrent(current);
        }
        try {
            page=content_imgtextService.selectPage(page,ew);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询图文列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("查询图文列表失败");
        }
        return codeMessage;
    }

    @PostMapping("/get_content_imgtext")
    @ApiOperation("根据id获取图文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="图文id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Content_imgtext> get_content_type(@RequestHeader String login_token,Integer id){
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
        Sys_user sys_user=redisService.getAppFuser(login_token);
        try {
            Content_imgtext content_imgtext=content_imgtextService.selectById(id);
            content_imgtext.setSee_num(content_imgtext.getSee_num()==null?1:(content_imgtext.getSee_num()+1));
            content_imgtextService.updateById(content_imgtext);
            Content_config content_config=content_configService.selectById(1);
            sys_user.setSocer(sys_user.getSocer()+content_config.getBrowse_integral_num());
            sys_userService.updateById(sys_user);
            Socer_log socer_log=new Socer_log();
            socer_log.setType(1);
            socer_log.setDel_flag(0);
            socer_log.setOpen_id(sys_user.getOpen_id());
            socer_log.setCreate_date(new Date());
            socer_log.setSocer_num(content_config.getBrowse_integral_num());
            socer_logService.insert(socer_log);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询图文信息成功");
            codeMessage.setData(content_imgtext);
        }catch (Exception e){
            System.out.print(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("查询图文信息失败");
        }
        return codeMessage;
    }
    @PostMapping("/add_dz_num")
    @ApiOperation("增加点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="图文id",name="id",paramType="query",dataType="Integer"),
    })
    public CodeMessage add_dz_num(@RequestHeader String login_token,Integer id){
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
            Sys_user sys_user=redisService.getAppFuser(login_token);
            EntityWrapper<Content_imgtext_dzlog> ew=new EntityWrapper<>();
            ew.eq("imgtext_id",id);
            ew.eq("open_id",sys_user.getOpen_id());
            Integer count=content_imgtext_dzlogService.selectList(ew).size();
            if(count==0){
                Content_imgtext content_imgtext=content_imgtextService.selectById(id);
                content_imgtext.setDz_num(content_imgtext.getDz_num()+1);
                if(content_imgtextService.updateById(content_imgtext)){
                    Content_imgtext_dzlog content_imgtext_dzlog=new Content_imgtext_dzlog();
                    content_imgtext_dzlog.setImgtext_id(content_imgtext.getId());
                    content_imgtext_dzlog.setOpen_id(sys_user.getOpen_id());
                    content_imgtext_dzlogService.insert(content_imgtext_dzlog);
                    codeMessage.setCode(200);
                    codeMessage.setMsg("点赞成功");
                }else{
                    codeMessage.setCode(500);
                    codeMessage.setMsg("点赞失败");
                }
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("点赞失败,已经点赞");
            }

        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("点赞失败");
        }
        return codeMessage;
    }
    @PostMapping("/add_content_imgtext")
    @ApiOperation("新增图文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage add_content_imgtext(@RequestHeader String login_token,Content_imgtext content_imgtext){
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
            Sys_user sys_user=redisService.getAppFuser(login_token);
            content_imgtext.setOpenid(sys_user.getOpen_id());
            content_imgtext.setAuthor(sys_user.getUser_name());
            content_imgtext.setAuthor_icon(sys_user.getIcon());
            content_imgtext.setAdd_time(new Date());
            content_imgtext.setIs_pay(0);
            content_imgtext.setIs_top(0);
            content_imgtext.setDz_num(0);
            content_imgtext.setSee_num(0);
            content_imgtext.setWrite_num(0);
            content_imgtext.setIs_show(1);
            if(content_imgtextService.insert(content_imgtext)){
                Content_config content_config=content_configService.selectById(1);
                sys_user.setSocer(sys_user.getSocer()+content_config.getArticles_integral_num());
                sys_userService.updateById(sys_user);
                Socer_log socer_log=new Socer_log();
                socer_log.setType(2);
                socer_log.setDel_flag(0);
                socer_log.setOpen_id(sys_user.getOpen_id());
                socer_log.setCreate_date(new Date());
                socer_log.setSocer_num(content_config.getArticles_integral_num());
                socer_logService.insert(socer_log);
                codeMessage.setCode(200);
                codeMessage.setMsg("新增图文分类信息成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("新增图文分类信息失败");
            }
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("新增图文信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_content_imgtext")
    @ApiOperation("根据id修改图文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage update_content_type(@RequestHeader String login_token, Content_imgtext content_imgtext){
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

            if(content_imgtextService.updateById(content_imgtext)){
                codeMessage.setCode(200);
                codeMessage.setMsg("更新图文分类信息成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("更新图文分类信息失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("更新图文信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/delete_content_imgtext")
    @ApiOperation("删除图文")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="图文id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_content_imgtext(@RequestHeader String login_token,Integer id){
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
            if(content_imgtextService.deleteById(id)){
                codeMessage.setCode(200);
                codeMessage.setMsg("删除分类成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("删除图文失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("删除图文失败");
        }
        return codeMessage;
    }
}

