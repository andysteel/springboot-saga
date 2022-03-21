package com.gmail.andersoninfonet.common.events;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCompletedEvent {
    
    private String orderId; 
    private OrderStatus orderStatus;
}
