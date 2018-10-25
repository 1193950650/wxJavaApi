package com.julu.utils;

import com.julu.entity.Content_config;
import com.julu.service.IContent_configService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysConfig {
    @Autowired
    private IContent_configService content_configService;
    public  Content_config getContent_config(){
        return content_configService.selectById(1);
    }
}
