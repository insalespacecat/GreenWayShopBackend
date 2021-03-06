package com.greenwayshop.learning.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "internalproperties.services")
@Data
public class ServiceProperties {
    private int topSliceForOperatorViewSize = 25;
    private String imageBucketURL = "s3://greenway-shop/";
}
