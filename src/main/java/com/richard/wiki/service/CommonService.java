package com.richard.wiki.service;

import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {

    @Autowired
    private FileUtil fileUtil;

    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        // 获取文件，文件名
        file.getOriginalFilename();
        // uploadFile 方法执行图片上传
        String dirPath = fileUtil.uploadFile(file);

        return dirPath;
    }

}
