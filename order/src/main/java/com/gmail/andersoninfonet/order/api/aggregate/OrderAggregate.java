package com.gmail.andersoninfonet.order.api.aggregate;

import com.gmail.andersoninfonet.common.commands.CompleteOrderCommand;
import com.gmail.andersoninfonet.common.events.OrderCompletedEvent;
import com.gmail.andersoninfonet.common.model.enums.OrderStatus;
import com.gmail.andersoninfonet.order.api.command.CreateOrderCommand;
import com.gmail.andersoninfonet.order.api.events.OrderCreatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId; 
    private String productId;
    private String userId; 
    private String addressId; 
    private int quantity; 
    private OrderStatus orderStatus;

    public OrderAggregate() {
        
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        var orderCreatedEvent = new OrderCreatedEvent(createOrderCommand.getOrderId(), createOrderCommand.getProductId(), createOrderCommand.getUserId(), createOrderCommand.getAddressId(), createOrderCommand.getQuantity(), createOrderCommand.getOrderStatus());
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    public OrderAggregate(OrderCreatedEvent event) {
        this.userId = event.getUserId();
        this.productId = event.getProductId();
        this.orderId = event.getOrderId();
        this.addressId = event.getAddressId();
        this.quantity = event.getQuantity();
        this.orderStatus = event.getOrderStatus();
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderStatus = event.getOrderStatus();
        this.userId = event.getUserId();
        this.orderId = event.getOrderId();
        this.quantity = event.getQuantity();
        this.productId = event.getProductId();
        this.addressId = event.getAddressId();
    }

    @CommandHandler
    public void handle(CompleteOrderCommand completeOrderCommand) {
        //validate the command, if everything is ok publish order completed event
        var orderCompletedEvent = new OrderCompletedEvent(completeOrderCommand.getOrderId(), completeOrderCommand.getOrderStatus());
        AggregateLifecycle.apply(orderCompletedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderStatus = event.getOrderStatus();
    }
}
