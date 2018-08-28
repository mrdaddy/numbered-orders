package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import com.rw.numbered.orders.dao.OrderDao;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.trip.OrderingInformation;
import com.rw.numbered.orders.dto.trip.PreOrder;
import com.rw.numbered.orders.dto.trip.TripInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    XMLGateService xmlGateService;

    public Order createOrder(OrderingInformation orderingInformation) {
        BuyTicketResponse etInfo = xmlGateService.buyTicket(orderingInformation);
        return new Order();
    }

    public PreOrder getPreOrderInfo(TripInformation tripInformation) {
        return new PreOrder();
    }

    public void deleteOrder(long orderId) {

    }

    public Order getOrder(long orderId) {
        return new Order();
    }

    public List<Order> getOrders(String filter) {
        return orderDao.getOrders(filter);
    }

}
