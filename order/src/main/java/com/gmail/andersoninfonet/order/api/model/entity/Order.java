package com.gmail.andersoninfonet.order.api.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private String productId; 
    private String userId; 
    private String addressId; 
    private int quantity;    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
