package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2//swagger2.x的注解
@EnableOpenApi //开启Swagger3的注解
//地址localhost:9090/swagger-ui/index.html
public class SwaggerConfig {

    @Bean
    public Docket createDocket(){



        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准接口")
                .apiInfo(apiInfo())//创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .select()//函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger ui来展现
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot.controller"))//指定需要扫描的包路路径
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false);
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("后台权限管理系统实战")
                .description("后台权限管理系统后端接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .contact(new Contact("","",""))
                .build();
    }
}
