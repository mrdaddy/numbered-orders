package com.rw.numbered.orders.dto.ticket;

import lombok.Data;

@Data
public class TariffDetail {
    private String tariffType;
    private String currencyCode;
    private double tariff;
    private double ticketTariff;
    private double reservedSeatTariff;
    private double insuranceTariff;
    private double serviceTariff;
    private double tariffVat;
    private double serviceTariffVat;
    private double commissionFee;
    private double commissionVat;
}
