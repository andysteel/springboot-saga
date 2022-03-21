package com.gmail.andersoninfonet.payment.api.repository;

import com.gmail.andersoninfonet.payment.api.model.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    
}
