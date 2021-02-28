package com.naukrimilega.configs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DataManagerConfigs extends Configuration {

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;
}
