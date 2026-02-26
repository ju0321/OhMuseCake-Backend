/*
 * Copyright (c) SKU K-IO-SK
 */
package com.app.ohmusecake.global.s3.service;

// import java.io.ByteArrayInputStream;
// import java.util.UUID;
//
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.model.DeleteObjectRequest;
// import com.amazonaws.services.s3.model.ObjectMetadata;
// import com.amazonaws.services.s3.model.PutObjectRequest;
// import com.app.ohmusecake.global.config.S3Config;
// import com.app.ohmusecake.global.exception.CustomException;
// import com.app.ohmusecake.global.s3.exception.S3ErrorCode;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

import com.app.ohmusecake.global.s3.entity.PathName;

// @Service
// @RequiredArgsConstructor
// @Slf4j
public class S3ServiceImpl implements S3Service {

  // private final S3Config s3Config;
  // private final AmazonS3 amazonS3;

  @Override
  public String uploadWebp(byte[] webpBytes, String keyName, String fileType) {
    // TODO: S3 의존성 추가 후 구현
    return null;
  }

  @Override
  public String createKeyName(PathName pathName, String fileType) {
    // TODO: S3 의존성 추가 후 구현
    return null;
  }

  @Override
  public void deleteFile(String keyName) {
    // TODO: S3 의존성 추가 후 구현
  }

  @Override
  public String extractKetNameFromUrl(String imageUrl) {
    // TODO: S3 의존성 추가 후 구현
    return null;
  }
}
