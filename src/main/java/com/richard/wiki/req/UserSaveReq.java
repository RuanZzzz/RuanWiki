package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserSaveReq {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "登录名")
    @NotNull(message = "登录名不能为空")
    private String loginName;

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "用户名不能为空")
    private String name;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "\"^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "密码至少包含数字和英文，长度为6～20")
    private String password;

}
