package com.gmail.andersoninfonet.common.commands;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CancelOrderCommand(@TargetAggregateIdentifier String orderId, OrderStatus orderStatus) {
    
    // private String orderId;
    // private OrderStatus orderStatus = OrderStatus.CANCELLED;

    public CancelOrderCommand(String orderId) {
        this(orderId, OrderStatus.CANCELLED);
    }
}
