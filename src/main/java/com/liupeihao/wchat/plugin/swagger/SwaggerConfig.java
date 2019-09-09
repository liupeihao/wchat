package com.liupeihao.wchat.plugin.swagger;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author fww on 2019-05-16.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled:false}")
    private Boolean swaggerEnabled;

    @Value("${swagger.apiInfo.title:title}")
    private String apiTitle;

    @Value("${swagger.apiInfo.version:0.0.1}")
    private String version;

    @Value("${swagger.apiInfo.description:项目api文档}")
    private String description;

    @Value("${swagger.pathMapping:/}")
    private String swaggerPathMapping;

    @Value("${swagger.apiInfo.basePackage:com.zyzh.zz}")
    private String apiBasePackage;

    @Bean
    public Docket createRestApi() {

        //header
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping(swaggerPathMapping)
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiTitle)
                .description(description)
                .version(version).build();
    }
}
