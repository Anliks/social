package com.example.demo.service;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {
    private final AmazonS3 space;

    public StorageService() {

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("YCAJEw6Nzjyx0f9pNCpeLwaqS","YCPo7rQ5OuVIV8Yq8vSrY_WRKQ0NefVfbd04UTDs")
        );
        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("s3.yandexcloud.net", "s3")
                )
                 .build();
    }

    public List<String> getSongFileNames() {
        ListObjectsV2Result result = space.listObjectsV2("anliks");
        List<S3ObjectSummary> objects = result.getObjectSummaries();
       return objects.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }


    public void uploadSong(MultipartFile file) throws IOException {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            space.putObject(new PutObjectRequest("anliks", file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));

    }
}
