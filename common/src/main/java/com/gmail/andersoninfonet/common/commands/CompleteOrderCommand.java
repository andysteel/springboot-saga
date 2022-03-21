package com.gmail.andersoninfonet.common.commands;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteOrderCommand {
    
    @TargetAggregateIdentifier 
    private String orderId; 
    private OrderStatus orderStatus;
}
