package com.richard.wiki.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DocSaveReq {
    private Long id;

    @NotNull(message = "电子书不能为空")
    private Long ebookId;

    @NotNull(message = "父文档不能为空")
    private Long parent;

    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(message = "顺序不能为空")
    private Integer sort;

    @NotNull(message = "内容不能为空")
    private String content;
}
