package com.rw.numbered.orders.dto.ticket;

import com.rw.numbered.orders.dto.passenger.Passenger;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Ticket {
    private long id;
    private long orderId;
    private String expressNo;
    private String ticketNo;
    private String status;
    private String timeBeforeDeparture;
    private String tListIssued;
    private boolean hasQrCode;

    private TariffDetail tariffDetail;
    private double cost;
    private double costEuro;

    private int passengerCount;
    private TicketSeatDetail ticketSeatDetail;
    private String registrationStatus;
    private Date registrationTime;

    private TicketReturn ticketReturn;

    private List<Passenger> passengers;
}
