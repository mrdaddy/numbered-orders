package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.ReturnInfo;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.security.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Service
@Validated
public class ReturnService {
    public Order returnOrder(@Valid @Min(1) long orderId, @Valid User user) {
        return returnTicket(orderId, 0, user);
    }

    public Order returnTicket(@Valid @Min(1) long orderId, @Valid @Min(1) long ticketId, @Valid User user) {
        return new Order();
    }

    public ReturnInfo checkReturnOrder(@Valid @Min(1) long orderId, @Valid User user) { return  new ReturnInfo(); }

    public ReturnInfo checkReturnTicket(@Valid @Min(1) long orderId, @Valid @Min(1) long ticketId, @Valid User user) { return  new ReturnInfo(); }

}
