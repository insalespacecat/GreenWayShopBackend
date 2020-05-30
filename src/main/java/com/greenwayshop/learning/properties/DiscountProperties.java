package com.greenwayshop.learning.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "internalproperties.discount")
@Data
public class DiscountProperties {
    private Double levelone = 5.0;
    private Double leveltwo = 7.0;
    private Double levelthree = 10.0;
    private Double employee = 10.0;
    private Double totalforlevelone = 0.0;
    private Double totalforleveltwo = 200.0;
    private Double totalforlevelthree = 500.0;
}
