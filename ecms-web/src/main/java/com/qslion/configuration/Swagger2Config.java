package com.qslion.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/12/11.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildApiInf())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.qslion"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
            .title("ECMS")
            .description("springboot swagger2")
            .termsOfServiceUrl("http://www.ecms.cn")
            .contact(new Contact("gray.z", "http://www.ecms.cn", "grayz@foxmail.com"))
            .license("copyright 2018 Qslion Company, Inc. All rights reserved.")
            .version("1.1.0")
            .build();

    }
}
