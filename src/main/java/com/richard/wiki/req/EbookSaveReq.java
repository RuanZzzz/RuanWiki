package com.richard.wiki.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EbookSaveReq {

    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Long categoryId1;

    private Long categoryId2;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;

    private String imgDirPath;

}
