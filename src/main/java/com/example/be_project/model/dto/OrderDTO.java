package com.example.be_project.model.dto;

import com.example.be_project.model.entities.OrderDetail;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
    Integer id;
    Integer userId;

    String orderStatus;

    Date orderCreatedAt;

    double totalPrice;
    List<OrderDetail> orderDetails;

}
