package com.rw.numbered.orders.dto.ticket;

import lombok.Data;

@Data
public class TicketSeatDetail {
    private int seatCount;
    private String seats;
    private String seatDesc;
}
