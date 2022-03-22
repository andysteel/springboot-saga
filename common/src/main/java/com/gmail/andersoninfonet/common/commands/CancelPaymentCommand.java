package com.gmail.andersoninfonet.common.commands;

import com.gmail.andersoninfonet.common.model.enums.PaymentStatus;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CancelPaymentCommand(@TargetAggregateIdentifier String paymentId, String orderId, PaymentStatus paymentStatus) {
    
    public CancelPaymentCommand(String paymentId, String orderId) {
        this(paymentId, orderId, PaymentStatus.CANCELLED);
    }
}
