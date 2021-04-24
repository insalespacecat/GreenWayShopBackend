package com.greenwayshop.learning.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Table(name="orders")
public class Order implements Serializable {
    @ManyToOne
    public AppUser user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ArrayList<CartItem> items;
    private Double total;
    private String name;
    private String address;
    private String phoneNumber;
    private String paymentMethod;
}
