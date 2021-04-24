package com.greenwayshop.learning.domain;

import com.greenwayshop.learning.enums.Authority;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@Table(name="UserDetails")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "UserAuthorities", joinColumns = @JoinColumn(name = "UserId"))
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities = new HashSet<Authority>();

    private String username;
    private String password;
    private String name;
    private Boolean active;
    private String phoneNumber;
    private String shippingAddress;
    private Double ordersTotal;
    private Double discount;


    public Set<Authority> getAuthorities(){
        return authorities;
    }
    public void grantCustomerAuthority(){
        authorities.add(Authority.CUSTOMER);
    }
    public void grantEmployeeAuthority(){
        authorities.add(Authority.EMPLOYEE);
    }
}