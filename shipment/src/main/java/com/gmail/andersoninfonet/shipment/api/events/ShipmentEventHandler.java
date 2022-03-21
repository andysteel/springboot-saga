package com.gmail.andersoninfonet.shipment.api.events;

import com.gmail.andersoninfonet.common.events.ShippedEvent;
import com.gmail.andersoninfonet.shipment.api.model.entity.Shipment;
import com.gmail.andersoninfonet.shipment.api.repository.ShipmentRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ShipmentEventHandler {

    private ShipmentRepository shipmentRepository;

    public ShipmentEventHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }



    @EventHandler
    public void on(ShippedEvent event) {
        var shipment = new Shipment();

        BeanUtils.copyProperties(event, shipment);

        this.shipmentRepository.save(shipment);
    }
}
