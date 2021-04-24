package com.greenwayshop.learning.domain;

import lombok.*;

@Data
@RequiredArgsConstructor
public class RegistrationForm {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String shippingAddress;
    private String employeeKey;
}
