package com.richard.wiki.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookQueryResp {

    private Long id;

    private String name;

    private String description;

    // cover：列表显示图片的地址
    private String cover;

    // dirPath：用于编辑使用的地址
    private String imgName;

    private Long category1Id;

    private Long category2Id;

}
