package com.gmail.andersoninfonet.shipment.api.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gmail.andersoninfonet.common.model.enums.ShipmentStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "shipment")
public class Shipment {
    
    @Id
    private String shipmentId;
    private String orderId;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
}
