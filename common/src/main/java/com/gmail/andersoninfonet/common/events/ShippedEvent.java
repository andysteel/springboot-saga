package com.gmail.andersoninfonet.common.events;

import com.gmail.andersoninfonet.common.model.enums.ShipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippedEvent {
    
    private String shipmentId; 
    private String orderId; 
    private ShipmentStatus shipmentStatus;
}
