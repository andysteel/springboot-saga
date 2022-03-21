package com.gmail.andersoninfonet.payment.api.events;

import java.time.LocalDateTime;

import com.gmail.andersoninfonet.common.events.PaymentProcessedEvent;
import com.gmail.andersoninfonet.payment.api.model.entity.Payment;
import com.gmail.andersoninfonet.payment.api.model.enums.PaymentStatus;
import com.gmail.andersoninfonet.payment.api.repository.PaymentRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
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
}
