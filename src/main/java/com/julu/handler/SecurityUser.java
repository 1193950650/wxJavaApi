package com.julu.handler;

import com.julu.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser extends Users implements UserDetails
{


    private static final long serialVersionUID = 1L;
    public SecurityUser(Users user) {
        if(user != null)
        {
            this.setId(user.getId());
            this.setPhone(user.getPhone());
            this.setEmail(user.getEmail());
            this.setRoleId(user.getRoleId());
            this.setComId(user.getComId());
            this.setFunctionId(user.getFunctionId());
            this.setPassword(user.getPassword());
            this.setRegTime(user.getRegTime());
            this.setIsAdmin(user.getIsAdmin());
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
        return super.getPhone();
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
