package com.example.be_project.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name ="Product")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

public class Product implements Serializable {
    @Id
    @Column(name= "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name= "Name")
    String name;

    @Column(name= "Product_Price_In")
    double productPriceIn;

    @Column(name= "Product_Price_Out")
    double productPriceOut;

    @Column(name= "Product_Image")
    String productImage;

    @Column(name= "Description")
    String description;

    @Column(name= "Discount")
    Integer discount;

    @Column(name= "Created_at")
    Date createdAt;

    @Column(name= "Rate")
    String rate;

    @Column(name= "Quantity")
    String quantity;

    @Column(name= "Product_Status")
    String status;

    @Column(name = "Category_Id")
    Integer categoryId;


}
