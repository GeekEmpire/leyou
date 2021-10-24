package com.leyou.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.controller.UploadController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xmz
 * @Description 文件上传，暂时没用fastDFS
 * @Date 2021/9/27 16:17
 **/
@Service
public class UploadService {

    private Logger logger = LoggerFactory.getLogger(UploadController.class);
    //不使用fastDFS
    @Autowired
    FastFileStorageClient storageClient;

    public String upload(MultipartFile file) {
        try {
            //第一步校验文件是否是许可文件
            List<String> suffixs = Arrays.asList("image/png", "image/jpeg");
            String contentType = file.getContentType();
            System.out.println(contentType);
            if (!suffixs.contains(contentType)){
                logger.info("这个类型我的服务器不识别{}",contentType);
                return null;
            }

            //后缀，contentType   8+3规范  jpg.jpeg   image/jpeg

            //第二部分校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage==null){
                logger.info("文件内容有误");
                return null;
            }


            // 2、将图片上传到FastDFS
            // 2.1、获取文件后缀名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 2.2、上传
            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), extension, null);
            // 2.3、返回完整路径
            return "http://image.leyou.com/" + storePath.getFullPath();

            /*
            File dir = new File("F:\\file\\heima\\upload");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 2.2、保存图片
            file.transferTo(new File(dir, file.getOriginalFilename()));

            // 2.3、拼接图片地址
            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();

            return url;
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
