package com.richard.wiki.controller;

import com.richard.wiki.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> uploadImg(@RequestParam("file") MultipartFile file) throws Exception {
        // 获取文件，文件名
        file.getOriginalFilename();

        return commonService.uploadFile(file);
    }

}
