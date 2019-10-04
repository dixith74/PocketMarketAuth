package com.pm.auth.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Profile("!prod")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String API_PKG = "com.pm.auth";
    private static final Predicate<String> PATH_SELECTOR = PathSelectors.any();
    private static final Predicate<RequestHandler> HANDLER = RequestHandlerSelectors.basePackage(API_PKG);
    private static final DocumentationType DOC_TYPE = DocumentationType.SWAGGER_2;
    
    @Bean
    public Docket api() {
        return new Docket(DOC_TYPE).select().apis(HANDLER).paths(PATH_SELECTOR).build().apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "POCKET MARKET APP",
                "REST API for Pocket market",
                "1.0",
                "Terms of service",
                new Contact("Pocket market", "https://pm.com/about/", "contact@pm.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
