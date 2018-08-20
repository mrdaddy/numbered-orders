package com.rw.numbered.orders.dto.order;

import lombok.Data;

@Data
public class OrderSeatDetail {
    private int seatCount;
    private String seats;
    private String compartmentType;
}
