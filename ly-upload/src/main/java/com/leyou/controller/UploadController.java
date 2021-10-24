package com.leyou.controller;

import com.leyou.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author XMZ
 * @Description 上传功能
 * @Date 2021/9/17 14:50
 **/
@RestController
@RequestMapping("upload")
public class UploadController {



    @Autowired
    private UploadService uploadService;

    /**
     * @Author XMZ
     * @Description 上传图片
     * @Date 2021/9/17 14:50
     **/
    @PostMapping("image")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){

        System.out.println(file);
        String url = uploadService.upload(file);

        if (!StringUtils.isNotBlank(url)){
            // url为空，证明上传失败
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // 返回200，并且携带url路径
        return ResponseEntity.ok(url);
    }
}
