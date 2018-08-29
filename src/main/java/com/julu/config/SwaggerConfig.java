package com.julu.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * url:http://localhost/swagger-ui.html
 *     http://localhost/doc.html
 * @author mhs123
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createAppRestApi() {
        com.google.common.base.Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.julu.appController");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("手机端接口")
                .select()
                .apis(Predicates.and(selector1))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket createWebRestApi() {
        com.google.common.base.Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.julu.webController");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("pc端接口")
                .select()
                .apis(Predicates.and(selector1))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket createUtilRestApi() {
        com.google.common.base.Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.julu.pubController");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("公共接口")
                .select()
                .apis(Predicates.and(selector1))
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("接口文档分组：pc接口 手机接口 公共接口")
                .title("微链云API")
                .termsOfServiceUrl("/doc.html（普通接口推荐） or /swagger-ui.html(文件上传接口推荐)")
                .contact(new Contact("mhs", "http://www.baidu.com", "471002385@qq.com"))
                .version("1.0")
                .build();
    }
}