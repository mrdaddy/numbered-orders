package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Service
public class RegistrationService {
    public Order changeTicketRegisterStatus(@Valid @Min(1) long orderId, boolean isRegistered) {
        return changeOrderRegisterStatus(orderId, 0, isRegistered);
    }

    public Order changeOrderRegisterStatus(@Valid @Min(1)long orderId, long ticketId, boolean isRegistered) {
        return new Order();
    }
}
