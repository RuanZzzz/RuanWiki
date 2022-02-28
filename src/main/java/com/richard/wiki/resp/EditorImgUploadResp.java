package com.richard.wiki.resp;

import com.richard.wiki.domain.dos.home.EditorImgDO;
import lombok.Data;

import java.util.List;

@Data
public class EditorImgUploadResp {

    /**
     * 富文本编辑器图片上传的返回格式
     * errno        errno 即错误代码，0 表示没有错误
     * data         ata 是一个数组，返回图片Object，Object中包含需要包含url、alt和href三个属性,它们分别代表图片地址、图片文字说明和跳转链接,alt和href属性是可选的，可以不设置或设置为空字符串,需要注意的是url是一定要填的
     */
    private String errno;
    private List<EditorImgDO> data;

}
