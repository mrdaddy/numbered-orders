package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Service;

@Service
public class PDFService {
    public byte[] getOrderPDF(long orderId) {
        return new byte[200];
    }

    public byte[] getTicketPDF(long orderId, long ticketId) {
        return new byte[200];
    }
}
