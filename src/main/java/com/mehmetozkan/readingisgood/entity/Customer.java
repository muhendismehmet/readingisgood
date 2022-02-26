package com.mehmetozkan.readingisgood.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "T_CUSTOMER")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private int version;

    @NotEmpty(message = "Name can not be empty.")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname can not be empty.")
    @Column(name = "surname")
    private String surname;

    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    public void add(Order order) {
        if (order != null) {
            if (orderList == null) {
                orderList = new ArrayList<>();
            }
            orderList.add(order);
            order.setCustomerId(this.getId());
        }
    }

}
