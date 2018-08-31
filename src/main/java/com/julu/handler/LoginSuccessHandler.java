package com.julu.handler;

import com.julu.entity.Sys_user;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理器
 * @author Administrator
 *
 */
@Service
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    protected Log log = LogFactory.getLog(getClass());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException,ServletException {
        Sys_user userDetails = (Sys_user)authentication.getPrincipal();
//        log.info("登录用户user:" + userDetails.getUser_name()+ "login"+request.getContextPath());
//        log.info("IP:" + getIpAddress(request));
//        // 判断该用户是不是管理员
//        /*Integer i= userDetails.getIsAdmin();
//        if(i == null || i != 1){
//        	 getRedirectStrategy().sendRedirect(request,response,"/login_out?error=1");
//        }*/
////        System.out.println("url:"+url);
        redirectStrategy.sendRedirect(request,response,"/main");
     //   super.onAuthenticationSuccess(request, response, authentication);
    }

    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
