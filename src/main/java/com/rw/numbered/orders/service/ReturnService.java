package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.ReturnInfo;
import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Service
@Validated
public class ReturnService {
    public Order returnOrder(@Valid @Min(1) long orderId) {
        return returnTicket(orderId, 0);
    }

    public Order returnTicket(@Valid @Min(1) long orderId, @Valid @Min(1) long ticketId) {
        return new Order();
    }

    public ReturnInfo checkReturnOrder(@Valid @Min(1) long orderId) { return  new ReturnInfo(); }

    public ReturnInfo checkReturnTicket(@Valid @Min(1) long orderId, @Valid @Min(1) long ticketId) { return  new ReturnInfo(); }

}
