package com.example.be_project.controller;


import com.example.be_project.model.entities.Order;
import com.example.be_project.model.entities.OrderDetail;
import com.example.be_project.model.entities.Product;
import com.example.be_project.repository.OrderDetailRepository;
import com.example.be_project.repository.OrderRepository;
import com.example.be_project.repository.ProductRepository;
import com.example.be_project.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderDetail")
@Slf4j

public class OrderDetailController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private OrderRepository orderRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping()
    public List<OrderDetail> getAllOrderItem(@RequestHeader (name="Authorization") String token){
        System.out.println("token: "+token);
        Integer userId = jwtUtil.getUserByIdfromJWT(token.replace("Bearer ",""));
        return orderDetailRepository.findAll();
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.PATCH)
    @PatchMapping
    public OrderDetail upDateOrderDetail(@RequestBody OrderDetail orderDetail){
        return  orderDetailRepository.save(orderDetail);
    }


}
