package com.gmail.andersoninfonet.order.api.repository;

import com.gmail.andersoninfonet.order.api.model.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    
}
