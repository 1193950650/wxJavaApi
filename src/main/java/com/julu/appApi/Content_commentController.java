package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Content_comment;
import com.julu.service.IContent_commentService;
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
    @GetMapping("/get_content_comment_list")
    @ApiOperation("获取评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="显示 0不显示 1显示",name="type_id",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="%图文名称%",name="name",paramType="query",dataType="String")
    })
    public CodeMessage<PageDto<Content_comment>> get_content_comment_list(String login_token,Integer is_show, String name){
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
        Page<Content_comment> page=new Page<>();
        EntityWrapper<Content_comment> ew=new EntityWrapper<>();
        ew.eq("is_show",is_show);
        ew.like(true,"name",name);
        page.setSize(10);
        try {
            page=content_commentService.selectPage(page,ew);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询评论列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询评论列表失败");
        }
        return codeMessage;
    }
    @GetMapping("/get_content_comment")
    @ApiOperation("根据id获取评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="评论id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Content_comment> get_content_comment(String login_token,Integer id){
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
    @GetMapping("/delete_content_comment")
    @ApiOperation("删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="评论id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_content_comment(String login_token,Integer id){
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
}

