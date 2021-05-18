package com.greenwayshop.learning.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsProperties {
    private String accessKey = "AKIAXTA5YW36LLSRWWSU";
    private String secretKey = "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";
}
