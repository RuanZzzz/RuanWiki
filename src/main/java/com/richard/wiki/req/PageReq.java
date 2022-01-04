package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class PageReq {

    @NotNull(message = "页码不能为空")
    @ApiModelProperty(name = "页码")
    private Integer page;

    @NotNull(message = "每页条数不能为空")
    @Max(value = 100,message = "每页条数不能超过100")
    @ApiModelProperty(name = "每页条数")
    private Integer pageSize;

}
