package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public Order changeTicketRegisterStatus(long orderId, boolean isRegistered) {
        return changeOrderRegisterStatus(orderId, 0, isRegistered);
    }

    public Order changeOrderRegisterStatus(long orderId, long ticketId, boolean isRegistered) {
        return new Order();
    }
}
