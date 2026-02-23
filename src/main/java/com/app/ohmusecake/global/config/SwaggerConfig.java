/*
 * Copyright (c) SKU K-IO-SK
 */
package com.app.ohmusecake.global.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

// @Configuration
public class SwaggerConfig {

  @Value("${server.servlet.context-path:}")
  private String contextPath;

  @Value("${swagger.server.profile}")
  private String profileUrl;

  @Value("${swagger.server.name}")
  private String profileName;

  @Bean
  public OpenAPI customOpenAPI() {
    Server server = new Server().url(profileUrl + contextPath).description(profileName + " Server");

    return new OpenAPI()
        .addServersItem(server)
        .info(
            new Info()
                .title("Kiosk API 명세서")
                .version("1.0")
                .description(
                    """
                    # 서경대학교 산업체연계형 캡스톤디자인 프로젝트 - K-IO_SK(Korea Input/Output SeoKyeong)

                    ## 주의사항
                    - 파일 업로드 크기 제한: 5MB (1개 파일 크기)

                    ## 문의
                    - 기술 문의: unijun0109@gmail.com, keumsiun@skuniv.ac.kr, ojing0116@skuniv.ac.kr
                    - 일반 문의: unijun0109@gmail.com, keumsiun@skuniv.ac.kr, ojing0116@skuniv.ac.kr
                    """));
  }

  @Bean
  public GroupedOpenApi apiGroup() {
    return GroupedOpenApi.builder().group("api").pathsToMatch("/**").build();
  }
}
