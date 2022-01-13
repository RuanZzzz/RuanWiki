package com.richard.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EbookQueryReq extends PageReq {

    @ApiModelProperty(value = "电子书id")
    private Long id;

    @ApiModelProperty(value = "电子书name")
    private String name;

    @ApiModelProperty(value = "分类2的id")
    private Long categoryId2;

}
