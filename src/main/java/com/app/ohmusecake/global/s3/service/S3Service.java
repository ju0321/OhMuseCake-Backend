/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.global.s3.service;

import com.app.ohmusecake.global.s3.entity.PathName;

public interface S3Service {

  /**
   * webp, keyName, fileType을 인자로 받아 s3에 업로드 후 url을 반환하는 메서드
   *
   * @param webpBytes 바이트 배열 형태의 webp
   * @param keyName 저장될 경로명 문자열
   * @param fileType 저장할 이미지 타입
   * @return 저장된 s3 url
   */
  String uploadWebp(byte[] webpBytes, String keyName, String fileType);

  /**
   * 이미지를 넣을 경로명과 타입 입력시 저장될 경로명을 문자열로 반환하는 메서드
   *
   * @param pathName s3 폴더(경로)명
   * @param fileType 저장할 이미지 타입
   * @return 저장될 경로 문자열
   */
  String createKeyName(PathName pathName, String fileType);

  /**
   * keyName을 인자로 받아 해당 이미지 파일을 s3에서 삭제하는 메서드
   *
   * @param keyName keyName
   */
  public void deleteFile(String keyName);

  /**
   * imageUrl을 인자로 받아 keyName 문자열을 반환하는 메서드
   *
   * @param imageUrl s3 이미지 url
   * @return keyName 문자열
   */
  public String extractKetNameFromUrl(String imageUrl);
}
