package com.rw.numbered.orders.dto;

import java.util.Date;

public class Ticket {
    private long id;
    private long orderId;
    private String expressNo;
    private String ticketNo;
    private String status;
    private String timeBeforeDeparture;
    private String tListIssued;
    private boolean hasQrCode;

    private String tariffType;
    private String currencyCode;
    private double cost;
    private double costEuro;
    private double tariff;
    private double ticketTariff;
    private double reservedSeatTariff;
    private double insuranceTariff;
    private double serviceTariff;
    private double tariffVat;
    private double serviceTariffVat;
    private double commissionFee;
    private double commissionVat;

    private int passengerCount;
    private int seatCount;
    private String seats;
    private String seatDesc;

    private String registrationStatus;
    private Date registrationTime;

    private String returnExpressNo;
    private double returnedAmount;
    private double returnedTicketTariff;
    private double returnedReservedSeatTariff;
    private double returnedServiceTariff;
    private double returnedInsuranceTariff;
    private double returnedCommissionFee;
    private double returnedCommission;
    private Date returnTime;
    private String returnMethod;
    private String returnMethodDesc;
    private String returnInfo;
    private boolean onlineReturn;
    private boolean returnedInPaymentSystem;

}
