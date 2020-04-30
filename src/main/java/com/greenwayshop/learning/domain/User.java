package com.greenwayshop.learning.domain;

import com.greenwayshop.learning.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String shippingAddress;
    private Role role;

    void grantCustomerAuthority(){
        role = Role.CUSTOMER;
    }
    void grantEmployeeAuthority(){
        role = Role.EMPLOYEE;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        final String role = "ROLE_" + getRole().toString();
        final GrantedAuthority userAuthority = new SimpleGrantedAuthority(role);
        return Arrays.asList(userAuthority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}