package com.greenwayshop.learning;

import com.greenwayshop.learning.services.ImageStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
@SpringBootApplication
public class LearningApplication implements CommandLineRunner {

    @Resource
    ImageStorageService imageStorageService;

    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        imageStorageService.init();
    }

}
