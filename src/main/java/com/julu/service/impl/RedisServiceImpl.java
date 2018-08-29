package com.julu.service.impl;

import com.julu.entity.Sys_user;
import com.julu.service.ISys_userService;
import com.julu.service.IRedisService;
import com.julu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * 手机端token 用户
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private ISys_userService fuserService;
    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    public Sys_user getAppFuser(String key) {
        Sys_user fuser = null ;
        try {
            String split[] = key.split("_") ;
            if(split.length==3){
                fuser = this.fuserService.selectById(Integer.parseInt(split[2])) ;
            }
        } catch (Exception e) {}
        return fuser ;
    }

    @Override
    public boolean isAppLogin(String key, boolean update) {
        Long l = null;
        Object s = valueOperations.get(key);
        if(s != null) {
            l = Long.parseLong(s.toString());
        }

        if(l==null){
            return false ;
        }else{

            /*
        Timestamp time = new Timestamp(l) ;
        if(Utils.getTimestamp().getTime() - time.getTime() <30*3600*1000L ){
            if(update==true){
                this.appSessionMap.put(key, Utils.getTimestamp().getTime()) ;
            }
            return true ;
        }else{
            return false ;
        }
		 */
        return true ;
    }
}

    @Override
    public void removeAppSession(String key) {
        valueOperations.set(key,null);
    }

    @Override
    public String putAppSession(HttpSession session, Sys_user fuser) {
        String loginToken = session.getId()+"_"+ Utils.getTimestamp().getTime()+"_"+fuser.getId() ;
        valueOperations.set(loginToken, Long.toString(Utils.getTimestamp().getTime()), 604800, TimeUnit.SECONDS);
        return loginToken ;
    }

    @Override
    public String getAppCode(String key) {
        String code="";
        String split[] = key.split("_") ;
        if(split.length==3){
            code=split[2] ;
        }
        return code;
    }

    @Override
    public String putAppCode(HttpSession session, String code) {
        String codeToken = session.getId()+"_"+ Utils.getTimestamp().getTime()+"_"+code ;
        System.out.println("codeToken"+codeToken);
        valueOperations.set(codeToken, Long.toString(Utils.getTimestamp().getTime()), 600, TimeUnit.SECONDS);
        return codeToken ;
    }
}
