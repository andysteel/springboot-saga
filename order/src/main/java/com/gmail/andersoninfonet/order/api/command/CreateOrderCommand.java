package com.gmail.andersoninfonet.order.api.command;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {
    
    @TargetAggregateIdentifier 
    private String orderId; 
    private String productId; 
    private String userId; 
    private String addressId; 
    private int quantity; 
    private OrderStatus orderStatus;
}
