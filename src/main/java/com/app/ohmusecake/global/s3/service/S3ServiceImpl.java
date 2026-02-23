/*
 * Copyright (c) SKU K-IO-SK
 */
package com.app.ohmusecake.global.s3.service;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.ohmusecake.global.config.S3Config;
import com.app.ohmusecake.global.exception.CustomException;
import com.app.ohmusecake.global.s3.entity.PathName;
import com.app.ohmusecake.global.s3.exception.S3ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service {

  private final S3Config s3Config;
  private final AmazonS3 amazonS3;

  @Override
  public String uploadWebp(byte[] webpBytes, String keyName, String fileType) {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(webpBytes.length);
      metadata.setContentType("image/" + fileType);

      ByteArrayInputStream inputStream = new ByteArrayInputStream(webpBytes);

      amazonS3.putObject(
          new PutObjectRequest(s3Config.getBucket(), keyName, inputStream, metadata));

      return amazonS3.getUrl(s3Config.getBucket(), keyName).toString();
    } catch (Exception e) {
      log.error("S3 upload 중 문제 발생", e);
      throw new CustomException(S3ErrorCode.FILE_SERVER_ERROR);
    }
  }

  @Override
  public String createKeyName(PathName pathName, String fileType) {
    return getPrefix(pathName) + '/' + UUID.randomUUID() + "." + fileType;
  }

  @Override
  public void deleteFile(String keyName) {
    filedExists(keyName);

    try {
      amazonS3.deleteObject(new DeleteObjectRequest(s3Config.getBucket(), keyName));
      log.info("파일 삭제 성공 - keyName: {}", keyName);
    } catch (Exception e) {
      log.error("S3 delete 중 오류 발생", e);
      throw new CustomException(S3ErrorCode.FILE_SERVER_ERROR);
    }
  }

  @Override
  public String extractKetNameFromUrl(String imageUrl) {
    String bucketUrl =
        "https://" + s3Config.getBucket() + ".s3." + s3Config.getRegion() + ".amazonaws.com/";

    if (!imageUrl.startsWith(bucketUrl)) {
      throw new CustomException(S3ErrorCode.FILE_URL_INVALID);
    }
    String keyName = imageUrl.substring(bucketUrl.length());
    log.info("keyName 추출 성공 - keyName: {}", keyName);
    return keyName;
  }

  private void filedExists(String keyName) {
    if (!amazonS3.doesObjectExist(s3Config.getBucket(), keyName)) {
      throw new CustomException(S3ErrorCode.FILE_NOT_FOUND);
    }
  }

  private String getPrefix(PathName pathName) {
    return switch (pathName) {
      case MAIN -> s3Config.getMainImagePath();
    };
  }
}
