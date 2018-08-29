package com.julu.handler;


import com.julu.entity.Sys_user;
import com.julu.service.ISys_userService;
import com.julu.service.ISys_user_rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ISys_userService usersMapper;

    /**
     * 用户身份验证
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("---phone---"+username);
        Map<String,Object> map=new HashMap<>();
        map.put("phone",username);
        //判断是否为运营者
       // map.put("is_admin",3);
        List<Sys_user> user = usersMapper.selectByMap(map);
        if (user==null || user.size()<1) {
            throw new BadCredentialsException("账号密码错误！");
        }

//        System.out.println(user1.getUserRole().getCode());
//        System.out.println(user1.getRoleId()+"--------------");
        return new SecurityUser(user.get(0));
    }


}
