package com.richard.wiki.domain.dos.home;

import lombok.Data;

@Data
public class EditorImgDO {

    /**
     * url      图片地址
     * alt      图片文字说明
     * href     跳转链接
     */
    private String url;
    private String alt;
    private String href;

}
