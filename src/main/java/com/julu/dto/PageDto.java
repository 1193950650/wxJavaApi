package com.julu.dto;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页")
public class PageDto<T> extends Page {
    @ApiModelProperty("当前页")
    private Integer current;
    @ApiModelProperty("每页n条")
    private Integer size;
    @ApiModelProperty("总页数")
    private Integer total;
}
