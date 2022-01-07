package com.richard.wiki.controller;

import com.google.common.collect.Lists;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file) throws Exception {
        // 获取文件，文件名
        file.getOriginalFilename();

        return commonService.uploadFile(file);
    }

}
