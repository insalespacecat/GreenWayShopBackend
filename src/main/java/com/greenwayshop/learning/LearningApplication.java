package com.greenwayshop.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
public class LearningApplication /* implements CommandLineRunner */ {

    /*
    @Resource
    ImageStorageService imageStorageService;
     */

    //private static final AWSCredentials credentials;

    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {
        imageStorageService.init();
    }
     */

}
