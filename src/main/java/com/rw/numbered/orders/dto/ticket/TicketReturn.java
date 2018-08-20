package com.rw.numbered.orders.dto.ticket;

import lombok.Data;

import java.util.Date;

@Data
public class TicketReturn {
    private String expressNo;
    private double amount;
    private double ticketTariff;
    private double reservedSeatTariff;
    private double serviceTariff;
    private double insuranceTariff;
    private double commissionFee;
    private double commission;
    private Date datetime;
    private String method;
    private String methodDesc;
    private String info;
    private boolean online;
    private boolean inPaymentSystem;

}
