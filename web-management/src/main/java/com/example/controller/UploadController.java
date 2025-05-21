package com.example.controller;

import com.example.pojo.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {

    /**
     *本地端儲存
     */
    @PostMapping("/upload")
    public Result upload(String name ,Integer age,MultipartFile file) throws IOException {
        log.info("接收參數:{},{},{}",name,age,file);
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extension;

        file.transferTo(new File("D:/images/"+newFileName));
        return Result.success();
    }


}
