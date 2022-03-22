package com.gmail.andersoninfonet.payment.api.events;

import java.time.LocalDateTime;

import com.gmail.andersoninfonet.common.events.PaymentCancelledEvent;
import com.gmail.andersoninfonet.common.events.PaymentProcessedEvent;
import com.gmail.andersoninfonet.common.model.enums.PaymentStatus;
import com.gmail.andersoninfonet.payment.api.model.entity.Payment;
import com.gmail.andersoninfonet.payment.api.repository.PaymentRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@Slf4j
public class PaymentEventHandler {

    private PaymentRepository paymentRepository;

    public PaymentEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        var payment = new Payment();
        BeanUtils.copyProperties(event, payment);
        payment.setTimeStamp(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.COMPLETED);

        paymentRepository.save(payment);
    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        paymentRepository.findById(event.getPaymentId())
        .ifPresentOrElse(p -> {
            p.setPaymentStatus(event.getPaymentStatus());
            paymentRepository.save(p);
        }, () -> log.warn("Payment {} not found", event.getPaymentId()));
    }
}
