package com.gmail.andersoninfonet.common.events;

import com.gmail.andersoninfonet.common.model.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentCancelledEvent {
    private String paymentId; 
    private String orderId; 
    private PaymentStatus paymentStatus;
}
