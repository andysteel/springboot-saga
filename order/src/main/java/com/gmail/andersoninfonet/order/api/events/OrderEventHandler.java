package com.gmail.andersoninfonet.order.api.events;

import com.gmail.andersoninfonet.common.events.OrderCancelledEvent;
import com.gmail.andersoninfonet.common.events.OrderCompletedEvent;
import com.gmail.andersoninfonet.order.api.model.entity.Order;
import com.gmail.andersoninfonet.order.api.repository.OrderRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@Slf4j
public class OrderEventHandler {
    
    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        var order = new Order();
        BeanUtils.copyProperties(event, order);
        this.orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        this.orderRepository.findById(event.getOrderId())
        .ifPresentOrElse(order -> {
            order.setOrderStatus(event.getOrderStatus());
            this.orderRepository.save(order);
        }, () -> log.warn("Order {} not found", event.getOrderId()));
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        this.orderRepository.findById(event.getOrderId())
        .ifPresentOrElse(order -> {
            order.setOrderStatus(event.getOrderStatus());
            this.orderRepository.save(order);
        }, () -> log.warn("Order {} not found", event.getOrderId()));
    }
}
