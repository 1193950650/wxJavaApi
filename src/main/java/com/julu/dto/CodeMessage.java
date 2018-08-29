package com.julu.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "app接口返回")
public class CodeMessage<T> {
    @ApiModelProperty(value = "编码：200（成功）、401 (未登录)、403（权限不足，或被禁用）、404（不存在，查不到）、500（接口异常）")
    private Integer code;
    @ApiModelProperty(value = "消息内容：根据实际场景相应编码的详细描述")
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "数据集合：返回业务场景中前端需要的数据")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
