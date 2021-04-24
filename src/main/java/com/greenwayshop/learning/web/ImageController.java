package com.greenwayshop.learning.web;

import com.greenwayshop.learning.services.AwsS3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ImageController {

    AwsS3Service awsS3Service;

    @PostMapping(value = "/images/upload/{imageName}", consumes = "multipart/form-data")
    public void uploadAnImage(@RequestParam(name = "file") MultipartFile image, @PathVariable String imageName) throws IOException {
        awsS3Service.uploadFileToS3(image, imageName);
    }

    @GetMapping("/images/{imageName}")
    public Resource getTheImage(@PathVariable String imageName) throws IOException {
        return awsS3Service.downloadS3Object(imageName);
    }
}