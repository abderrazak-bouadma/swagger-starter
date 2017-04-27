package com.sfeir.school.microservices.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Abderrazak BOUADMA
 * on 26/12/2016.
 *
 */

@ConfigurationProperties(prefix = "swagger")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerConfigurationProperties {

    private String title = "My API";

    private String description = "API documentation";

    private String version = "0.0.1";

    private String termsOfServiceUrl;

    private String contactName = "John Doe";

    private String contactUrl;

    private String contactEmail = "john.doe@gmail.com";

    private String license = "Community Commons";

    private String licenseUrl;
}
