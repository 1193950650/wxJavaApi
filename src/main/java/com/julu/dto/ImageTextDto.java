package com.julu.dto;

import com.julu.entity.Content_imgtext;
import com.julu.entity.Sys_user;
import io.swagger.annotations.ApiModel;

@ApiModel("图文+作者")
public class ImageTextDto extends Content_imgtext {
    private Sys_user sys_user;

    public Sys_user getSys_user() {
        return sys_user;
    }

    public void setSys_user(Sys_user sys_user) {
        this.sys_user = sys_user;
    }
}
