package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.ReturnInfo;
import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {
    public Order returnOrder(long orderId) {
        return returnTicket(orderId, 0);
    }

    public Order returnTicket(long orderId, long ticketId) {
        return new Order();
    }

    public ReturnInfo checkReturnOrder(long orderId) { return  new ReturnInfo(); }

    public ReturnInfo checkReturnTicket(long orderId, long ticketId) { return  new ReturnInfo(); }

}
