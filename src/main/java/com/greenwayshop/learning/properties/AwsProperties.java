package com.greenwayshop.learning.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsProperties {
    private String accessKey = "AKIAXTA5YW36EVRHFFNS";
    private String secretKey = "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";
}
