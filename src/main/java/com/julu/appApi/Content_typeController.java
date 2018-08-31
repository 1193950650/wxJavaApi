package com.julu.appApi;


import com.baomidou.mybatisplus.plugins.Page;
import com.julu.dto.CodeMessage;
import com.julu.dto.PageDto;
import com.julu.entity.Content_type;
import com.julu.service.IContent_typeService;
import com.julu.service.IRedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 内容-分类 前端控制器
 * </p>
 *
 * @author mhs
 * @since 2018-08-31
 */
@Controller
@RequestMapping("/content_type")
public class Content_typeController {
    @Autowired
    private IContent_typeService content_typeService;
    @Autowired
    private IRedisService redisService;
    @GetMapping("/get_content_type_list")
    @ApiOperation("获取分类列表")
    @ApiImplicitParams({@ApiImplicitParam(value="login_token",name="login_token",paramType="query",dataType="String")
    })
    public CodeMessage<PageDto<Content_type>> get_content_type_list(String login_token){
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
        Page<Content_type> page=new Page<>();
        page.setSize(10);
        try {
            page=content_typeService.selectPage(page);
            codeMessage.setCode(200);
            codeMessage.setMsg("查询分类列表成功");
            codeMessage.setData(page);
        }catch (Exception e){
            codeMessage.setCode(500);
            codeMessage.setMsg("查询分类列表失败");
        }
        return codeMessage;
    }


}

