package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginReq {

    @ApiModelProperty(value = "登录名")
    @NotEmpty(message = "登录名不能为空")
    private String loginName;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

}
