package com.example.be_project.model.entities;

import com.example.be_project.util.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name ="Orders")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Order {
    @Id
    @Column(name= "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name= "User_id")
    Integer userId;

    @Column(name= "Order_Status")
    String orderStatus;

    @Column(name= "Order_Created_at")
    Date orderCreatedAt;

    @OneToMany(mappedBy = "order")
    Set<OrderDetail> orderDetails;

    public Order(Integer userId) {
        this.userId = userId;
        this.orderStatus = Status.STATUS_WAITING;
        this.orderCreatedAt = new Date(System.currentTimeMillis());
    }

}
