package com.rw.numbered.orders.dto.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRegistration {
    private boolean allowed;
    private boolean needed;
    private String status;
    private Date regTime;
    private Date stopTime;
    private boolean withASMBP;


}
