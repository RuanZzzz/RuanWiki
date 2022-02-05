package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryReq extends PageReq {

    @ApiModelProperty(value = "登陆名")
    private String loginName;

}
