package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Service
@Validated
public class PDFService {
    public byte[] getOrderPDF(@Valid @Min(1) long orderId) {
        return new byte[200];
    }

    public byte[] getTicketPDF(@Valid @Min(1) long orderId, long ticketId) {
        return new byte[200];
    }
}
