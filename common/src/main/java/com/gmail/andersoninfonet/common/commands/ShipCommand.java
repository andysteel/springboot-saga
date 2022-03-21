package com.gmail.andersoninfonet.common.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipCommand {
    
    @TargetAggregateIdentifier 
    private String shipmentId; 
    private String orderId;
}
