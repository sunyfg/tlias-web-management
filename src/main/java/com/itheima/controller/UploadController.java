package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

//    /**
//     * 本地存储文件
//     * @param username
//     * @param age
//     * @param image
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传：{}, {}, {}", username, age, image);
//        // 获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        // 构建唯一的文件名 - uuid
//        int index = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + suffix;
//        log.info("新的文件名：{}", newFileName);
//
//        image.transferTo(new File("/Users/max/Desktop/upload/" + newFileName));
//
//        return Result.success();
//    }

    /**
     * 阿里云OSS存储文件
     * @param image
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();

        log.info("文件上传：{}", originalFilename);

        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url：{}", url);

        return Result.success(url);
    }
}
