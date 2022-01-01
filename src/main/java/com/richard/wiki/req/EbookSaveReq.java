package com.richard.wiki.req;

import lombok.Data;

@Data
public class EbookSaveReq {

    private Long id;

    private String name;

    private Long categoryId1;

    private Long categoryId2;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;

}
