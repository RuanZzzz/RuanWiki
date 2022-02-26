package com.richard.wiki.service;

import com.richard.wiki.domain.EditorImgDO;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.EditorImgUploadResp;
import com.richard.wiki.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Autowired
    private FileUtil fileUtil;

    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        // 获取文件，文件名
        file.getOriginalFilename();
        // uploadFile 方法执行图片上传
        Map<String,String> imgMap = fileUtil.uploadFile(file);

        return imgMap;
    }

    public EditorImgUploadResp uploadEditorImg(@RequestParam("file") MultipartFile file) throws Exception {
        // 获取文件，文件名
        file.getOriginalFilename();
        // uploadFile 方法执行图片上传
        Map<String,String> imgMap = fileUtil.uploadFile(file);
        List<EditorImgDO> editorImgDOList = new ArrayList<>();
        EditorImgDO editorImgDO = new EditorImgDO();
        editorImgDO.setAlt("");
        editorImgDO.setHref("");
        editorImgDO.setUrl(imgMap.get("imgUrl"));
        editorImgDOList.add(editorImgDO);

        EditorImgUploadResp resp = new EditorImgUploadResp();
        resp.setErrno("0");
        resp.setData(editorImgDOList);

        return resp;
    }

}
