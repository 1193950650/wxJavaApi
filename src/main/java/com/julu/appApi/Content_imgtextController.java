package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Content_imgtext;
import com.julu.service.IContent_imgtextService;
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
    private IRedisService redisService;
    @PostMapping("/get_content_imgtext_list")
    @ApiOperation("获取图文列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="所属分类id",name="type_id",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="显示封面缩略图 0不显示 1显示",name="is_show",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="%图文名称%",name="name",paramType="query",dataType="String")
    })
    public CodeMessage<PageDto<Content_imgtext>> get_content_imgtext_list(String login_token,Integer type_id, Integer is_show, String name){
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
        Page<Content_imgtext> page=new Page<>();
        EntityWrapper<Content_imgtext> ew=new EntityWrapper<>();
        ew.eq("type_id",type_id);
        ew.eq("is_show",is_show);
        ew.like(true,"name",name);
        page.setSize(10);
        try {
            page=content_imgtextService.selectPage(page,ew);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询图文列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
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
    public CodeMessage<Content_imgtext> get_content_type(String login_token,Integer id){
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
            Content_imgtext content_imgtext=content_imgtextService.selectById(id);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询图文信息成功");
            codeMessage.setData(content_imgtext);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询图文信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_content_imgtext")
    @ApiOperation("根据id修改图文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="图文信息",name="integral_good",paramType="query",dataType="Integral_good")
    })
    public CodeMessage update_content_type(String login_token,Content_imgtext content_imgtext){
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
                codeMessage.setMsg("图文分类信息成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("图文分类信息失败");
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
    public CodeMessage delete_content_imgtext(String login_token,Integer id){
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

