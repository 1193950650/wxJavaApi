package com.julu.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 权限管理
 * @author mhs123
 *
 */
@ConfigurationProperties(prefix="securityconfig")
public class SecuritySettings {
    private String logoutsuccssurl = "/logout";//用来定义退出成功的链接
    private String permitall = "/api";//用来定义允许访问的url列表
    private String deniedpage = "/deny";//用来设定拒绝访问的信息提示连接
    private String urlroles;//这是一个权限管理规则，是链接地址和角色权限的配置列表

    public String getLogoutsuccssurl() {
        return logoutsuccssurl;
    }

    public void setLogoutsuccssurl(String logoutsuccssurl) {
        this.logoutsuccssurl = logoutsuccssurl;
    }

    public String getPermitall() {
        return permitall;
    }

    public void setPermitall(String permitall) {
        this.permitall = permitall;
    }

    public String getDeniedpage() {
        return deniedpage;
    }

    public void setDeniedpage(String deniedpage) {
        this.deniedpage = deniedpage;
    }

    public String getUrlroles() {
        return urlroles;
    }

    public void setUrlroles(String urlroles) {
        this.urlroles = urlroles;
    }
}
