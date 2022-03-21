package com.gmail.andersoninfonet.order.api.events;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    
    private String orderId; 
    private String productId; 
    private String userId; 
    private String addressId; 
    private int quantity;
    private  OrderStatus orderStatus;
}
