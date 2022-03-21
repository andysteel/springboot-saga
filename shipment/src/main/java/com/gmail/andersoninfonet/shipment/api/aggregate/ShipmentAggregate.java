package com.gmail.andersoninfonet.shipment.api.aggregate;

import com.gmail.andersoninfonet.common.commands.ShipCommand;
import com.gmail.andersoninfonet.common.events.ShippedEvent;
import com.gmail.andersoninfonet.common.model.enums.ShipmentStatus;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class ShipmentAggregate {
    
    @AggregateIdentifier
    private String shipmentId; 
    private String orderId; 
    private ShipmentStatus shipmentStatus;

    @CommandHandler
    public ShipmentAggregate(ShipCommand shipCommand) {

        var shippedEvent = new ShippedEvent(shipCommand.getShipmentId(), shipCommand.getOrderId(), ShipmentStatus.COMPLETED);
    
        AggregateLifecycle.apply(shippedEvent);
    }

    @EventSourcingHandler
    public void on(ShippedEvent event) {
        this.shipmentId = event.getShipmentId();
        this.orderId = event.getOrderId();
        this.shipmentStatus = event.getShipmentStatus();
    }
}
