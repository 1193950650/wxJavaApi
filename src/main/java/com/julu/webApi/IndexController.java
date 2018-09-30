package com.julu.webApi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/7/2 0002.
 */
@Controller
public class IndexController {
    @RequestMapping({"/index.html","/login","/","/login.html"})
    public String index(String error,Model model) {
        if(error!=null){
            model.addAttribute("error","账号或密码错误或不是管理员");
        }
        return "index";
    }
    
    @RequestMapping("login_out")
    public String login_out(String error, Model model){
    	if(error == "1"){
    		model.addAttribute("error","该用户还不是管理员");
    	}
    	return "index";
    }

    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest request) {

        return "/main/main";
    }

}
