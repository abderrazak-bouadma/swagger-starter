package com.sfeir.school.microservices.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Date;

import static com.google.common.base.Predicates.not;

/**
 * Created by Abderrazak BOUADMA
 * on 26/12/2016.
 */

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
public class SwaggerAutoConfiguration {

    private static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    private final SwaggerConfigurationProperties config;

    @Autowired
    public SwaggerAutoConfiguration(SwaggerConfigurationProperties config) {
        this.config = config;
    }

    @Bean
    public Docket unsecuredDocket() {

        Contact contact = new Contact(
                config.getContactName(),
                config.getContactUrl(),
                config.getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                config.getTitle(),
                config.getDescription(),
                config.getVersion(),
                config.getTermsOfServiceUrl(),
                contact.toString(),
                config.getLicense(),
                config.getLicenseUrl());

        //noinspection Guava
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .paths(not(PathSelectors.regex("/error.*")))
                .paths(not(PathSelectors.regex("/admin.*")))
                .build();
    }

}
