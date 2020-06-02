package com.greenwayshop.learning.web;


import com.greenwayshop.learning.services.ImageStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ImageController {
    ImageStorageService imageStorageService;

    @PostMapping(value = "/images/upload", consumes = "multipart/form-data")
    public void uploadAnImage(@RequestParam(name = "file") MultipartFile image){
        imageStorageService.save(image);
    }

    @GetMapping("/images/{imageName}")
    public Resource getTheImage(@PathVariable String imageName){
        return imageStorageService.load(imageName);
    }

    @DeleteMapping("/images/{imageName}")
    public void deleteAnImage(@PathVariable String imageName) {
        this.imageStorageService.delete(imageName);
    }
}
