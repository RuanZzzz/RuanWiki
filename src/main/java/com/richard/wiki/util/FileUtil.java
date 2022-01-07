package com.richard.wiki.util;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 图片上传工具类
 */
@Component
public class FileUtil {

    @Value("${file.path}")
    private String dirPath;

    public String uploadFile(MultipartFile file) throws Exception {
        // 图片校验
        List<String> imageType = Lists.newArrayList("jpg", "jpeg", "png", "bmp", "gif");
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀格式
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

        if (imageType.contains(fileSuffix)) {
            // 只有党满足图片格式时候才进来，重新为图片取名，防止出现名称重复
            String newFileName = System.currentTimeMillis() + "." + fileSuffix;
            String path = File.separator + newFileName;
            File destFile = new File(this.dirPath + path);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(destFile);
                // 返回上传的文件名
                return newFileName;
            }catch (IOException e) {
                return null;
            }
        }else {
            throw new Exception("图片格式不正确");
        }

    }

}
