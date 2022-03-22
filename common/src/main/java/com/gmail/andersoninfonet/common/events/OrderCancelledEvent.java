package com.gmail.andersoninfonet.common.events;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCancelledEvent {
    
    private String orderId;
    private OrderStatus orderStatus;
}
