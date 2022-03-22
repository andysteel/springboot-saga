package com.gmail.andersoninfonet.payment.api.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gmail.andersoninfonet.common.model.enums.PaymentStatus;

import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    
    @Id
    private String paymentId;
    private String orderId;
    private LocalDateTime timeStamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
