package com.gmail.andersoninfonet.order.api.controller;

import java.util.UUID;

import com.gmail.andersoninfonet.common.model.enums.OrderStatus;
import com.gmail.andersoninfonet.order.api.command.CreateOrderCommand;
import com.gmail.andersoninfonet.order.api.model.request.OrderRequest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;
    
    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {

        String orderId = UUID.randomUUID().toString();

        var createOrderCommand = new CreateOrderCommand(orderId, orderRequest.productId(), orderRequest.userId(), orderRequest.addressId(), orderRequest.quantity(), OrderStatus.CREATED);

        commandGateway.sendAndWait(createOrderCommand);
        
        return "Success";
    }
}
