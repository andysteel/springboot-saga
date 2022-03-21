package com.gmail.andersoninfonet.shipment.api.repository;

import com.gmail.andersoninfonet.shipment.api.model.entity.Shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {
    
}
