package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Req extends PageReq {

    @ApiModelProperty(name = "电子书id")
    private Long id;

    @ApiModelProperty(name = "电子书name")
    private String name;

}
