package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.Content_commentDto;
import com.julu.dto.PageDto;
import com.julu.entity.*;
import com.julu.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 内容-评论 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "内容-评论")
@RequestMapping("/app/content_comment")
public class Content_commentController {
    @Autowired
    private IContent_commentService content_commentService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IContent_configService content_configService;
    @Autowired
    private ISocer_logService socer_logService;
    @Autowired
    private IContent_imgtextService content_imgtextService;
    @Autowired
    private IContent_replyService content_replyService;
    @Autowired
    private ISys_userService sys_userService;
    @PostMapping("/get_content_comment_list")
    @ApiOperation("根据图文id获取评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="图文id",name="id",paramType="query",dataType="Integer")

    })
    public CodeMessage<List<Content_commentDto>> get_content_comment_list(@RequestHeader String login_token, Integer id){
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
        EntityWrapper<Content_comment> ew=new EntityWrapper<>();
        ew.eq("imgtext_id",id);
        try {
            LinkedList<Content_commentDto> content_commentDtos=new LinkedList<>();
            List<Content_comment> list=content_commentService.selectList(ew);
            for (Content_comment content_comment: list){
                EntityWrapper<Content_reply> ew1=new EntityWrapper<>();
                Content_commentDto content_commentDto=new Content_commentDto();
                ew1.eq("comment_id",content_comment.getId());
                List<Content_reply> content_replies=content_replyService.selectList(ew1);
                BeanUtils.copyProperties(content_comment,content_commentDto);
                content_commentDto.setContent_replies(content_replies);
                content_commentDtos.add(content_commentDto);
            }
            codeMessage.setCode(200);
            codeMessage.setMsg("查询评论列表成功");
            codeMessage.setData(content_commentDtos);
            Content_config content_config=content_configService.selectById(1);
            sys_user.setSocer(sys_user.getSocer()+content_config.getBrowse_integral_num());
            sys_userService.updateById(sys_user);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询评论列表失败");
        }
        return codeMessage;
    }


    @PostMapping("/get_content_comment")
    @ApiOperation("根据id获取评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="评论id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Content_comment> get_content_comment(@RequestHeader String login_token,Integer id){
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
            Content_comment content_comment=content_commentService.selectById(id);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询评论信息成功");
            codeMessage.setData(content_comment);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询评论信息失败");
        }
        return codeMessage;
    }
    @PostMapping("/delete_content_comment")
    @ApiOperation("删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="评论id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_content_comment(@RequestHeader String login_token,Integer id){
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
            if(content_commentService.deleteById(id)){
                codeMessage.setCode(200);
                codeMessage.setMsg("删除评论成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("删除评论失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("删除评论失败");
        }
        return codeMessage;
    }

    @PostMapping("/add_content_comment")
    @ApiOperation("新增评论")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage add_content_comment(@RequestHeader String login_token, Content_comment content_comment){
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
            content_comment.setIs_show(1);
            content_comment.setOpen_id(sys_user.getOpen_id());
            content_comment.setUser_icon(sys_user.getIcon());
            content_comment.setUser_name(sys_user.getUser_name());
            content_comment.setAdd_time(new Date());
            if(content_commentService.insert(content_comment)){
                Content_imgtext imgtext=content_imgtextService.selectById(content_comment.getImgtext_id());
                imgtext.setWrite_num(imgtext.getWrite_num()==null?(0+1):(imgtext.getWrite_num()+1));
                content_imgtextService.updateById(imgtext);
                codeMessage.setCode(200);
                codeMessage.setMsg("新增评论成功");
                Content_config content_config=content_configService.selectById(1);
                sys_user.setSocer(sys_user.getSocer()+content_config.getComment_integral_num());
                sys_userService.updateById(sys_user);
                Socer_log socer_log=new Socer_log();
                socer_log.setType(0);
                socer_log.setDel_flag(0);
                socer_log.setCreate_date(new Date());
                socer_log.setOpen_id(sys_user.getOpen_id());
                socer_log.setSocer_num(content_config.getComment_integral_num());
                socer_logService.insert(socer_log);
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("新增评论失败");
            }
        }catch (Exception e){
            System.out.println(e);
            codeMessage.setCode(500);
            codeMessage.setMsg("新增评论失败");
        }
        return codeMessage;
    }

    @PostMapping("/add_content_comment_reply")
    @ApiOperation("新增评论回复")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage add_content_comment_reply(@RequestHeader String login_token,Content_reply content_reply){
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
            content_reply.setOpenid(sys_user.getOpen_id());
            content_reply.setUser_name(sys_user.getUser_name());
            content_reply.setAdd_time(new Date());
            content_reply.setDel_flag(0);
            content_reply.setCreate_date(new Date());
            if(content_replyService.insert(content_reply)){
                codeMessage.setCode(200);
                codeMessage.setMsg("新增评论回复成功");
                Content_config content_config=content_configService.selectById(1);
                sys_user.setSocer(sys_user.getSocer()+content_config.getBrowse_integral_num());
                sys_userService.updateById(sys_user);
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("新增评论回复失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("新增评论回复失败");
        }
        return codeMessage;
    }
}

