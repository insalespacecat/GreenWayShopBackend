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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    ArrayList<CartItem> items;
    Double total;
    String name;
    String address;
    String phoneNumber;
    String paymentMethod;

    //This type of annotation requires entity
    @ManyToOne
    User user;
}
