package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import com.rw.numbered.orders.dao.OrderDao;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.order.PreOrder;
import com.rw.numbered.orders.dto.request.TripInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Service
@Validated
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    XMLGateService xmlGateService;

    public Order createOrder(@Valid OrderingInformation orderingInformation) {
        BuyTicketResponse etInfo = xmlGateService.buyTicket(orderingInformation);
        return new Order();
    }

    public PreOrder getPreOrderInfo(@Valid TripInformation tripInformation) {
        return new PreOrder();
    }

    public void deleteOrder(@Valid @Min(1) long orderId) {

    }

    public Order getOrder(@Valid @Min(1) long orderId) {
        return new Order();
    }

    public List<Order> getOrders(@Valid @Size(max = 30) String filter) {
        return orderDao.getOrders(filter);
    }

}
