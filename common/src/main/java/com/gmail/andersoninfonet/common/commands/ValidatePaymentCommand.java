package com.gmail.andersoninfonet.common.commands;

import com.gmail.andersoninfonet.common.model.CardDetails;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatePaymentCommand {
    
    @TargetAggregateIdentifier 
    private String paymentId; 
    private String orderId; 
    private CardDetails cardDetails;
}
