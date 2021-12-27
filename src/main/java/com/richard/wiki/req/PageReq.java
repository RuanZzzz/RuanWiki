package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageReq {

    @ApiModelProperty(name = "页码")
    private Integer page;

    @ApiModelProperty(name = "每页条数")
    private Integer pageSize;

}
