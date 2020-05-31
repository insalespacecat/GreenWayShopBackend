package com.greenwayshop.learning.web;


import com.greenwayshop.learning.services.ImageStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ImageController {
    ImageStorageService imageStorageService;

    @PostMapping("/images/upload")
    public void uploadAnImage(@RequestParam("/image")MultipartFile image){
        imageStorageService.save(image);
    }

    @GetMapping("/images/{imageName}")
    public Resource getTheImage(@PathVariable String imageName){
        return imageStorageService.load(imageName);
    }

}
