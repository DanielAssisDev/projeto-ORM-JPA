package com.danielassisdesenvolvedor.projetojavaorm.dto;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Payment;

import java.time.Instant;

public class PaymentDTO {
    private Long id;
    private Instant moment;

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment payment) {
        id = payment.getId();
        moment = payment.getMoment();
    }

    public PaymentDTO() {
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
