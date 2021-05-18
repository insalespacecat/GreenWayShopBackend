
package com.greenwayshop.learning.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class AwsS3Service {

   /* private ResourceLoader resourceLoader;
    private ServiceProperties serviceProperties;
    private ImageStorageService imageStorageService;
    */
    private String bucketName;
    private AmazonS3 amazonS3;

    AwsS3Service() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAXTA5YW36EVRHFFNS",
                "UxEb+4Wvnzayu6IY9/8HpHuHHzjw5yqYf71nx40j"
        );
        amazonS3 =  AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        bucketName = "greenway-shop";
    }

    public Resource downloadS3Object(String filename) throws IOException {

        log.info("Trying to download the file " + filename);
        try (S3ObjectInputStream inputStream = amazonS3.getObject(bucketName, filename).getObjectContent()) {
            File file = new File(filename);
            FileOutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            Resource resource = new FileSystemResource(file);
            return resource;
        }

        /*
        Resource resource = resourceLoader.getResource(serviceProperties.getImageBucketURL() + "/" + filename);
        return resource;
         */

        /*
        try (InputStream inputStream = resource.getInputStream()) {
            File file = new File(resourceFilename);
            FileOutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
        }
         */

    }

public void uploadFileToS3(MultipartFile multipartFile, String imageName) throws IOException {
        File file = new File(imageName + ".jpg");
        FileOutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(multipartFile.getInputStream(), outputStream);

        int maxUploadThreads = 5;

        TransferManager tm = TransferManagerBuilder
        .standard()
        .withS3Client(amazonS3)
        .withMultipartUploadThreshold((long) (5 * 1024 * 1024))
        .withExecutorFactory(() -> Executors.newFixedThreadPool(maxUploadThreads))
        .build();

        ProgressListener progressListener =
        progressEvent -> System.out.println("Transferred bytes: " + progressEvent.getBytesTransferred());

        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);

        request.setGeneralProgressListener(progressListener);

        Upload upload = tm.upload(request);

        try {
        upload.waitForCompletion();
        System.out.println("Upload complete.");
        } catch (AmazonClientException | InterruptedException e) {
        System.out.println("Error occurred while uploading file");
        e.printStackTrace();
        }
        /*WritableResource resource = (WritableResource) resourceLoader
                .getResource(serviceProperties.getImageBucketURL());

        try (OutputStream outputStream = resource.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
        }
        */
        }
}

