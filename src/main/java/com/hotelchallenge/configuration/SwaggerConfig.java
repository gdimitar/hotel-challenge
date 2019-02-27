package com.hotelchallenge.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        final StopWatch watch = new StopWatch();
        watch.start();

        final Contact contact = new Contact("Dimitar Gavrilov",
                "",
                "gavrilov.dimitar@gmail.com");

        final List<VendorExtension> vendorExtensionList = new ArrayList<>();
        final ApiInfo apiInfo = new ApiInfo(
                "REST API for a HotelInformation",
                "Restful api for hotel information",
                "0.0.1",
                "",
                contact,
                "",
                "", vendorExtensionList);

        final Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();

        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}