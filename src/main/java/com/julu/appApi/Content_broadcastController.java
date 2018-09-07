package com.julu.appApi;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Content_broadcast;
import com.julu.service.IContent_broadcastService;
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
 * 内容-轮播管理 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@RestController
@Api(tags = "内容-轮播管理")
@RequestMapping("/app/content_broadcast")
public class Content_broadcastController {
    @Autowired
    private IContent_broadcastService content_broadcastService;
    @Autowired
    private IRedisService redisService;
    @PostMapping("/get_content_broadcast_list")
    @ApiOperation("获取轮播列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="显示 0不显示 1显示",name="is_show",paramType="query",dataType="Integer"),
            @ApiImplicitParam(value="%标题名称%",name="title_name",paramType="query",dataType="String")
    })
    public CodeMessage<PageDto<Content_broadcast>> get_content_broadcast_list(String login_token,Integer is_show, String title_name){
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
        Page<Content_broadcast> page=new Page<>();
        EntityWrapper<Content_broadcast> ew=new EntityWrapper<>();
        ew.eq("is_show",is_show);
        ew.eq("title_name",title_name);
        page.setSize(10);
        try {
            page=content_broadcastService.selectPage(page,ew);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询轮播列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询轮播列表失败");
        }
        return codeMessage;
    }
    @PostMapping("/get_content_broadcast")
    @ApiOperation("根据id获取轮播信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="轮播id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage<Content_broadcast> get_content_broadcast(String login_token,Integer id){
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
            Content_broadcast content_broadcast=content_broadcastService.selectById(id);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询轮播信息成功");
            codeMessage.setData(content_broadcast);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询轮播信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/update_content_broadcast")
    @ApiOperation("根据id修改轮播信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="轮播信息",name="content_broadcast",paramType="query",dataType="Content_broadcast")
    })
    public CodeMessage update_content_broadcast(String login_token,Content_broadcast content_broadcast){
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
            if(content_broadcastService.updateById(content_broadcast)){
                codeMessage.setCode(200);
                codeMessage.setMsg("更新轮播信息成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("更新轮播信息失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("更新轮播信息失败");
        }
        return codeMessage;
    }

    @PostMapping("/delete_content_broadcast")
    @ApiOperation("删除轮播")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="轮播id",name="id",paramType="query",dataType="Integer")
    })
    public CodeMessage delete_content_broadcast(String login_token,Integer id){
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
            if(content_broadcastService.deleteById(id)){
                codeMessage.setCode(200);
                codeMessage.setMsg("删除轮播成功");
            }else{
                codeMessage.setCode(500);
                codeMessage.setMsg("删除轮播失败");
            }
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("删除轮播失败");
        }
        return codeMessage;
    }

    @PostMapping("/add_content_broadcast")
    @ApiOperation("新增轮播")
    @ApiImplicitParams({
            @ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String"),
            @ApiImplicitParam(value="轮播信息",name="sys_user",paramType="query",dataType="Sys_user")
    })
    public CodeMessage add_content_broadcast(String login_token,Content_broadcast content_broadcast){
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
        if(content_broadcastService.insert(content_broadcast)){
            codeMessage.setCode(200);
            codeMessage.setMsg("新增轮播成功");
        }else{
            codeMessage.setCode(500);
            codeMessage.setMsg("新增轮播失败");
        }
        return codeMessage;
    }
}

