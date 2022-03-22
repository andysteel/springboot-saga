package com.gmail.andersoninfonet.order.api.saga;

import java.util.UUID;

import com.gmail.andersoninfonet.common.commands.CancelOrderCommand;
import com.gmail.andersoninfonet.common.commands.CancelPaymentCommand;
import com.gmail.andersoninfonet.common.commands.CompleteOrderCommand;
import com.gmail.andersoninfonet.common.commands.ShipCommand;
import com.gmail.andersoninfonet.common.commands.ValidatePaymentCommand;
import com.gmail.andersoninfonet.common.events.OrderCancelledEvent;
import com.gmail.andersoninfonet.common.events.OrderCompletedEvent;
import com.gmail.andersoninfonet.common.events.PaymentCancelledEvent;
import com.gmail.andersoninfonet.common.events.PaymentProcessedEvent;
import com.gmail.andersoninfonet.common.events.ShippedEvent;
import com.gmail.andersoninfonet.common.model.User;
import com.gmail.andersoninfonet.common.model.enums.OrderStatus;
import com.gmail.andersoninfonet.common.queries.GetUserPaymentDetailsQuery;
import com.gmail.andersoninfonet.order.api.events.OrderCreatedEvent;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id {}", event.getOrderId());

        var getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(event.getOrderId());

        try {
            var user = queryGateway.query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();
            var validatePayment = new ValidatePaymentCommand(UUID.randomUUID().toString(), event.getOrderId(), user.getCardDetails());

            commandGateway.sendAndWait(validatePayment);
        } catch (Exception e) {
            log.error(e.getMessage());
            cancelOrderCommand(event.getOrderId());
            //Start compensating transaction
        }

    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent in Saga for Order Id {}", event.getOrderId());

        try {
            if(true) {
                throw new RuntimeException("Testando SAGA");
            }
            var shipCommand = new ShipCommand(UUID.randomUUID().toString(), event.getOrderId());

            commandGateway.send(shipCommand);   
        } catch (Exception e) {
            log.error(e.getMessage());
            cancelPaymentCommand(event);
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ShippedEvent event) {
        log.info("ShippedEvent in Saga for Order Id {}", event.getOrderId());

        try {
            var completeOrderCommand = new CompleteOrderCommand(event.getOrderId(), OrderStatus.APPROVED);
            commandGateway.send(completeOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga for Order Id {}", event.getOrderId());

    }

    private void cancelOrderCommand(String orderId) {
        commandGateway.send(new CancelOrderCommand(orderId));
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga for Order Id {}", event.getOrderId());
    }

    private void cancelPaymentCommand(PaymentProcessedEvent event) {
        commandGateway.send(new CancelPaymentCommand(event.getPaymentId(), event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentCancelledEvent event) {
        log.info("PaymentCancelledEvent in Saga for Order Id {}", event.getOrderId());
        cancelOrderCommand(event.getOrderId());
    }
}
