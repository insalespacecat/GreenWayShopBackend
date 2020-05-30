package com.greenwayshop.learning.properties;

//Properties for admin mode of the shop

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "internalproperties.admin")
@Data
public class AdministrationProperties {
    private String employeeKey = "supsup";
}
