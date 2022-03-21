package com.gmail.andersoninfonet.payment.api.aggregate;

import com.gmail.andersoninfonet.common.commands.ValidatePaymentCommand;
import com.gmail.andersoninfonet.common.events.PaymentProcessedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aggregate
@Slf4j
@Data
@NoArgsConstructor
public class PaymentAggregate {
    
    @AggregateIdentifier
    private String paymentId; 
    private String orderId; 
    private String paymentStatus;

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        //validation the payment details
        //publish the payment processed event
        log.info("Executing validatePaymentCommand for orderId: {} and paymentID: {}", validatePaymentCommand.getOrderId(), validatePaymentCommand.getPaymentId());

        var paymentProcessedEvent = new PaymentProcessedEvent(validatePaymentCommand.getPaymentId(), validatePaymentCommand.getOrderId());
    
        AggregateLifecycle.apply(paymentProcessedEvent);

        log.info("PaymentProcessedEvent Applied");
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        this.orderId = event.getOrderId();
        this.paymentId = event.getPaymentId();
    }
}
