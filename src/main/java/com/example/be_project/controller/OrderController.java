package com.example.be_project.controller;

import com.example.be_project.model.dto.OrderDTO;
import com.example.be_project.model.entities.Order;
import com.example.be_project.model.entities.OrderDetail;
import com.example.be_project.repository.OrderDetailRepository;
import com.example.be_project.repository.OrderRepository;
import com.example.be_project.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping()
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("{id}")
    public Optional<Order> getOne(@PathVariable Integer id){
        return orderRepository.findById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping("/create")
    public ResponseEntity<?> createNewOrder(@RequestBody OrderDTO orderDTO, @RequestHeader (name="Authorization") String token) {
        if(jwtUtil.validateToken(token.replace("Bearer ",""))){
            int userId= jwtUtil.getUserByIdfromJWT(token.replace("Bearer ",""));

            // Tạo mới 1 order và lưu vào DB
            Order orderNew = new Order(userId);
            orderRepository.save(orderNew);
            for (OrderDetail od: orderDTO.getOrderDetails()) {
                // Tạo ra các order details
                OrderDetail detail = new OrderDetail();
                detail.setOrder(orderNew);
                detail.setProductId(od.getProductId());
                detail.setQuantity(od.getQuantity());
                orderDetailRepository.save(detail);
            }
            return ResponseEntity.ok("Order Successfully!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized!");

    }


}
