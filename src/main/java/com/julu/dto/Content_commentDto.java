package com.julu.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.julu.entity.Content_comment;
import com.julu.entity.Content_reply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 内容-评论+回复列表
 * </p>
 *
 * @author mhs
 * @since 2018-09-16
 */
@ApiModel("内容-评论+回复列表")
public class Content_commentDto extends Content_comment {
    @ApiModelProperty("回复评论列表")
    private List<Content_reply> content_replies;

    public List<Content_reply> getContent_replies() {
        return content_replies;
    }

    public void setContent_replies(List<Content_reply> content_replies) {
        this.content_replies = content_replies;
    }
}
