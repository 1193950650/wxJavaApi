package com.julu.service;


import com.julu.entity.Sys_user;

import javax.servlet.http.HttpSession;

/**
 * 手机端token 用户
 */
public interface IRedisService {

    public Sys_user getAppFuser(String key);

    boolean isAppLogin(String key, boolean update);

    void removeAppSession(String key);

    String putAppSession(HttpSession session, Sys_user fuser);

    String getAppCode(String key);

    String putAppCode(HttpSession session, String code);
}
