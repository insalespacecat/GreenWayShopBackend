package com.greenwayshop.learning.domain;

import com.greenwayshop.learning.enums.Authority;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@Table(name="UserDetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    private String username;
    private String password;
    private String name;
    private Boolean active;
    private String phoneNumber;
    private String shippingAddress;
    private Double discount;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "UserAuthorities", joinColumns = @JoinColumn(name = "UserId"))
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    void grantCustomerAuthority(){
        authorities.add(Authority.CUSTOMER);
    }
    void grantEmployeeAuthority(){
        authorities.add(Authority.EMPLOYEE);
    }



    public static User newCustomerFromRegistrationForm(BCryptPasswordEncoder passwordEncoder, RegistrationForm registrationForm) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setName(registrationForm.getName());
        user.grantCustomerAuthority();
        user.setActive(true);
        user.setShippingAddress(registrationForm.getShippingAddress());
        user.setPhoneNumber(registrationForm.getPhoneNumber());
        user.setDiscount(5.0);
        return user;
    }
}