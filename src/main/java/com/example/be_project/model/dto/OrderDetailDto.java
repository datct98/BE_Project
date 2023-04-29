package com.example.be_project.model.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    Integer id;
    String productName;

    String productImage;

    double productPriceOut;
    Integer quantity;
    Integer orderId;

    public OrderDetailDto(Integer id,  String productName,String productImage, double productPriceOut, Integer quantity, Integer orderId) {
        this.id = id;
        this.productName = productName;
        this.productImage= productImage;
        this.productPriceOut = productPriceOut;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}
