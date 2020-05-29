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
/*
    public User toUser(BCryptPasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setShippingAddress(shippingAddress);
        user.grantCustomerAuthority();
        return user;
    }
    */
}
