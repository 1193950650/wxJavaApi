package com.julu.handler;

import com.julu.entity.Sys_user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser extends Sys_user implements UserDetails
{


    private static final long serialVersionUID = 1L;
    public SecurityUser(Sys_user user) {
        if(user != null)
        {
            this.setId(user.getId());
            this.setPassword(user.getPassword());
            this.setUser_name(user.getUser_name());
            this.setIcon(user.getIcon());
        }
    }
    //取得用户分配的权限列表用于后面的权限验证
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

       //List<> roles = this.getRoleId();
      /*  if(roles != null)
        {
            for (Role role : roles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);
            }
        }*/
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUser_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
