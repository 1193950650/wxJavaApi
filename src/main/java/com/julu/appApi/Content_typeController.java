package com.julu.appApi;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.julu.dto.CodeMessage;
import com.julu.entity.Content_type;
import com.julu.service.IContent_typeService;
import com.julu.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 内容-分类 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "内容-分类")
@RequestMapping("/app/content_type")
public class Content_typeController {
    @Autowired
    private IContent_typeService content_typeService;
    @Autowired
    private IRedisService redisService;
    @PostMapping("/get_content_type_list")
    @ApiOperation("获取分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="%分类名称%",name="name",paramType="query",dataType="String"),
            @ApiImplicitParam(value="所属模块 0首页 1资讯",name="modle",paramType="query",dataType="Integer")
    })
    public CodeMessage<List<Content_type>> get_content_type_list(String login_token, String name,Integer modle){
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
        EntityWrapper<Content_type> ew=new EntityWrapper<>();
        if(name!=null && !"".equals(name)){
            ew.like(true,"name",name);
        }
        ew.eq("is_show",1);
        if(modle!=null && !"".equals(modle)){
            ew.eq("modle",modle);
        }
        try {
            List<Content_type> list=content_typeService.selectList(ew);
            LinkedList<LinkedList<Content_type>> res_list=new LinkedList<>();
            LinkedList<Content_type> item_list=new LinkedList<>();
            for (int i=0;i<list.size();i++){
                item_list.add(list.get(i));
                if(i>=7&&i%7==0){
                    res_list.add(item_list);
                    item_list=new LinkedList<>();
                }
                if(i==list.size()-1){
                    res_list.add(item_list);
                }
            }
            codeMessage.setCode(200);
            codeMessage.setMsg("查询分类列表成功");
            codeMessage.setData(res_list);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询分类列表失败");
        }
        return codeMessage;
    }
    @PostMapping("/get_content_type")
    @ApiOperation("根据id获取分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="分类id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Content_type> get_content_type(String login_token,Integer id){
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
            Content_type content_type=content_typeService.selectById(id);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询分类信息成功");
            codeMessage.setData(content_type);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询分类信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_content_type")
    @ApiOperation("根据id修改分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage update_content_type(String login_token,Content_type content_type){
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
            if(content_typeService.updateById(content_type)){
                codeMessage.setCode(200);
                codeMessage.setMsg("更新分类信息成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("更新分类信息失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("更新分类信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/delete_content_type")
    @ApiOperation("删除分类")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="分类id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_content_type(String login_token,Integer id){
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
            if(content_typeService.deleteById(id)){
                codeMessage.setCode(200);
                codeMessage.setMsg("删除分类成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("删除分类失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("删除分类失败");
        }
        return codeMessage;
    }

    @PostMapping("/add_content_type")
    @ApiOperation("新增分类")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage add_content_type(String login_token,Content_type content_type){
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
        if(content_typeService.insert(content_type)){
            codeMessage.setCode(200);
            codeMessage.setMsg("新增分类成功");
        }else{
            codeMessage.setCode(500);
            codeMessage.setMsg("新增分类失败");
        }
        return codeMessage;
    }

}

