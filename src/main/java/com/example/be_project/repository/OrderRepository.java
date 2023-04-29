package com.example.be_project.repository;

import com.example.be_project.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {

    @Query(value =" SELECT * FROM Orders O LEFT JOIN Oder_Detail OD ON OD.Order_Id=O.id WHERE O.User_Id = :userId AND O.Order_Status = :orderStatus ", nativeQuery = true)
    Optional<Order> findOrder(@Param("userId") Integer userId, @Param("orderStatus") String orderStatus);

    @Query(value =" SELECT * FROM Orders O LEFT JOIN Oder_Detail OD ON OD.Order_Id=O.id WHERE O.User_Id = :userId ", nativeQuery = true)
    Optional<Order> findOrderByUserId(@Param("userId") Integer userId);
}
