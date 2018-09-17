package com.julu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户登录返回实体")
public class LoginDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "登录状态的token")
    private  String login_token;


    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token( String login_token) {
        this.login_token = login_token;
    }
}
